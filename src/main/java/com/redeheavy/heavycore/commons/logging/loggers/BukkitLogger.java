package com.redeheavy.heavycore.commons.logging.loggers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

public class BukkitLogger {

    static final ConsoleCommandSender log = Bukkit.getConsoleSender();
    static String loggerPrefix;

    public BukkitLogger(String prefix) {
        loggerPrefix = prefix;
    }

    public void debug(String message) {
        log.sendMessage(translate(loggerPrefix + " " + "&3[DEBUG] " + message));
    }

    public void info(String message) {
        log.sendMessage(translate(loggerPrefix + " " + "&b[INFO] &f" + message));
    }

    public void common(String message) {
        log.sendMessage(translate(loggerPrefix + " " + "&6[COMMON] " + message));
    }

    public void normal(String message) {
        log.sendMessage(translate(loggerPrefix + " " + "&a[NORMAL] " + message));
    }

    public void warn(String message) {
        log.sendMessage(translate(loggerPrefix + " " + "&e[WARN] " + message));
    }

    public void error(String message) {
        log.sendMessage(translate(loggerPrefix + " " + "&c[ERROR] " + message));
    }

    public void severe(String message) {
        log.sendMessage(translate(loggerPrefix + " " + "&4[SEVERE] " + message));
    }

    public void success(String message) {
        log.sendMessage(translate(loggerPrefix + " " + "&a[SUCCESS] " + message));
    }

    public void failed(String message) {
        log.sendMessage(translate(loggerPrefix + " " + "&c[FAIL] " + message));
    }

    String translate(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
