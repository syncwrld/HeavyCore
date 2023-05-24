package com.redeheavy.heavycore.commons.logging;

import com.redeheavy.heavycore.commons.enums.LogTarget;
import com.redeheavy.heavycore.commons.logging.loggers.BukkitLogger;
import com.redeheavy.heavycore.commons.logging.loggers.BungeecordLogger;
import com.redeheavy.heavycore.commons.logging.loggers.VelocityLogger;
import com.redeheavy.heavycore.commons.objects.HeavyPlugin;
import lombok.NonNull;
import org.slf4j.Logger;

import java.util.LinkedHashMap;

public class LoggerFactory {

    static final Logger logger = org.slf4j.LoggerFactory.getLogger(LoggerFactory.class);
    static final LinkedHashMap<HeavyPlugin, String> map = new LinkedHashMap<>();
    static LogTarget logTarget;
    static HeavyPlugin plugin;

    public LoggerFactory(@NonNull HeavyPlugin heavyPlugin, @NonNull String prefix) {
        map.put(heavyPlugin, prefix);

        plugin = heavyPlugin;

        try {
            Class.forName("org.bukkit.plugin.java.JavaPlugin");
            logTarget = LogTarget.BUKKIT;
        } catch (ClassNotFoundException e) {
            try {
                Class.forName("net.md_5.bungee.api.plugin.Plugin");
                logTarget = LogTarget.BUNGEECORD;
            } catch (ClassNotFoundException ex) {
                try {
                    Class.forName("com.velocitypowered.api.plugin.Plugin");
                    logTarget = LogTarget.VELOCITY;
                } catch (ClassNotFoundException exc) {
                    logger.error("----------- FATAL ERROR [HeavyCore - by syncwrld] -----------");
                    logger.error("This plugin can just be loaded in 3 platforms: Bukkit (or forks), Velocity and Bungeecord (or forks too).");
                    logger.error("Run a supported version, running in Bukkit this plugin requires: minimum Java 8+ and version 1.8 +.");
                    logger.error("For Velocity compatibility this requires Java 17+ and build version 3.2.0+.");
                    logger.error("And if you wanna run a Bungeecord instance, requires: minimum Java 8+ and build 1.19-R0.1-SNAPSHOT +.");
                    logger.error("----------- UNSUPPORTED PLATFORM TYPE: UNKNOWN (?) ----------");
                    logTarget = LogTarget.UNKNOWN;
                }
            }
        }

    }

    public void debug(String text) {
        switch (logTarget) {
            case BUKKIT:
                new BukkitLogger(map.get(plugin)).debug(text);
                break;
            case BUNGEECORD:
                new BungeecordLogger(map.get(plugin)).debug(text);
                break;
            case VELOCITY:
                new VelocityLogger(map.get(plugin)).debug(text);
                break;
            default:
                logger.error("Wasn't possible to send a message in console because this platform unsupported.");
                break;
        }
    }

    public void info(String text) {
        switch (logTarget) {
            case BUKKIT:
                new BukkitLogger(map.get(plugin)).info(text);
                break;
            case BUNGEECORD:
                new BungeecordLogger(map.get(plugin)).info(text);
                break;
            case VELOCITY:
                new VelocityLogger(map.get(plugin)).info(text);
                break;
            default:
                logger.error("Wasn't possible to send a message in console because this platform unsupported.");
        }
    }

    public void common(String text) {
        switch (logTarget) {
            case BUKKIT:
                new BukkitLogger(map.get(plugin)).common(text);
                break;
            case BUNGEECORD:
                new BungeecordLogger(map.get(plugin)).common(text);
                break;
            case VELOCITY:
                new VelocityLogger(map.get(plugin)).common(text);
                break;
            default:
                logger.error("Wasn't possible to send a message in console because this platform unsupported.");
        }
    }

    public void normal(String text) {
        switch (logTarget) {
            case BUKKIT:
                new BukkitLogger(map.get(plugin)).normal(text);
                break;
            case BUNGEECORD:
                new BungeecordLogger(map.get(plugin)).normal(text);
                break;
            case VELOCITY:
                new VelocityLogger(map.get(plugin)).normal(text);
                break;
            default:
                logger.error("Wasn't possible to send a message in console because this platform unsupported.");
        }
    }

    public void warn(String text) {
        switch (logTarget) {
            case BUKKIT:
                new BukkitLogger(map.get(plugin)).warn(text);
                break;
            case BUNGEECORD:
                new BungeecordLogger(map.get(plugin)).warn(text);
                break;
            case VELOCITY:
                new VelocityLogger(map.get(plugin)).warn(text);
                break;
            default:
                logger.error("Wasn't possible to send a message in console because this platform unsupported.");
        }
    }

    public void error(String text) {
        switch (logTarget) {
            case BUKKIT:
                new BukkitLogger(map.get(plugin)).error(text);
                break;
            case BUNGEECORD:
                new BungeecordLogger(map.get(plugin)).error(text);
                break;
            case VELOCITY:
                new VelocityLogger(map.get(plugin)).error(text);
                break;
            default:
                logger.error("Wasn't possible to send a message in console because this platform unsupported.");
        }
    }

    public void severe(String text) {
        switch (logTarget) {
            case BUKKIT:
                new BukkitLogger(map.get(plugin)).severe(text);
                break;
            case BUNGEECORD:
                new BungeecordLogger(map.get(plugin)).severe(text);
                break;
            case VELOCITY:
                new VelocityLogger(map.get(plugin)).severe(text);
                break;
            default:
                logger.error("Wasn't possible to send a message in console because this platform unsupported.");
        }
    }

    public void success(String text) {
        switch (logTarget) {
            case BUKKIT:
                new BukkitLogger(map.get(plugin)).success(text);
                break;
            case BUNGEECORD:
                new BungeecordLogger(map.get(plugin)).success(text);
                break;
            case VELOCITY:
                new VelocityLogger(map.get(plugin)).success(text);
                break;
            default:
                logger.error("Wasn't possible to send a message in console because this platform unsupported.");
        }
    }

    public void failed(String text) {
        switch (logTarget) {
            case BUKKIT:
                new BukkitLogger(map.get(plugin)).failed(text);
                break;
            case BUNGEECORD:
                new BungeecordLogger(map.get(plugin)).failed(text);
                break;
            case VELOCITY:
                new VelocityLogger(map.get(plugin)).failed(text);
                break;
            default:
                logger.error("Wasn't possible to send a message in console because this platform unsupported.");
        }
    }

}
