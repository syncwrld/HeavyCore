package com.redeheavy.heavycore.loaders;

import com.redeheavy.heavycore.commons.enums.Level;
import com.redeheavy.heavycore.commons.enums.LoggerType;
import com.redeheavy.heavycore.platform.bungeecord.utils.BungeeLogger;
import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeLoader extends Plugin{

    @Getter static Plugin plugin;

    public void onEnable() {
        plugin = this;
        BungeeLogger.setPrefix("&6[HeavyCore] ");

        BungeeLogger.send(Level.INFO, "Carregado.");
    }



}
