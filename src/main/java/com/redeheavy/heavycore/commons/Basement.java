package com.redeheavy.heavycore.commons;

import com.redeheavy.heavycore.commons.enums.PlatformType;
import com.redeheavy.heavycore.commons.logging.LoggerFactory;
import com.redeheavy.heavycore.commons.objects.HeavyPlugin;
import lombok.Getter;

public class Basement {

    static HeavyPlugin hp = new HeavyPlugin("HeavyCore", "?", PlatformType.BUKKIT, "?", "?");

    @Getter
    static final LoggerFactory logger = new LoggerFactory(hp, "[HeavyCore]");

}
