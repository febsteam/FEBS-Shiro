package com.hwtx.pf4j;

import com.hwtx.pf4j.util.FileUtils;
import com.hwtx.pf4j.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * Read the plugin descriptor from the manifest file.
 *
 * @author Decebal Suiu
 */
public class ManifestPluginDescriptorFinder implements PluginDescriptorFinder {

    private static final Logger log = LoggerFactory.getLogger(ManifestPluginDescriptorFinder.class);

    public static final String PLUGIN_ID = "Plugin-Id";
    public static final String PLUGIN_DESCRIPTION = "Plugin-Description";
    public static final String PLUGIN_CLASS = "Plugin-Class";
    public static final String PLUGIN_VERSION = "Plugin-Version";
    public static final String PLUGIN_PROVIDER = "Plugin-Provider";
    public static final String PLUGIN_DEPENDENCIES = "Plugin-Dependencies";
    public static final String PLUGIN_REQUIRES = "Plugin-Requires";
    public static final String PLUGIN_LICENSE = "Plugin-License";

    @Override
    public boolean isApplicable(Path pluginPath) {
        return Files.exists(pluginPath) && (Files.isDirectory(pluginPath) || FileUtils.isJarFile(pluginPath));
    }

    @Override
    public PluginDescriptor find(Path pluginPath) {
        Manifest manifest = readManifest(pluginPath);

        return createPluginDescriptor(manifest);
    }

    protected Manifest readManifest(Path pluginPath) {
        if (FileUtils.isJarFile(pluginPath)) {
            try (JarFile jar = new JarFile(pluginPath.toFile())) {
                Manifest manifest = jar.getManifest();
                if (manifest != null) {
                    return manifest;
                }
            } catch (IOException e) {
                throw new PluginRuntimeException(e);
            }
        }

        Path manifestPath = getManifestPath(pluginPath);
        if (manifestPath == null) {
            throw new PluginRuntimeException("Cannot find the manifest path");
        }

        log.debug("Lookup plugin descriptor in '{}'", manifestPath);
        if (Files.notExists(manifestPath)) {
            throw new PluginRuntimeException("Cannot find '{}' path", manifestPath);
        }

        try (InputStream input = Files.newInputStream(manifestPath)) {
            return new Manifest(input);
        } catch (IOException e) {
            throw new PluginRuntimeException(e);
        }
    }

    protected Path getManifestPath(Path pluginPath) {
        if (Files.isDirectory(pluginPath)) {
            // legacy (the path is something like "classes/META-INF/MANIFEST.MF")
            return FileUtils.findFile(pluginPath, "MANIFEST.MF");
        }

        return null;
    }

    protected PluginDescriptor createPluginDescriptor(Manifest manifest) {
        DefaultPluginDescriptor pluginDescriptor = createPluginDescriptorInstance();

        // TODO validate !!!
        Attributes attributes = manifest.getMainAttributes();
        String id = attributes.getValue(PLUGIN_ID);
        pluginDescriptor.setPluginId(id);

        String description = attributes.getValue(PLUGIN_DESCRIPTION);
        if (StringUtils.isNullOrEmpty(description)) {
            pluginDescriptor.setPluginDescription("");
        } else {
            pluginDescriptor.setPluginDescription(description);
        }

        String clazz = attributes.getValue(PLUGIN_CLASS);
        if (StringUtils.isNotNullOrEmpty(clazz)) {
            pluginDescriptor.setPluginClass(clazz);
        }

        String version = attributes.getValue(PLUGIN_VERSION);
        if (StringUtils.isNotNullOrEmpty(version)) {
            pluginDescriptor.setPluginVersion(version);
        }

        String provider = attributes.getValue(PLUGIN_PROVIDER);
        pluginDescriptor.setProvider(provider);
        String dependencies = attributes.getValue(PLUGIN_DEPENDENCIES);
        pluginDescriptor.setDependencies(dependencies);

        String requires = attributes.getValue(PLUGIN_REQUIRES);
        if (StringUtils.isNotNullOrEmpty(requires)) {
            pluginDescriptor.setRequires(requires);
        }

        pluginDescriptor.setLicense(attributes.getValue(PLUGIN_LICENSE));

        return pluginDescriptor;
    }

    protected DefaultPluginDescriptor createPluginDescriptorInstance() {
        return new DefaultPluginDescriptor();
    }

}
