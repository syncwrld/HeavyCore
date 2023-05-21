package com.redeheavy.heavycore.commons.superupdater;

import com.redeheavy.heavycore.commons.superupdater.scanner.RegisteredPluginFileScanner;

public class SuperUpdater {

    public static void startUpdater() {
        RegisteredPluginFileScanner.load();
    }

}
