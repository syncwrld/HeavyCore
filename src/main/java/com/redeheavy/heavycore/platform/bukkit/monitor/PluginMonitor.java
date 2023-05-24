package com.redeheavy.heavycore.platform.bukkit.monitor;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.Plugin;

public class PluginMonitor implements Listener {

    public PluginMonitor(Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEnabled(PluginEnableEvent event) {
        if (allEnabled()) {
            System.out.println("Todos os plugins foram ligados!");
            // BukkitLoader.loadModules();
        }
    }

    static boolean allEnabled() {
        if (Bukkit.getPluginManager().getPlugins() == null) return false;
        for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            if (plugin != null && !plugin.isEnabled()) {
                return false;
            }
        }
        return true;
    }

}
