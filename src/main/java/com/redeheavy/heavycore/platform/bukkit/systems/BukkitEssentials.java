package com.redeheavy.heavycore.platform.bukkit.systems;

import lombok.NonNull;
import lombok.val;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class BukkitEssentials {

    private final Plugin instantiator;

    public BukkitEssentials(@NonNull Plugin instantiator) {
        this.instantiator = instantiator;
    }


    public boolean checkDepend(String dependencyName) {
        val anotherManager = instantiator.getServer().getPluginManager();
        val dependency = anotherManager.getPlugin(dependencyName);
        return dependency != null;
    }

    public void createDefaultConfig() {
        File configuration = new File(instantiator.getDataFolder(), "config.yml");
        if (!configuration.exists()) instantiator.saveResource("config.yml", false);
    }


}
