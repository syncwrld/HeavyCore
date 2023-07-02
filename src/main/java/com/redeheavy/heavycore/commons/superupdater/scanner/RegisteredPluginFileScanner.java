package com.redeheavy.heavycore.commons.superupdater.scanner;

import com.redeheavy.heavycore.commons.Basement;
import com.redeheavy.heavycore.commons.logging.LoggerFactory;
import com.redeheavy.heavycore.commons.objects.HeavyPlugin;
import com.redeheavy.heavycore.commons.registry.RegistryMap;
import com.redeheavy.heavycore.commons.superupdater.model.PendingPlugin;
import com.redeheavy.heavycore.commons.superupdater.model.SuperUpdaterOptions;
import com.redeheavy.heavycore.exceptions.SuperUpdaterContradictionException;
import com.redeheavy.heavycore.platform.bukkit.BukkitLoader;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class RegisteredPluginFileScanner {

    static ArrayList<File> possibleFiles = new ArrayList<>();
    static final LoggerFactory logger = Basement.getLogger();

    private static boolean canUpdate = false;
    private static PendingPlugin pendingPlugin = null;
    private static List<SuperUpdaterOptions> options;
    private static String downloadURL;

    public RegisteredPluginFileScanner(SuperUpdaterOptions option, String updateURL) {
        options = Collections.singletonList(option);
        downloadURL = updateURL;
    }

    public RegisteredPluginFileScanner(List<SuperUpdaterOptions> superUpdaterOptions, String updateURL) {
        options = superUpdaterOptions;
        downloadURL = updateURL;
    }

    private void update(String builtVersion, File file) {
        if (!canUpdate)
            return;

        String r = System.getProperty("user.dir");
        String s = File.separator;

        logger.info("&b======================================= &6★");
        logger.info("Uma nova versão foi detectada para o plugin '" + pendingPlugin.getName() + "'!");
        logger.info("   &f| &cv" + pendingPlugin.getVersion() + " &f➞  &av" + builtVersion);
        logger.info("&6★ &b=======================================");

        logger.info("Tentando atualizar plugin...");

        try {
            URL url = new URL(downloadURL);
            InputStream in = url.openStream();
            Path path = new File(r + s + "plugins" + s, pendingPlugin.getName() + ".jar").toPath();

            if (options.contains(SuperUpdaterOptions.DELETE_OLD_JAR) && (options.contains(SuperUpdaterOptions.RENAME_OLD_JAR_AS_OLD)))
                throw new SuperUpdaterContradictionException();

            if (options.contains(SuperUpdaterOptions.DELETE_OLD_JAR)) {
                if (file.exists()) Files.delete(file.toPath());
                logger.success("Arquivo JAR antigo deletado com sucesso. (" + file.getName() + ")");
            } else if (options.contains(SuperUpdaterOptions.RENAME_OLD_JAR_AS_OLD)) {
                File renamedFile = new File(r + s + "plugins" + s, pendingPlugin.getName() + "-desatualizado.old");
                file.renameTo(renamedFile);
                logger.success("Arquivo JAR antigo marcado como '-desatualizado' na pasta plugins.");
            }

            Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
            logger.info("Novo arquivo JAR baixado! ➻  " + file.getName());
            logger.info("Descarregando JAR antiga e carregando a nova...");

            val mng = Bukkit.getPluginManager();
            val sch = Bukkit.getScheduler();
            val tgt = mng.getPlugin(pendingPlugin.getName());

            ScheduledExecutorService service = new ScheduledThreadPoolExecutor(2);
            service.schedule(
                    new Runnable() {
                        @Override
                        public void run() {
                            mng.disablePlugin(tgt);
                            try {
                                mng.loadPlugin(path.toFile());
                            } catch (InvalidPluginException | InvalidDescriptionException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
            , 5, TimeUnit.SECONDS);

            mng.disablePlugin(mng.getPlugin(pendingPlugin.getName()));
            mng.loadPlugin(path.toFile());

            logger.success("Atualização completa com sucesso!");

        } catch (SuperUpdaterContradictionException | IOException e) {
            e.printStackTrace();
        } catch (InvalidPluginException e) {
            throw new RuntimeException(e);
        } catch (InvalidDescriptionException e) {
            throw new RuntimeException(e);
        }
    }

    private void scan() {
        for (HeavyPlugin heavyPlugin : RegistryMap.getUpdateList()) {

            String hpName = heavyPlugin.getPluginName();

            for (File possible : possibleFiles) {

                try {
                    JarFile jarFile = new JarFile(possible);
                    Enumeration<JarEntry> entries = jarFile.entries();
                    JarEntry jarEntry;
                    InputStream stream = null;

                    while (entries.hasMoreElements()) {
                        jarEntry = entries.nextElement();
                        String entryName = jarEntry.getName().isEmpty() ? "not_empty_name.dynamo" : jarEntry.getName();

                        if (entryName.equals("plugin.yml")) {
                            stream = jarFile.getInputStream(jarEntry);
                        }
                    }

                    if (stream == null)
                        continue;

                    pendingPlugin = readProperties(possible, stream, hpName);

                    if (pendingPlugin.getName().equals(hpName)) {
                        String currentVersion = pendingPlugin.getVersion();
                        String builtVersion = heavyPlugin.getPluginVersion();

                        if (!(currentVersion.equals(builtVersion)))
                            canUpdate = true;

                        update(builtVersion, possible);

                        continue;
                    }

                } catch (IOException | RuntimeException e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }
    }

    public void load() {
        val r = System.getProperty("user.dir");
        val s = File.separator;

        File pluginPathFile = new File(r + s + "plugins");

        if (pluginPathFile.listFiles() == null) return;

        for (File file : Objects.requireNonNull(pluginPathFile.listFiles())) {
            if (file.getName().endsWith(".jar")) possibleFiles.add(file);
        }

        scan();
    }

    private PendingPlugin readProperties(File file, InputStream inputStream, String name) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bfReader = new BufferedReader(inputStreamReader);
        String line;

        String pl_name = null, version = null, description = null;

        try {
            while ((line = bfReader.readLine()) != null) {
                if (line.startsWith("name")) {
                    pl_name = line.replace("name: ", "".trim());
                } else if (line.startsWith("version")) {
                    version = line.replace("version: ", "".trim());
                } else if (line.startsWith("description")) {
                    description = line.replace("description: ", "".trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new PendingPlugin(pl_name, version, description, inputStream);
    }

}
