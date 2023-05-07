package com.redeheavy.heavycore.loaders;

import com.redeheavy.heavycore.commons.enums.PlatformType;
import com.redeheavy.heavycore.commons.logging.LoggerFactory;
import com.redeheavy.heavycore.commons.objects.HeavyPlugin;
import com.redeheavy.heavycore.commons.registry.HeavyRegistry;
import com.redeheavy.heavycore.commons.updater.UpdateChecker;
import lombok.Getter;
import lombok.var;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class BukkitLoader extends JavaPlugin {

    @Getter
    public static Plugin plugin;

    @Getter
    static final LoggerFactory buildLogger = new LoggerFactory("&6[HeavyCore]");

    @Getter
    static UpdateChecker updateChecker;

    public void onEnable() {
        plugin = this;

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

    public static void load() {
        HeavyPlugin heavyLobby = new HeavyPlugin(
                "HeavyLobby",
                "com.redeheavy.lobby",
                PlatformType.BUKKIT,
                "v1.0",
                Arrays.asList("syncwrld", "AlbanoHouse")
        );

        new HeavyRegistry(heavyLobby).register();
    }

}
