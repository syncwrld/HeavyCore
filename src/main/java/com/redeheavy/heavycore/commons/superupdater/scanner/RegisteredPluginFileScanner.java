package com.redeheavy.heavycore.commons.superupdater.scanner;

import com.redeheavy.heavycore.commons.objects.HeavyPlugin;
import com.redeheavy.heavycore.commons.registry.RegistryMap;
import lombok.SneakyThrows;
import lombok.val;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class RegisteredPluginFileScanner extends RegistryMap {

    static ArrayList<File> possibleFiles = new ArrayList<>();

    public static void load() {
        val r = System.getProperty("user.dir");
        val s = File.separator;

        File pluginPathFile = new File(r + s + "plugins");
        Path pluginPath = pluginPathFile.toPath();

        if (pluginPathFile.listFiles() == null) return;

        for (File file : Objects.requireNonNull(pluginPathFile.listFiles())) {
            if (file.getName().endsWith(".jar")) possibleFiles.add(file);
        }

        scan();
    }

    @SneakyThrows
    static synchronized void scan() {

        for (HeavyPlugin heavyPlugin : getActivePlugins()) {
            for (File possible : possibleFiles) {
                JarFile jarFile = new JarFile(possible);
                Enumeration<JarEntry> entries = jarFile.entries();

                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    if (entry.getName().endsWith(".yml")) {
                        if (entry.getName().equals("plugin.yml")) {
                            InputStream inputStream = jarFile.getInputStream(entry);
                            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                            String nameAttribute = "name", requiredName = heavyPlugin.getPluginName();
                            String line;

                            while ((line = reader.readLine()) != null) {

                                if (line.trim().startsWith(nameAttribute + ":")) {

                                    String valor = line.trim().substring(nameAttribute.length() + 1).trim();

                                    if (valor.equals(requiredName)) {

                                        if (!possible.getName().equalsIgnoreCase(requiredName + ".jar")) {
                                            val newJarName = new File(requiredName + ".jar");
                                            possible.renameTo(newJarName);
                                        }

                                    }
                                }

                            }

                            jarFile.close();
                            reader.close();
                            inputStream.close();
                            break;
                        }
                    }
                }
            }
        }
    }

}
