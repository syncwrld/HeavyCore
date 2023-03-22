package com.redeheavy.heavycore.platform.bukkit.systems;

import com.redeheavy.heavycore.commons.enums.Level;
import com.redeheavy.heavycore.commons.enums.LoggerType;
import com.redeheavy.heavycore.commons.systems.Logger;
import com.redeheavy.heavycore.loaders.BukkitLoader;
import lombok.NonNull;
import lombok.val;
import org.bukkit.plugin.Plugin;

public class BukkitEssentials {

    private static Plugin instantiator;

    public BukkitEssentials(@NonNull Plugin instanciator) {
        instantiator = instanciator;
    }


    public void checkDepend(String dependencyName) {
        if (!instantiator.isEnabled()) return;
        val anotherManager = instantiator.getServer().getPluginManager();
        val dependency = anotherManager.getPlugin(dependencyName);
        if (dependency == null) {
            Logger.setPrefix("&6[HeavyCore] ");

            Logger.send(Level.FATAL, LoggerType.BUKKIT, "Dependência não encontrada para o plugin " + instantiator.getName() + ": " + dependencyName + ".");

            while (instantiator.isEnabled()) {
                Logger.send(Level.INFO, LoggerType.BUKKIT, "O plugin " + instantiator.getName() + " foi desligado por falta de dependências.");
                BukkitLoader.getPlugin().getServer().getPluginManager().disablePlugin(instantiator);
            }
        }
    }

}
