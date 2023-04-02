package com.redeheavy.heavycore.platform.bukkit.apis;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


public class ActionBarAPI {
    private static Class<?> CRAFTPLAYERCLASS;
    private static Class<?> PACKET_PLAYER_CHAT_CLASS;
    private static Class<?> ICHATCOMP;
    private static Class<?> CHATMESSAGE;
    private static Class<?> PACKET_CLASS;
    private static Constructor<?> PACKET_PLAYER_CHAT_CONSTRUCTOR;
    private static Constructor<?> CHATMESSAGE_CONSTRUCTOR;
    private static final String SERVER_VERSION;

    static {
        String name = Bukkit.getServer().getClass().getName();
        name = name.substring(name.indexOf("craftbukkit.") + "craftbukkit.".length());
        name = (SERVER_VERSION = name.substring(0, name.indexOf(".")));
        try {
            ActionBarAPI.CRAFTPLAYERCLASS = Class.forName("org.bukkit.craftbukkit." + ActionBarAPI.SERVER_VERSION + ".entity.CraftPlayer");
            ActionBarAPI.PACKET_PLAYER_CHAT_CLASS = Class.forName("net.minecraft.server." + ActionBarAPI.SERVER_VERSION + ".PacketPlayOutChat");
            ActionBarAPI.PACKET_CLASS = Class.forName("net.minecraft.server." + ActionBarAPI.SERVER_VERSION + ".Packet");
            ActionBarAPI.ICHATCOMP = Class.forName("net.minecraft.server." + ActionBarAPI.SERVER_VERSION + ".IChatBaseComponent");
            ActionBarAPI.PACKET_PLAYER_CHAT_CONSTRUCTOR = ActionBarAPI.PACKET_PLAYER_CHAT_CLASS.getConstructor(ActionBarAPI.ICHATCOMP, Byte.TYPE);
            ActionBarAPI.CHATMESSAGE = Class.forName("net.minecraft.server." + ActionBarAPI.SERVER_VERSION + ".ChatMessage");
            ActionBarAPI.CHATMESSAGE_CONSTRUCTOR = ActionBarAPI.CHATMESSAGE.getDeclaredConstructor(String.class, Object[].class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendActionBarMessage(final Player player, final String message) {
        try {
            final Object icb = ActionBarAPI.CHATMESSAGE_CONSTRUCTOR.newInstance(message, new Object[0]);
            final Object packet = ActionBarAPI.PACKET_PLAYER_CHAT_CONSTRUCTOR.newInstance(icb, (byte) 2);
            final Object craftplayerInst = ActionBarAPI.CRAFTPLAYERCLASS.cast(player);
            final Method methodHandle = ActionBarAPI.CRAFTPLAYERCLASS.getMethod("getHandle", (Class<?>[]) new Class[0]);
            final Object methodhHandle = methodHandle.invoke(craftplayerInst, new Object[0]);
            final Object playerConnection = methodhHandle.getClass().getField("playerConnection").get(methodhHandle);
            playerConnection.getClass().getMethod("sendPacket", ActionBarAPI.PACKET_CLASS).invoke(playerConnection, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}