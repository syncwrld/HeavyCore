package com.redeheavy.heavycore.platform.bukkit.apis;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PlayerUtils {

    private static Plugin plugin;

    public PlayerUtils(Plugin plugin) {
        plugin = plugin;
    }

    public static void connectToServer(final Player who, final String server_name) {
        final ByteArrayDataOutput dataOutput = ByteStreams.newDataOutput();
        dataOutput.writeUTF("Connect");
        dataOutput.writeUTF(server_name);
        who.sendPluginMessage(plugin, "BungeeCord", dataOutput.toByteArray());
    }

    public static void sendTitle(final Player target, final @NonNull String title, final @NonNull String subtitle) {
        target.sendTitle(title, subtitle);
    }

    public static void sendTitleForAll(final @NonNull String title, final @NonNull String subtitle) {
        for (Player all : Bukkit.getOnlinePlayers()) {
            sendTitle(all, title, subtitle);
        }
    }

    public static void playSoundForAll(final Sound sound, final float volume, final float pitch) {
        for (Player all : Bukkit.getOnlinePlayers()) {
            all.playSound(all.getLocation(), sound, volume, pitch);
        }
    }

    public static void playSound(final Player target, final Sound sound, final float volume, final float pitch) {
        target.playSound(target.getLocation(), sound, volume, pitch);
    }

    public static void sendActionbarForAll(final @NonNull String message) {
        for (Player all : Bukkit.getOnlinePlayers()) {
            ActionBarAPI.sendActionBarMessage(all, message);
        }
    }

    public static void sendMessageToAll(final @NonNull String message) {
        for (Player all : Bukkit.getOnlinePlayers()) {
            all.sendMessage(message);
        }
    }

    public static void sendCenteredMessage(final Player target, final @NonNull String message) {
        target.sendMessage(TextUtils.getCenteredMessage(message));
    }

    public static void sendCenteredMessageForAll(final @NonNull String message) {
        for (Player all : Bukkit.getOnlinePlayers()) {
            all.sendMessage(TextUtils.getCenteredMessage(message));
        }
    }
}
