package com.redeheavy.heavycore.platform.bukkit.systems;

import com.redeheavy.heavycore.commons.enums.Level;
import com.redeheavy.heavycore.commons.enums.LoggerType;
import com.redeheavy.heavycore.loaders.BukkitLoader;
import com.redeheavy.heavycore.platform.bukkit.utils.BukkitLogger;
import lombok.NonNull;
import lombok.val;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
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


}
