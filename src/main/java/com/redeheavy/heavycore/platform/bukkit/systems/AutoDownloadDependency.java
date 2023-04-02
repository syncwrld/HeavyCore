package com.redeheavy.heavycore.platform.bukkit.systems;

import com.redeheavy.heavycore.commons.enums.Level;
import com.redeheavy.heavycore.commons.objects.HeavyPlugin;
import com.redeheavy.heavycore.platform.bukkit.utils.BukkitLogger;
import lombok.val;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class AutoDownloadDependency {

    public static void download(HeavyPlugin heavyPlugin, String dependencyName, String URL, String outputName) {
        BukkitLogger.setPrefix("&6[HeavyCore] [" + heavyPlugin.getPluginName() + "] ");

        BukkitLogger.send(Level.NORMAL, "Baixando dependência necessária: " + dependencyName + ". (0%)");

        val r = System.getProperty("user.dir");
        val s = File.separator;

        try {
            InputStream in = new URL(URL).openStream();
            Path path = new File(r + s + "plugins" + s, outputName).toPath();
            Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);

            BukkitLogger.send(Level.SUCCESS, "Dependência instalada! (" + dependencyName + ")");
        } catch (IOException e) {
            BukkitLogger.send(Level.FAILED, "A URL provida pelo plugin " + heavyPlugin.getPluginName() + " para realizar o download da dependência é inválida.");
            e.printStackTrace();
        }
    }
}
