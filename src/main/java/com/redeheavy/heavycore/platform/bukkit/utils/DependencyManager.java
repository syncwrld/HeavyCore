package com.redeheavy.heavycore.platform.bukkit.utils;

import com.redeheavy.heavycore.commons.logging.LoggerFactory;
import com.redeheavy.heavycore.commons.objects.HeavyPlugin;
import com.redeheavy.heavycore.platform.bukkit.BukkitLoader;
import lombok.val;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class DependencyManager {

    static final LoggerFactory logger = BukkitLoader.getBuildLogger();

    public boolean isPresent(String dependencyName) {
        val plugin = Bukkit.getPluginManager().getPlugin(dependencyName);
        return plugin != null;
    }

    public void download(HeavyPlugin heavyPlugin, String dependencyName, String URL, String outputName) {
        logger.normal("Baixando dependência necessária: " + dependencyName + ". (0%)");

        val r = System.getProperty("user.dir");
        val s = File.separator;

        try {
            InputStream in = new URL(URL).openStream();
            Path path = new File(r + s + "plugins" + s, outputName).toPath();
            Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);

            logger.success("Dependência instalada! (" + dependencyName + ")");
        } catch (IOException e) {
            logger.failed("A URL provida pelo plugin " + heavyPlugin.getPluginName() + " para realizar o download da dependência é inválida.");
            e.printStackTrace();
        }
    }
}