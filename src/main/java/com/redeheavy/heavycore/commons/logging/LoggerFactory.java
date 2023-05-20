package com.redeheavy.heavycore.commons.logging;

import com.redeheavy.heavycore.commons.enums.LogTarget;
import com.redeheavy.heavycore.commons.logging.loggers.BukkitLogger;
import com.redeheavy.heavycore.commons.logging.loggers.BungeecordLogger;
import com.redeheavy.heavycore.commons.logging.loggers.VelocityLogger;
import lombok.NonNull;
import org.slf4j.Logger;

public class LoggerFactory {

    static final Logger logger = org.slf4j.LoggerFactory.getLogger(LoggerFactory.class);
    static LogTarget logTarget;
    static String logPrefix;

    public LoggerFactory(@NonNull String prefix) {

        logPrefix = prefix;

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
        if (logTarget == LogTarget.BUKKIT) {
            new BukkitLogger(logPrefix).debug(text);
        } else if (logTarget == LogTarget.BUNGEECORD) {
            new BungeecordLogger(logPrefix).debug(text);
        } else {
            logger.error("Wasn't possible to send a message in console because this platform unsupported.");
        }
    }

    public void info(String text) {
        if (logTarget == LogTarget.BUKKIT) {
            new BukkitLogger(logPrefix).info(text);
        } else if (logTarget == LogTarget.BUNGEECORD) {
            new BungeecordLogger(logPrefix).info(text);
        } else if (logTarget == LogTarget.VELOCITY) {
            new VelocityLogger(logPrefix).info(text);
        } else {
            logger.error("Wasn't possible to send a message in console because this platform unsupported.");
        }
    }

    public void common(String text) {
        if (logTarget == LogTarget.BUKKIT) {
            new BukkitLogger(logPrefix).common(text);
        } else if (logTarget == LogTarget.BUNGEECORD) {
            new BungeecordLogger(logPrefix).common(text);
        } else if (logTarget == LogTarget.VELOCITY) {
            new VelocityLogger(logPrefix).info(text);
        } else {
            logger.error("Wasn't possible to send a message in console because this platform unsupported.");
        }
    }

    public void normal(String text) {
        if (logTarget == LogTarget.BUKKIT) {
            new BukkitLogger(logPrefix).normal(text);
        } else if (logTarget == LogTarget.BUNGEECORD) {
            new BungeecordLogger(logPrefix).normal(text);
        } else if (logTarget == LogTarget.VELOCITY) {
            new VelocityLogger(logPrefix).info(text);
        } else {
            logger.error("Wasn't possible to send a message in console because this platform unsupported.");
        }
    }

    public void warn(String text) {
        if (logTarget == LogTarget.BUKKIT) {
            new BukkitLogger(logPrefix).warn(text);
        } else if (logTarget == LogTarget.BUNGEECORD) {
            new BungeecordLogger(logPrefix).warn(text);
        } else if (logTarget == LogTarget.VELOCITY) {
            new VelocityLogger(logPrefix).info(text);
        } else {
            logger.error("Wasn't possible to send a message in console because this platform unsupported.");
        }
    }

    public void error(String text) {
        if (logTarget == LogTarget.BUKKIT) {
            new BukkitLogger(logPrefix).error(text);
        } else if (logTarget == LogTarget.BUNGEECORD) {
            new BungeecordLogger(logPrefix).error(text);
        } else if (logTarget == LogTarget.VELOCITY) {
            new VelocityLogger(logPrefix).info(text);
        } else {
            logger.error("Wasn't possible to send a message in console because this platform unsupported.");
        }
    }

    public void severe(String text) {
        if (logTarget == LogTarget.BUKKIT) {
            new BukkitLogger(logPrefix).severe(text);
        } else if (logTarget == LogTarget.BUNGEECORD) {
            new BungeecordLogger(logPrefix).severe(text);
        } else if (logTarget == LogTarget.VELOCITY) {
            new VelocityLogger(logPrefix).info(text);
        } else {
            logger.error("Wasn't possible to send a message in console because this platform unsupported.");
        }
    }

    public void success(String text) {
        if (logTarget == LogTarget.BUKKIT) {
            new BukkitLogger(logPrefix).success(text);
        } else if (logTarget == LogTarget.BUNGEECORD) {
            new BungeecordLogger(logPrefix).success(text);
        } else if (logTarget == LogTarget.VELOCITY) {
            new VelocityLogger(logPrefix).info(text);
        } else {
            logger.error("Wasn't possible to send a message in console because this platform unsupported.");
        }
    }

    public void failed(String text) {
        if (logTarget == LogTarget.BUKKIT) {
            new BukkitLogger(logPrefix).failed(text);
        } else if (logTarget == LogTarget.BUNGEECORD) {
            new BungeecordLogger(logPrefix).failed(text);
        } else if (logTarget == LogTarget.VELOCITY) {
            new VelocityLogger(logPrefix).info(text);
        } else {
            logger.error("Wasn't possible to send a message in console because this platform unsupported.");
        }
    }

}
