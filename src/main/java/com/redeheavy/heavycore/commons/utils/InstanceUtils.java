package com.redeheavy.heavycore.commons.utils;

import com.redeheavy.heavycore.commons.enums.Platform;

public class InstanceUtils {

    public static Platform getPlatform() {
        try {
            Class.forName("org.bukkit.plugin.java.JavaPlugin");
            return Platform.BUKKIT;
        } catch (ClassNotFoundException e) {
            try {
                Class.forName("net.md_5.bungee.api.plugin.Plugin");
                return Platform.BUNGEECORD;
            } catch (ClassNotFoundException ignored) {
                try {
                    Class.forName("org.spongepowered.api.Application");
                    return Platform.SPONGE;
                } catch (ClassNotFoundException ex) {
                    return Platform.UNKNOWN;
                }
            }
        }
    }

}
