package com.redeheavy.heavycore.loaders;

import com.redeheavy.heavycore.commons.logging.LoggerFactory;
import lombok.Getter;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitLoader extends JavaPlugin {

    @Getter
    public static Plugin plugin;

    @Getter
    static final LoggerFactory buildLogger = new LoggerFactory("&6[HeavyCore]");

    public void onEnable() {
        plugin = this;
    }

}
