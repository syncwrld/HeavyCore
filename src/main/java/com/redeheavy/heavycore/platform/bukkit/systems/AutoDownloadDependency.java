package com.redeheavy.heavycore.platform.bukkit.systems;

import com.redeheavy.heavycore.commons.enums.Level;
import com.redeheavy.heavycore.commons.objects.HeavyPlugin;
import com.redeheavy.heavycore.platform.bukkit.utils.BukkitLogger;
import org.bukkit.plugin.Plugin;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class AutoDownloadDependency {

    public static void download(HeavyPlugin heavyPlugin, String dependencyName, String URL, String outputName) {
        BukkitLogger.setPrefix("&6[HeavyCore] [" + heavyPlugin.getPluginName() + "] ");

        BukkitLogger.send(Level.NORMAL, "Baixando dependência necessária: " + dependencyName + ". (0%)");

        try {
            InputStream in = new URL(URL).openStream();
            Path path = new File("/home/container/plugins/", outputName).toPath();
            Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);

            BukkitLogger.send(Level.SUCCESS, "Dependência instalada! (" + dependencyName + ")");
        } catch (IOException e) {
            BukkitLogger.send(Level.FAILED, "A URL provida pelo plugin " + heavyPlugin.getPluginName() + " para realizar o download da dependência é inválida.");
            e.printStackTrace();
        }
    }
}
