package com.hwtx.pf4j;

import com.hwtx.pf4j.util.FileUtils;
import com.hwtx.pf4j.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Find a plugin descriptor in a properties file (in plugin repository).
 *
 * @author Decebal Suiu
 */
public class PropertiesPluginDescriptorFinder implements PluginDescriptorFinder {

    private static final Logger log = LoggerFactory.getLogger(PropertiesPluginDescriptorFinder.class);

    public static final String DEFAULT_PROPERTIES_FILE_NAME = "plugin.properties";

    public static final String PLUGIN_ID = "plugin.id";
    public static final String PLUGIN_DESCRIPTION = "plugin.description";
    public static final String PLUGIN_CLASS = "plugin.class";
    public static final String PLUGIN_VERSION = "plugin.version";
    public static final String PLUGIN_PROVIDER = "plugin.provider";
    public static final String PLUGIN_DEPENDENCIES = "plugin.dependencies";
    public static final String PLUGIN_REQUIRES = "plugin.requires";
    public static final String PLUGIN_LICENSE = "plugin.license";

    protected String propertiesFileName;

    public PropertiesPluginDescriptorFinder() {
        this(DEFAULT_PROPERTIES_FILE_NAME);
    }

    public PropertiesPluginDescriptorFinder(String propertiesFileName) {
        this.propertiesFileName = propertiesFileName;
    }

    @Override
    public boolean isApplicable(Path pluginPath) {
        return Files.exists(pluginPath) && (Files.isDirectory(pluginPath) || FileUtils.isJarFile(pluginPath));
    }

    @Override
    public PluginDescriptor find(Path pluginPath) {
        Properties properties = readProperties(pluginPath);

        return createPluginDescriptor(properties);
    }

    protected Properties readProperties(Path pluginPath) {
        Path propertiesPath = getPropertiesPath(pluginPath, propertiesFileName);
        if (propertiesPath == null) {
            throw new PluginRuntimeException("Cannot find the properties path");
        }

        log.debug("Lookup plugin descriptor in '{}'", propertiesPath);
        if (Files.notExists(propertiesPath)) {
            throw new PluginRuntimeException("Cannot find '{}' path", propertiesPath);
        }

        Properties properties = new Properties();
        try (InputStream input = Files.newInputStream(propertiesPath)) {
            properties.load(input);
        } catch (IOException e) {
            throw new PluginRuntimeException(e);
        }

        return properties;
    }

    protected Path getPropertiesPath(Path pluginPath, String propertiesFileName) {
        if (Files.isDirectory(pluginPath)) {
            return pluginPath.resolve(Paths.get(propertiesFileName));
        } else {
            // it's a jar file
            try {
                return FileUtils.getPath(pluginPath, propertiesFileName);
            } catch (IOException e) {
                throw new PluginRuntimeException(e);
            }
        }
    }

    protected PluginDescriptor createPluginDescriptor(Properties properties) {
        DefaultPluginDescriptor pluginDescriptor = createPluginDescriptorInstance();

        // TODO validate !!!
        String id = properties.getProperty(PLUGIN_ID);
        pluginDescriptor.setPluginId(id);

        String description = properties.getProperty(PLUGIN_DESCRIPTION);
        if (StringUtils.isNullOrEmpty(description)) {
            pluginDescriptor.setPluginDescription("");
        } else {
            pluginDescriptor.setPluginDescription(description);
        }

        String clazz = properties.getProperty(PLUGIN_CLASS);
        if (StringUtils.isNotNullOrEmpty(clazz)) {
            pluginDescriptor.setPluginClass(clazz);
        }

        String version = properties.getProperty(PLUGIN_VERSION);
        if (StringUtils.isNotNullOrEmpty(version)) {
            pluginDescriptor.setPluginVersion(version);
        }

        String provider = properties.getProperty(PLUGIN_PROVIDER);
        pluginDescriptor.setProvider(provider);

        String dependencies = properties.getProperty(PLUGIN_DEPENDENCIES);
        pluginDescriptor.setDependencies(dependencies);

        String requires = properties.getProperty(PLUGIN_REQUIRES);
        if (StringUtils.isNotNullOrEmpty(requires)) {
            pluginDescriptor.setRequires(requires);
        }

        pluginDescriptor.setLicense(properties.getProperty(PLUGIN_LICENSE));

        return pluginDescriptor;
    }

    protected DefaultPluginDescriptor createPluginDescriptorInstance() {
        return new DefaultPluginDescriptor();
    }

}
