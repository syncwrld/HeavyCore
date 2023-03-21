package com.redeheavy.heavycore.loaders;

import com.redeheavy.heavycore.commons.enums.Level;
import com.redeheavy.heavycore.commons.enums.LoggerType;
import com.redeheavy.heavycore.commons.systems.Logger;
import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeLoader extends Plugin{

    @Getter static Plugin plugin;

    public void onEnable() {
        plugin = this;
        Logger.setPrefix("&6[HeavyCore] ");

        Logger.send(Level.INFO, LoggerType.BUNGEECORD, "Carregado.");
    }



}
