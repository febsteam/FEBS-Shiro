package com.hwtx.pf4j;

import com.hwtx.pf4j.util.JarFileFilter;

import java.nio.file.Path;

/**
 * @author Decebal Suiu
 */
public class JarPluginRepository extends BasePluginRepository {

    public JarPluginRepository(Path pluginsRoot) {
        super(pluginsRoot, new JarFileFilter());
    }

}
