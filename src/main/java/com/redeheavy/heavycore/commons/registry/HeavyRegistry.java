package com.redeheavy.heavycore.commons.registry;

import com.redeheavy.heavycore.commons.enums.Level;
import com.redeheavy.heavycore.commons.enums.PlatformType;
import com.redeheavy.heavycore.commons.objects.HeavyPlugin;
import com.redeheavy.heavycore.platform.bukkit.utils.BukkitLogger;
import com.redeheavy.heavycore.platform.bungeecord.utils.BungeeLogger;
import com.redeheavy.heavycore.platform.velocity.utils.VelocityLogger;

public class HeavyRegistry {

    private final HeavyPlugin heavyPlugin;

    public HeavyRegistry(HeavyPlugin heavyPlugin) {
        this.heavyPlugin = heavyPlugin;
    }

    public void logRegistry() {

        if (heavyPlugin.getPlatformType() == PlatformType.BUKKIT) {
            BukkitLogger.send(Level.SUCCESS, "Registrado plugin: [" +
                    heavyPlugin.getPluginName() + "] - versão " + heavyPlugin.getPluginVersion()
                    + " by " + heavyPlugin.getAuthors()
            );
        } else if (heavyPlugin.getPlatformType() == PlatformType.BUNGEECORD) {
            BungeeLogger.send(Level.SUCCESS, "Registrado plugin: [" +
                    heavyPlugin.getPluginName() + "] - versão " + heavyPlugin.getPluginVersion()
                    + " by " + heavyPlugin.getAuthors()
            );
        } else if (heavyPlugin.getPlatformType() == PlatformType.VELOCITY) {
            VelocityLogger.send(Level.SUCCESS, "Registrado plugin: [" +
                    heavyPlugin.getPluginName() + "] - versão " + heavyPlugin.getPluginVersion()
                    + " by " + heavyPlugin.getAuthors()
            );
        }

    }

}
