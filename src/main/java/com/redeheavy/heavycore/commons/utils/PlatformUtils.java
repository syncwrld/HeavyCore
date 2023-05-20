package com.redeheavy.heavycore.commons.utils;

import com.redeheavy.heavycore.commons.enums.Platform;

public class PlatformUtils {

    public static Platform getPlatform() {
        try {
            Class.forName("org.bukkit.plugin.java.JavaPlugin");
            return Platform.BUKKIT;
        } catch (ClassNotFoundException e) {
            try {
                Class.forName("net.md_5.bungee.api.plugin.Plugin");
                return Platform.BUNGEECORD;
            } catch (ClassNotFoundException ex) {
                try {
                    Class.forName("com.velocitypowered.api.plugin.Plugin");
                    return Platform.VELOCITY;
                } catch (ClassNotFoundException exc) {
                    try {
                        Class.forName("org.spongepowered.api.Application");
                        return Platform.SPONGE;
                    } catch (ClassNotFoundException exce) {
                        return Platform.UNKNOWN;
                    }
                }
            }
        }
    }

    public static void checkCompatibility() {

    }

}
