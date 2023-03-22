package com.redeheavy.heavycore.loaders;


import com.google.inject.Inject;
import com.redeheavy.heavycore.commons.enums.Level;
import com.redeheavy.heavycore.commons.enums.LoggerType;
import com.redeheavy.heavycore.platform.velocity.utils.VelocityLogger;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;

@Plugin(id = "heavycore",
        name = "HeavyCore",
        version = "1.0-SNAPSHOT",
        url = "https://github.com/syncwrld",
        description = "Dependência pros plugin da Heavy",
        authors = {"syncwrld", "AlbanoHouse"})

public class VelocityLoader {

    private final ProxyServer server;

    @Inject
    public VelocityLoader(ProxyServer server) {
        this.server = server;
    }

    @Subscribe
    public void onEnable(ProxyInitializeEvent event) {
        VelocityLogger.setProxyServer(server);
        VelocityLogger.send(Level.COMMON, "Carregado no Velocity.");
    }

}
