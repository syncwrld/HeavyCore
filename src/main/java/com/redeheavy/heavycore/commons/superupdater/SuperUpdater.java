package com.redeheavy.heavycore.commons.superupdater;

import com.redeheavy.heavycore.commons.objects.HeavyPlugin;
import com.redeheavy.heavycore.commons.registry.RegistryMap;
import com.redeheavy.heavycore.commons.superupdater.model.SuperUpdaterOptions;
import com.redeheavy.heavycore.commons.superupdater.scanner.RegisteredPluginFileScanner;

import java.util.Collections;
import java.util.List;

public class SuperUpdater {

    private final List<SuperUpdaterOptions> options;
    private final String download_url;

    public SuperUpdater(HeavyPlugin heavyPlugin, List<SuperUpdaterOptions> options, String download_url) {
        RegistryMap.addToUpdateList(heavyPlugin);

        this.options = options;
        this.download_url = download_url;
    }

    public SuperUpdater(HeavyPlugin heavyPlugin, SuperUpdaterOptions option, String download_url) {
        RegistryMap.addToUpdateList(heavyPlugin);

        this.options = Collections.singletonList(option);
        this.download_url = download_url;
    }

    public void check() {
        new RegisteredPluginFileScanner(options, download_url).load();
    }

}
