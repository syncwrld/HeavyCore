package com.redeheavy.heavycore.platform.bukkit.utils;

import com.redeheavy.heavycore.commons.apis.BungeeChannelAPI;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
public class BungeecordUtils {

    private static BungeeChannelAPI channelAPI;

    public BungeecordUtils (@NonNull Plugin instanciator) {
        channelAPI = BungeeChannelAPI.of(instanciator);
    }

    public static void connect(Plugin connector, Player player, String server_name) {
        channelAPI.connect(player, server_name);
    }

    public static void kickPlayer(String target_name, String kick_message) {
        channelAPI.kickPlayer(target_name, kick_message);
    }

    public static Integer getPlayersSize(String server_name) {
        return channelAPI.getPlayerCount(server_name).join();
    }

    public static List<String> getServerList() {
        try {
            return channelAPI.getServers().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
