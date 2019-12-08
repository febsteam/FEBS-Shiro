package com.hwtx.pf4j;

import com.hwtx.pf4j.util.FileUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.*;

/**
 * @author Decebal Suiu
 * @author Mário Franco
 */
public class BasePluginRepository implements PluginRepository {

    protected final Path pluginsRoot;

    protected FileFilter filter;
    protected Comparator<File> comparator;

    public BasePluginRepository(Path pluginsRoot) {
        this(pluginsRoot, null);
    }

    public BasePluginRepository(Path pluginsRoot, FileFilter filter) {
        this.pluginsRoot = pluginsRoot;
        this.filter = filter;

        // last modified file is first
        this.comparator = (o1, o2) -> (int) (o2.lastModified() - o1.lastModified());
    }

    public void setFilter(FileFilter filter) {
        this.filter = filter;
    }

    /**
     * Set a {@link File} {@link Comparator} used to sort the listed files from {@code pluginsRoot}.
     * This comparator is used in {@link #getPluginPaths()} method.
     * By default is used a file comparator that returns the last modified files first.
     * If you don't want a file comparator, then call this method with {@code null}.
     */
    public void setComparator(Comparator<File> comparator) {
        this.comparator = comparator;
    }

    @Override
    public List<Path> getPluginPaths() {
        File[] files = pluginsRoot.toFile().listFiles(filter);

        if ((files == null) || files.length == 0) {
            return Collections.emptyList();
        }

        if (comparator != null) {
            Arrays.sort(files, comparator);
        }

        List<Path> paths = new ArrayList<>(files.length);
        for (File file : files) {
            paths.add(file.toPath());
        }

        return paths;
    }

    @Override
    public boolean deletePluginPath(Path pluginPath) {
        if (!filter.accept(pluginPath.toFile())) {
            return false;
        }

        try {
            FileUtils.delete(pluginPath);
            return true;
        } catch (NoSuchFileException e) {
            // Return false on not found to be compatible with previous API (#135)
            return false;
        } catch (IOException e) {
            throw new PluginRuntimeException(e);
        }
    }

}
