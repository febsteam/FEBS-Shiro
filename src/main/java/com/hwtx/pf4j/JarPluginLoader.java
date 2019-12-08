package com.hwtx.pf4j;


import com.hwtx.pf4j.util.FileUtils;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Decebal Suiu
 */
public class JarPluginLoader implements PluginLoader {

    protected PluginManager pluginManager;

    public JarPluginLoader(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    @Override
    public boolean isApplicable(Path pluginPath) {
        return Files.exists(pluginPath) && FileUtils.isJarFile(pluginPath);
    }

    @Override
    public ClassLoader loadPlugin(Path pluginPath, PluginDescriptor pluginDescriptor) {
        PluginClassLoader pluginClassLoader = new PluginClassLoader(pluginManager, pluginDescriptor, getClass().getClassLoader());
        pluginClassLoader.addFile(pluginPath.toFile());

        return pluginClassLoader;
    }

}
