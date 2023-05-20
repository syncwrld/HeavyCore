package com.redeheavy.heavycore.commons.logging.loggers;

import com.redeheavy.heavycore.platform.velocity.VelocityLoader;
import com.velocitypowered.api.proxy.ConsoleCommandSource;
import net.kyori.adventure.text.Component;

public class VelocityLogger {

    static final ConsoleCommandSource log = VelocityLoader.getServer().getConsoleCommandSource();
    static String loggerPrefix;

    public VelocityLogger(String prefix) {
        loggerPrefix = prefix;
    }

    public void debug(String message) {
        log.sendMessage(Component.text(loggerPrefix + " " + "&b[DEBUG] &f" + message));
    }

    public void info(String message) {
        log.sendMessage(Component.text(translate(loggerPrefix + " " + "&B[INFO] &f" + message)));
    }

    public void common(String message) {
        log.sendMessage(Component.text(translate(loggerPrefix + " " + "&6[COMMON] " + message)));
    }

    public void normal(String message) {
        log.sendMessage(Component.text(translate(loggerPrefix + " " + "&a[NORMAL] " + message)));
    }

    public void warn(String message) {
        log.sendMessage(Component.text(translate(loggerPrefix + " " + "&e[WARN] " + message)));
    }

    public void error(String message) {
        log.sendMessage(Component.text(translate(loggerPrefix + " " + "&c[ERROR] " + message)));
    }

    public void severe(String message) {
        log.sendMessage(Component.text(translate(loggerPrefix + " " + "&4[SEVERE] " + message)));
    }

    public void success(String message) {
        log.sendMessage(Component.text(translate(loggerPrefix + " " + "&a[SUCCESS] " + message)));
    }

    public void failed(String message) {
        log.sendMessage(Component.text(translate(loggerPrefix + " " + "&c[FAIL] " + message)));
    }

    String translate(String text) {
        return text.replace("&", "§");
    }

}
