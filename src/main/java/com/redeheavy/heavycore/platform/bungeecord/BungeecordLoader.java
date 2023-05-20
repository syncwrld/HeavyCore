package com.redeheavy.heavycore.platform.bungeecord;

import com.redeheavy.heavycore.commons.Basement;
import com.redeheavy.heavycore.commons.logging.LoggerFactory;
import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeecordLoader extends Plugin {

    @Getter
    static final LoggerFactory buildLogger = Basement.getLogger();

    @Override
    public void onEnable() {
        buildLogger.info("Carregado no Bungeecord.");
    }
}
