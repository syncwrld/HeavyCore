package com.redeheavy.heavycore.platform.bukkit.utils;

import org.bukkit.Bukkit;

public class BukkitInformation {

    public static String getMinecraftVersion() {
        try {
            String info = Bukkit.getVersion();
            return info.split("MC: ")[1].split("\\)")[0];
        } catch (Throwable e) {
            return "Desconhecida";
        }
    }

    public static String getJarType() {
        try {
            String info = Bukkit.getVersion();
            return info.split("git-")[1].split("-")[0];
        } catch (Throwable e) {
            return "Desconhecida";
        }
    }

}
