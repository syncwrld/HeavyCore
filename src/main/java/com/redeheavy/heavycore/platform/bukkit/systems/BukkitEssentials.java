package com.redeheavy.heavycore.platform.bukkit.systems;

import com.redeheavy.heavycore.commons.objects.HeavyPlugin;
import lombok.NonNull;
import lombok.val;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileNotFoundException;

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

    public void downloadDependency(HeavyPlugin heavyPlugin, String dependencyName, String URL, String output_name) {
        AutoDownloadDependency.download(heavyPlugin, dependencyName, URL, output_name);
    }

    public void doCustomDirectory(String path) {
        CustomFileAndDirectory.doCustomDirectory(path);
    }

    public void saveToDirectory(String pathName, String resource, Plugin maker, ClassLoader classloader) throws FileNotFoundException {
        CustomFileAndDirectory.saveFileToCustomPath(pathName, resource, maker, classloader);
    }

}
