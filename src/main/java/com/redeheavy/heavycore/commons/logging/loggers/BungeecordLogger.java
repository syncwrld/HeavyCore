package com.redeheavy.heavycore.commons.logging.loggers;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;

public class BungeecordLogger {

    static final CommandSender log = BungeeCord.getInstance().getConsole();
    static String loggerPrefix;

    public BungeecordLogger(String prefix) {
        loggerPrefix = prefix;
    }

    public void debug(String message) {
        log.sendMessage(new TextComponent(translate(loggerPrefix + " " + "&3[DEBUG] " + message)));
    }

    public void info(String message) {
        log.sendMessage(new TextComponent(translate(loggerPrefix + " " + "&B[INFO] &f" + message)));
    }

    public void common(String message) {
        log.sendMessage(new TextComponent(translate(loggerPrefix + " " + "&6[COMMON] " + message)));
    }

    public void normal(String message) {
        log.sendMessage(new TextComponent(translate(loggerPrefix + " " + "&a[NORMAL] " + message)));
    }

    public void warn(String message) {
        log.sendMessage(new TextComponent(translate(loggerPrefix + " " + "&e[WARN] " + message)));
    }

    public void error(String message) {
        log.sendMessage(new TextComponent(translate(loggerPrefix + " " + "&c[ERROR] " + message)));
    }

    public void severe(String message) {
        log.sendMessage(new TextComponent(translate(loggerPrefix + " " + "&4[SEVERE] " + message)));
    }

    public void success(String message) {
        log.sendMessage(new TextComponent(translate(loggerPrefix + " " + "&a[SUCCESS] " + message)));
    }

    public void failed(String message) {
        log.sendMessage(new TextComponent(translate(loggerPrefix + " " + "&c[FAIL] " + message)));
    }

    String translate(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
