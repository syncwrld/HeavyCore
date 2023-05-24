package com.redeheavy.heavycore.commons.superupdater.scanner;

import com.redeheavy.heavycore.commons.objects.HeavyPlugin;
import com.redeheavy.heavycore.commons.registry.RegistryMap;
import lombok.val;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;
import java.util.jar.JarInputStream;

public class RegisteredPluginFileScanner extends RegistryMap {

    static ArrayList<File> possibleFiles = new ArrayList<>();

    static synchronized void scan() {

        val r = System.getProperty("user.dir");
        val s = File.separator;
        File pluginPathFile = new File(r + s + "plugins");

        for (HeavyPlugin heavyPlugin : getActivePlugins()) {
            for (File possible : possibleFiles) {

                Class<?> pluginMain = null;
                try {
                    pluginMain = Class.forName(heavyPlugin.getMainDirectory());
                } catch (ClassNotFoundException ignored) {
                    continue;
                }

                try {
                    InputStream jarNativeStream = Files.newInputStream(possible.toPath());
                    JarInputStream jarInputStream = new JarInputStream(jarNativeStream);

                    while (jarInputStream.getNextEntry() != null) {
                        val entry = jarInputStream.getNextEntry();
                        if (entry.getName().equalsIgnoreCase("plugin.yml")) {
                            System.out.println("Plugin.yml encontrada: " + entry);
                            break;
                        }
                    }

                    System.out.println("Possível arquivo: " + possible.getName() + " - Objeto: " + heavyPlugin);

                    /*
                    InputStream inputStream = pluginMain.getResourceAsStream("plugin.yml");

                    if (inputStream == null) {
                        System.out.println("Parei por aqui.");
                        return;
                    }

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                    String nameAttribute = "name", requiredName = heavyPlugin.getPluginName(), line;

                    Yaml yaml = new Yaml();
                    Map<String, Object> dados = yaml.load(inputStream);

                    String name = (String) dados.get("name");

                    System.out.println("Nome do plugin na plugin.yml: " + name);

                    if (!(name.equalsIgnoreCase(heavyPlugin.getPluginName()))) {
                        System.out.println("Possível arquivo descartado: " + possible.getName());
                        continue;
                    }

                    if (name.equals(requiredName)) {

                        if (!possible.getName().equalsIgnoreCase(requiredName + ".jar")) {
                            val newJarName = new File(pluginPathFile + s + requiredName + ".jar");
                            possible.renameTo(newJarName);
                        }

                    }

                    reader.close();
                    inputStream.close();
                    */
                } catch (IOException ignored) {
                }
            }
        }
    }

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
}
