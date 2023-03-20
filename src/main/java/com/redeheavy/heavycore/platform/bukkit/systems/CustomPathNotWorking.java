package com.redeheavy.heavycore.platform.bukkit.systems;

import lombok.NonNull;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class CustomPathNotWorking {

    protected static void doCustomDirectory(@NonNull String name) {
        File file = new File("../plugins/" + name);
        if (!file.exists()) file.mkdirs();
    }

    protected static void saveFileToCustomPath(@NonNull String path, @NonNull String resource, @NonNull Plugin maker, @NonNull ClassLoader classLoader) throws IOException {

        URL url = classLoader.getResource(resource);

        if (url == null) {
            throw new FileNotFoundException("[HeavyCore] Arquivo " + resource + " não encontrado no plugin: " + maker.getName());
        }

        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
        JarFile jarFile = jarURLConnection.getJarFile();
        JarEntry jarEntry = jarFile.getJarEntry(resource);

        ClassLoader nativeCL = CustomPathNotWorking.class.getClassLoader().getParent();

        File customPath = new File(path);
        File targetFile = new File(customPath, resource);

        if (!customPath.exists()) customPath.mkdirs();
        if (!targetFile.exists()) targetFile.mkdirs();

        Path targetPath = new File(customPath, resource).toPath();
        Path realPath = new File(maker.getDataFolder(), resource).toPath();

        try {
            maker.saveResource(resource, true);
            Files.copy(realPath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            Files.deleteIfExists(realPath);
        } catch (IOException e) {
            throw new RuntimeException("[HeavyCore] Houve um erro ao criar um arquivo personalizado: " + e);
        }
    }

}
