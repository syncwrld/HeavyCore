package com.redeheavy.heavycore.loaders;

import lombok.Getter;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitLoader extends JavaPlugin {

    @Getter public static Plugin plugin;

    public void onEnable() {
        plugin = this;
    }

}
