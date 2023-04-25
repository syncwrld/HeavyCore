package com.redeheavy.heavycore.commons.registry;

import com.redeheavy.heavycore.commons.logging.LoggerFactory;
import com.redeheavy.heavycore.commons.objects.HeavyPlugin;
import com.redeheavy.heavycore.loaders.BukkitLoader;

public class HeavyRegistry {

    static final LoggerFactory logger = BukkitLoader.getBuildLogger();
    private final HeavyPlugin heavyPlugin;

    public HeavyRegistry(HeavyPlugin heavyPlugin) {
        this.heavyPlugin = heavyPlugin;

        logger.success("Registrado plugin: [" +
                heavyPlugin.getPluginName() + "] - versão " + heavyPlugin.getPluginVersion()
                + " by " + heavyPlugin.getAuthors()
        );
    }

}
