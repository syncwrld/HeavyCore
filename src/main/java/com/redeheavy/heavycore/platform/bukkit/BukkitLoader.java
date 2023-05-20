package com.redeheavy.heavycore.platform.bukkit;

import com.redeheavy.heavycore.commons.logging.LoggerFactory;
import com.redeheavy.heavycore.commons.updater.UpdateChecker;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.var;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Bukkit.shutdown;

public class BukkitLoader extends JavaPlugin {

    @Getter
    static final LoggerFactory buildLogger = new LoggerFactory("&6[HeavyCore]");
    @Getter
    public static Plugin plugin;
    @Getter
    static UpdateChecker updateChecker;

    @SneakyThrows
    public static void checkSpigot() {
        try {
            Class.forName("org.spigotmc.SpigotConfig");
        } catch (ClassNotFoundException e) {
            buildLogger.error("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            buildLogger.error("Erro: Plataforma não suportada.");
            buildLogger.error("O HeavyCore não tem suporte para servidores CraftBukkit puro.");
            buildLogger.error("É necessário Spigot ou alguma fork, por exemplo o Paper (Recomendado).");
            buildLogger.error("Link do PaperSpigot: https://papermc.io/software/paper");
            buildLogger.error("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            buildLogger.severe("O servidor será desligado por falta de compatibilidade.");

            Thread.sleep(9000);
            shutdown();
        }
    }

    public void onEnable() {
        plugin = this;

        checkSpigot();

        updateChecker = new UpdateChecker(plugin, "syncwrld", "HeavyCore");
        update();
    }

    void update() {
        buildLogger.common("Checando atualizações...");
        updateChecker.check();
        if (updateChecker.canUpdate()) {

            var updateLink = updateChecker.getLastRelease().getDownloadURL() == null ? "https://github.com/syncwrld/HeavyCore/releases/latest" : updateChecker.getLastRelease().getDownloadURL();

            buildLogger.info("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            buildLogger.info("&cVocê não está utilizando a última versão do plugin!");
            buildLogger.info("Atualize para a nova versão utilizando o link abaixo.");
            buildLogger.info("Link para update: &b" + updateLink + "");
            buildLogger.info("Atualização &e" + updateChecker.getCurrentVersion() + " &f➞  &b" + updateChecker.getLastRelease().getVersion() + " &f(Nova versão).");
            buildLogger.info("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        } else {
            buildLogger.info("&aGG! &bVocê está utilizando a última versão do Core.");
        }
    }

}