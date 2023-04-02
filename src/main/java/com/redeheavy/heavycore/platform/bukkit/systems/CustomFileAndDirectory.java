package com.redeheavy.heavycore.platform.bukkit.systems;

import lombok.NonNull;
import lombok.val;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

public class CustomFileAndDirectory {

    public static void doCustomDirectory(@NonNull String name) {
        val root = System.getProperty("user.dir");
        File file = new File(root + File.separator + "plugins" + File.separator + name);
        if (!file.exists()) file.mkdirs();
    }

    public static void saveFileToCustomPath(@NonNull String pathName, @NonNull String resource, @NonNull Plugin maker, @NonNull ClassLoader classLoader) throws FileNotFoundException {

        val stream = classLoader.getResourceAsStream(resource);

        if (stream == null) {
            throw new FileNotFoundException("[HeavyCore] Arquivo " + resource + " não encontrado no plugin: " + maker.getName());
        }

        val rootDirectory = System.getProperty("user.dir");
        val customPath = new File(rootDirectory + File.separator + "plugins" + File.separator + pathName);

        if (!customPath.exists()) customPath.mkdirs();
        File targetFile = new File(customPath, resource);

        if (!targetFile.exists()) {
            try {
                Files.copy(stream, targetFile.toPath());
            } catch (IOException e) {
                throw new RuntimeException("[HeavyCore] Erro ao copiar arquivo " + resource + ": " + e);
            }
        }

    }

}
