package com.redeheavy.heavycore.commons.superupdater.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.InputStream;

@AllArgsConstructor @Getter
public class PendingPlugin {

    private String name;
    private String version;
    private String description;
    private InputStream pluginYaml;

}
