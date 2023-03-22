package com.redeheavy.heavycore.platform.bungeecord.systems;

import com.google.common.io.ByteStreams;
import com.redeheavy.heavycore.commons.enums.Level;
import com.redeheavy.heavycore.commons.enums.LoggerType;
import com.redeheavy.heavycore.platform.bungeecord.utils.BungeeLogger;
import lombok.NonNull;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.*;

public class BungeeEssentials {

    private static Plugin instantiator;

    public BungeeEssentials(@NonNull Plugin instanciator) {
        instantiator = instanciator;
    }

    public File essentialConfig(String name) {
        File folder = instantiator.getDataFolder();
        if (!folder.exists()) folder.mkdir();
        File resourceFile = new File(folder, name + ".yml");
        try {
            if (!resourceFile.exists()) {
                resourceFile.createNewFile();
                try (InputStream in = instantiator.getResourceAsStream(name + ".yml");
                     OutputStream out = new FileOutputStream(resourceFile)) {
                    ByteStreams.copy(in, out);
                }
            }
        } catch (Exception e) {
            BungeeLogger.send(Level.ERROR, "Houve um erro ao criar um dos arquivos de configuração.");
            e.printStackTrace();
        }
        return resourceFile;
    }

    public Configuration getConfig(String file) {
        try {
            return ConfigurationProvider.getProvider(YamlConfiguration.class).load(essentialConfig(file));
        } catch (IOException e) {
            BungeeLogger.send(Level.ERROR, "Houve um erro ao carregar ums dos arquivos de configuração: " + file + ".yml para o plugin: " + instantiator.getDescription().getName() + ".");
        }
        return null;
    }

}
