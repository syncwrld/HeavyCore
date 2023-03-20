package com.redeheavy.heavycore.commons.systems;

import com.redeheavy.heavycore.commons.enums.Level;
import com.redeheavy.heavycore.commons.enums.LoggerType;
import com.velocitypowered.api.proxy.ProxyServer;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;

public class Logger {

    @Setter
    @Getter
    public static String prefix = "&e[HeavyPlugins] ";
    @Setter static ProxyServer ps;

    private static String translate(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    private static void sendMessage(LoggerType loggerType, String msg, ProxyServer instance) {
        if (loggerType == LoggerType.BUKKIT) {
            Bukkit.getConsoleSender().sendMessage(translate(msg));
        } else if (loggerType == LoggerType.BUNGEECORD) {
            BungeeCord.getInstance().getConsole().sendMessage(new ComponentBuilder(translate(msg)).create());
        } else if (loggerType == LoggerType.VELOCITY) {
            ps = instance;
            instance.getConsoleCommandSource().sendMessage(Component.text(replace(msg)));
        } else {
            throw new RuntimeException("O modo escolhido para o Logger ainda não foi concluído, escolhido: " + loggerType.name());
        }
    }

    public static void send(Level LogLevel, LoggerType logMode, String message) {
        String levelPrefix;
        message = " &f" + message;
        if (LogLevel == Level.SECURITY) {
            levelPrefix = "&4[SEGURANÇA]";
            if (logMode == LoggerType.VELOCITY) {
                sendMessage(logMode, prefix + levelPrefix + replace(message), ps);
                return;
            }
            sendMessage(logMode, prefix + levelPrefix + message, null);
        } else if (LogLevel == Level.FATAL) {
            levelPrefix = "&4[FATAL]";
            if (logMode == LoggerType.VELOCITY) {
                sendMessage(logMode, prefix + levelPrefix + replace(message), ps);
                return;
            }
            sendMessage(logMode, prefix + levelPrefix + message, null);
        } else if (LogLevel == Level.SEVERE) {
            levelPrefix = "&4[SEVERO]";
            if (logMode == LoggerType.VELOCITY) {
                sendMessage(logMode, prefix + levelPrefix + replace(message), ps);
                return;
            }
            sendMessage(logMode, prefix + levelPrefix + message, null);
        } else if (LogLevel == Level.HIGH) {
            levelPrefix = "&4[IMPORTANTE]";
            if (logMode == LoggerType.VELOCITY) {
                sendMessage(logMode, prefix + levelPrefix + replace(message), ps);
                return;
            }
            sendMessage(logMode, prefix + levelPrefix + message, null);
        } else if (LogLevel == Level.SUCCESS) {
            levelPrefix = "&a[SUCESSO]";
            if (logMode == LoggerType.VELOCITY) {
                sendMessage(logMode, prefix + levelPrefix + replace(message), ps);
                return;
            }
            sendMessage(logMode, prefix + levelPrefix + message, null);
        } else if (LogLevel == Level.FAILED) {
            levelPrefix = "&c[FALHA]";
            if (logMode == LoggerType.VELOCITY) {
                sendMessage(logMode, prefix + levelPrefix + replace(message), ps);
                return;
            }
            sendMessage(logMode, prefix + levelPrefix + message, null);
        } else if (LogLevel == Level.NORMAL) {
            levelPrefix = "&b[NORMAL]";
            if (logMode == LoggerType.VELOCITY) {
                sendMessage(logMode, prefix + levelPrefix + replace(message), ps);
                return;
            }
            sendMessage(logMode, prefix + levelPrefix + message, null);
        } else if (LogLevel == Level.COMMON) {
            levelPrefix = "&e[COMUM]";
            if (logMode == LoggerType.VELOCITY) {
                sendMessage(logMode, prefix + levelPrefix + replace(message), ps);
                return;
            }
            sendMessage(logMode, prefix + levelPrefix + message, null);
        } else if (LogLevel == Level.ERROR) {
            levelPrefix = "&c[ERRO]";
            if (logMode == LoggerType.VELOCITY) {
                sendMessage(logMode, prefix + levelPrefix + replace(message), ps);
                return;
            }
            sendMessage(logMode, prefix + levelPrefix + message, null);
        } else if (LogLevel == Level.WARN) {
            levelPrefix = "&6[AVISO] ";
            if (logMode == LoggerType.VELOCITY) {
                sendMessage(logMode, prefix + levelPrefix + replace(message), ps);
                return;
            }
            sendMessage(logMode, prefix + levelPrefix + message, null);
        } else if (LogLevel == Level.INFO) {
            levelPrefix = "&a[INFO]";
            if (logMode == LoggerType.VELOCITY) {
                sendMessage(logMode, prefix + levelPrefix + replace(message), ps);
                return;
            }
            sendMessage(logMode, prefix + levelPrefix + message, null);
        } else if (LogLevel == Level.NO_PREFIX) {
            if (logMode == LoggerType.VELOCITY) {
                sendMessage(logMode, prefix + replace(message), ps);
                return;
            }
            sendMessage(logMode, prefix + replace(message), null);
        } else {
            levelPrefix = "&4[?]";
            sendMessage(logMode, prefix + levelPrefix + message, null);
            ;
            sendMessage(logMode, "Houve um erro ao usar o Logger: o nível do Logger não é reconhecido: \n" + LogLevel.name(), null);
        }
    }

    private static String replace(String rawMessage) {
        return rawMessage.replace("&", "§");
    }

}
