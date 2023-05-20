package com.redeheavy.heavycore.platform.bukkit.systems;

import com.redeheavy.heavycore.commons.logging.LoggerFactory;
import com.redeheavy.heavycore.commons.objects.HeavyPlugin;
import com.redeheavy.heavycore.platform.bukkit.BukkitLoader;
import lombok.val;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class AutoDownloadDependency {

    static final LoggerFactory logger = BukkitLoader.getBuildLogger();

    public static void download(HeavyPlugin heavyPlugin, String dependencyName, String URL, String outputName) {
        logger.normal("[" + heavyPlugin.getPluginName() + "]" + "Baixando dependência necessária: " + dependencyName + ". (0%)");

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
