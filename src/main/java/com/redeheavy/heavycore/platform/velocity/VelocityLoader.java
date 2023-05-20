package com.redeheavy.heavycore.platform.velocity;

import com.google.inject.Inject;
import com.redeheavy.heavycore.commons.Basement;
import com.redeheavy.heavycore.commons.logging.LoggerFactory;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import lombok.Getter;


@Plugin(
        id = "heavycore",
        name = "HeavyCore",
        version = "v1.1",
        url = "https://github.com/syncwrld/HeavyCore",
        authors = {"syncwrld", "synczinbr"},
        description = "Um Core/API completo para a maioria das plataformas de servidores"
)
public class VelocityLoader {

    @Getter
    static ProxyServer server;

    @Getter
    LoggerFactory buildLogger = Basement.getLogger();

    @Inject
    public VelocityLoader(ProxyServer proxyServer) {
        server = proxyServer;
    }

    @Subscribe
    public void onProxyInitiliaze(ProxyInitializeEvent event) {
        buildLogger.info("Carregado no Velocity.");
    }

}
