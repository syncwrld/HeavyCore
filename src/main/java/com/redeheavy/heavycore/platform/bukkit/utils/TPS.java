package com.redeheavy.heavycore.platform.bukkit.utils;

import org.bukkit.Bukkit;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TPS {
    private static final Class<?> spigotServerClass;
    private static final Method getSpigotMethod;
    private static final Method getTPSMethod;
    private static final Class<?> minecraftServerClass;
    private static final Method getServerMethod;
    private static final Field recentTpsField;

    public static double getAverageTPS(final int time) {
        final double[] recentTps = canGetWithPaper() ? getPaperRecentTps() : getNMSRecentTps();
        switch (time) {
            case 1: {
                final double raw = recentTps[0];
                return Math.min(Math.round(raw * 100.0) / 100.0, 20.0);
            }
            case 5: {
                final double raw = recentTps[1];
                return Math.min(Math.round(raw * 100.0) / 100.0, 20.0);
            }
            case 15: {
                final double raw = recentTps[2];
                return Math.min(Math.round(raw * 100.0) / 100.0, 20.0);
            }
            default: {
                throw new IllegalArgumentException("Unsupported tps measure time " + time);
            }
        }
    }

    private static double[] getPaperRecentTps() {
        if (!canGetWithPaper()) {
            throw new UnsupportedOperationException("Erro ao obter TPS");
        }
        final Object server = Reflection.callMethod(TPS.getServerMethod, null, new Object[0]);
        return Reflection.getField(TPS.recentTpsField, server);
    }

    private static boolean canGetWithPaper() {
        return TPS.getSpigotMethod != null && TPS.getTPSMethod != null;
    }

    private static double[] getNMSRecentTps() {
        if (TPS.getServerMethod == null || TPS.recentTpsField == null) {
            throw new UnsupportedOperationException("Erro ao obter TPS");
        }
        final Object server = Reflection.callMethod(TPS.getServerMethod, null, new Object[0]);
        return Reflection.getField(TPS.recentTpsField, server);
    }

    static {
        spigotServerClass = Reflection.getClass("org.bukkit.Server$Spigot");
        getSpigotMethod = Reflection.makeMethod(Bukkit.class, "spigot", (Class<?>[]) new Class[0]);
        getTPSMethod = ((TPS.spigotServerClass != null) ? Reflection.makeMethod(TPS.spigotServerClass, "getTPS", (Class<?>[]) new Class[0]) : null);
        minecraftServerClass = Reflection.getNmsClass("MinecraftServer");
        getServerMethod = ((TPS.minecraftServerClass != null) ? Reflection.makeMethod(TPS.minecraftServerClass, "getServer", (Class<?>[]) new Class[0]) : null);
        recentTpsField = ((TPS.minecraftServerClass != null) ? Reflection.makeField(TPS.minecraftServerClass, "recentTps") : null);
    }
}
