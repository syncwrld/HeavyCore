package com.redeheavy.heavycore.platform.velocity.utils;

import com.redeheavy.heavycore.commons.enums.Level;
import com.velocitypowered.api.proxy.ProxyServer;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;

public class VelocityLogger {

    @Setter
    @Getter
    public static String prefix = "&e[HeavyPlugins] ";
    @Setter
    static ProxyServer proxyServer;


    private static void sendMessage(String msg) {
        proxyServer.sendMessage(Component.text(replace(msg)));
    }

    public static void send(Level LogLevel, String message) {
        String levelPrefix;
        message = " &f" + message;
        if (LogLevel == Level.SECURITY) {
            levelPrefix = "&4[SEGURANÇA]";
            sendMessage(prefix + levelPrefix + message);
        } else if (LogLevel == Level.FATAL) {
            levelPrefix = "&4[FATAL]";
            sendMessage(prefix + levelPrefix + message);
        } else if (LogLevel == Level.SEVERE) {
            levelPrefix = "&4[SEVERO]";
            sendMessage(prefix + levelPrefix + message);
        } else if (LogLevel == Level.HIGH) {
            levelPrefix = "&4[IMPORTANTE]";
            sendMessage(prefix + levelPrefix + message);
        } else if (LogLevel == Level.SUCCESS) {
            levelPrefix = "&a[SUCESSO]";
            sendMessage(prefix + levelPrefix + message);
        } else if (LogLevel == Level.FAILED) {
            levelPrefix = "&c[FALHA]";
            sendMessage(prefix + levelPrefix + message);
        } else if (LogLevel == Level.NORMAL) {
            levelPrefix = "&b[NORMAL]";
            sendMessage(prefix + levelPrefix + message);
        } else if (LogLevel == Level.COMMON) {
            levelPrefix = "&e[COMUM]";
            sendMessage(prefix + levelPrefix + message);
        } else if (LogLevel == Level.ERROR) {
            levelPrefix = "&c[ERRO]";
            sendMessage(prefix + levelPrefix + message);
        } else if (LogLevel == Level.WARN) {
            levelPrefix = "&6[AVISO] ";
            sendMessage(prefix + levelPrefix + message);
        } else if (LogLevel == Level.INFO) {
            levelPrefix = "&a[INFO]";
            sendMessage(prefix + levelPrefix + message);
        } else if (LogLevel == Level.NO_PREFIX) {
            sendMessage(prefix + replace(message));
        } else {
            levelPrefix = "&4[?]";
            sendMessage(prefix + levelPrefix + message);
            sendMessage("Houve um erro ao usar o Logger: o nível do Logger não é reconhecido: \n" + LogLevel.name());
        }
    }

    private static String replace(String rawMessage) {
        return rawMessage.replace("&", "§");
    }

}
