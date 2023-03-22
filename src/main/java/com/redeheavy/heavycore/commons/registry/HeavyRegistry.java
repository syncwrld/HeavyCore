package com.redeheavy.heavycore.commons.registry;

import com.redeheavy.heavycore.commons.enums.Level;
import com.redeheavy.heavycore.commons.enums.LoggerType;
import com.redeheavy.heavycore.commons.enums.PlatformType;
import com.redeheavy.heavycore.commons.objects.HeavyPlugin;
import com.redeheavy.heavycore.commons.systems.Logger;
public class HeavyRegistry {

    private final HeavyPlugin heavyPlugin;

    public HeavyRegistry(HeavyPlugin heavyPlugin) {
        this.heavyPlugin = heavyPlugin;
    }

    public void logRegistry() {

        if (heavyPlugin.getPlatformType() == PlatformType.BUKKIT) {
            Logger.send(Level.SUCCESS, LoggerType.BUKKIT, "Registrado plugin: [" +
                    heavyPlugin.getPluginName() + "] - versão " + heavyPlugin.getPluginVersion()
                    + " compatibilizado para " + heavyPlugin.getPlatformType()
            );
            return;
        }

    }

}
