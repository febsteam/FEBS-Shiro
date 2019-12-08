/*
 * Copyright (C) 2012-present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hwtx.pf4j.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Decebal Suiu
 */
public class FileUtils {

    private static final Logger log = LoggerFactory.getLogger(FileUtils.class);

    public static List<String> readLines(Path path, boolean ignoreComments) throws IOException {
        File file = path.toFile();
        if (!file.exists() || !file.isFile()) {
            return new ArrayList<>();
        }

        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (ignoreComments && !line.startsWith("#") && !lines.contains(line)) {
                    lines.add(line);
                }
            }
        }

        return lines;
    }

    /**
     * Use {@link #writeLines(Collection, Path)} instead.
     */
    @Deprecated
    public static void writeLines(Collection<String> lines, File file) throws IOException {
        writeLines(lines, file.toPath());
    }

    public static void writeLines(Collection<String> lines, Path path) throws IOException {
        Files.write(path, lines, StandardCharsets.UTF_8);
    }

    /**
     * Delete a file or recursively delete a folder, do not follow symlinks.
     *
     * @param path the file or folder to delete
     * @throws IOException if something goes wrong
     */
    public static void delete(Path path) throws IOException {
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {

           @Override
           public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
               if (!attrs.isSymbolicLink()) {
                   Files.delete(path);
               }

               return FileVisitResult.CONTINUE;
           }

           @Override
           public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
               Files.delete(dir);

               return FileVisitResult.CONTINUE;
           }

        });
    }

    public static List<File> getJars(Path folder) {
        List<File> bucket = new ArrayList<>();
        getJars(bucket, folder);

        return bucket;
    }

    private static void getJars(final List<File> bucket, Path folder) {
        FileFilter jarFilter = new JarFileFilter();
        FileFilter directoryFilter = new DirectoryFileFilter();

        if (Files.exists(folder) && Files.isDirectory(folder)) {
            File[] jars = folder.toFile().listFiles(jarFilter);
            for (int i = 0; (jars != null) && (i < jars.length); ++i) {
                bucket.add(jars[i]);
            }

            File[] directories = folder.toFile().listFiles(directoryFilter);
            for (int i = 0; (directories != null) && (i < directories.length); ++i) {
                File directory = directories[i];
                getJars(bucket, directory.toPath());
            }
        }
    }

    /**
     * Finds a path with various endings or null if not found.
     *
     * @param basePath the base name
     * @param endings a list of endings to search for
     * @return new path or null if not found
     */
    public static Path findWithEnding(Path basePath, String... endings) {
        for (String ending : endings) {
            Path newPath = basePath.resolveSibling(basePath.getFileName() + ending);
            if (Files.exists(newPath)) {
                return newPath;
            }
        }

        return null;
    }

    /**
     * Delete a file (not recursively) and ignore any errors.
     *
     * @param path the path to delete
     */
    public static void optimisticDelete(Path path) {
        if (path == null) {
            return;
        }

        try {
            Files.delete(path);
        } catch (IOException ignored) { }
    }

    /**
     * Unzip a zip file in a directory that has the same name as the zip file.
     * For example if the zip file is {@code my-plugin.zip} then the resulted directory
     * is {@code my-plugin}.
     *
     * @param filePath the file to evaluate
     * @return Path of unzipped folder or original path if this was not a zip file
     * @throws IOException on error
     */
    public static Path expandIfZip(Path filePath) throws IOException {
        if (!isZipFile(filePath)) {
            return filePath;
        }

        FileTime pluginZipDate = Files.getLastModifiedTime(filePath);
        String fileName = filePath.getFileName().toString();
        String directoryName = fileName.substring(0, fileName.lastIndexOf("."));
        Path pluginDirectory = filePath.resolveSibling(directoryName);

        if (!Files.exists(pluginDirectory) || pluginZipDate.compareTo(Files.getLastModifiedTime(pluginDirectory)) > 0) {
            // expand '.zip' file
            Unzip unzip = new Unzip();
            unzip.setSource(filePath.toFile());
            unzip.setDestination(pluginDirectory.toFile());
            unzip.extract();
            log.info("Expanded plugin zip '{}' in '{}'", filePath.getFileName(), pluginDirectory.getFileName());
        }

        return pluginDirectory;
    }

    /**
     * Return true only if path is a zip file.
     *
     * @param path to a file/dir
     * @return true if file with {@code .zip} ending
     */
    public static boolean isZipFile(Path path) {
        return Files.isRegularFile(path) && path.toString().toLowerCase().endsWith(".zip");
    }

    /**
     * Return true only if path is a jar file.
     *
     * @param path to a file/dir
     * @return true if file with {@code .jar} ending
     */
    public static boolean isJarFile(Path path) {
        return Files.isRegularFile(path) && path.toString().toLowerCase().endsWith(".jar");
    }

    public static Path getPath(Path path, String first, String... more) throws IOException {
        URI uri = path.toUri();
        if (isJarFile(path)) {
            String pathString = path.toAbsolutePath().toString();
            // transformation for Windows OS
            pathString = StringUtils.addStart(pathString.replace("\\", "/"), "/");
            // space is replaced with %20
            pathString = pathString.replaceAll(" ","%20");
            uri = URI.create("jar:file:" + pathString);
        }

        return getPath(uri, first, more);
    }

    public static Path getPath(URI uri, String first, String... more) throws IOException {
        return getFileSystem(uri).getPath(first, more);
    }

    public static Path findFile(Path directoryPath, String fileName) {
        File[] files = directoryPath.toFile().listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    if (file.getName().equals(fileName)) {
                        return file.toPath();
                    }
                } else if (file.isDirectory()) {
                    Path foundFile = findFile(file.toPath(), fileName);
                    if (foundFile != null) {
                        return foundFile;
                    }
                }
            }
        }

        return null;
    }

    private static FileSystem getFileSystem(URI uri) throws IOException {
        try {
            return FileSystems.getFileSystem(uri);
        } catch (FileSystemNotFoundException e) {
            return FileSystems.newFileSystem(uri, Collections.<String, String>emptyMap());
        }
    }

}
