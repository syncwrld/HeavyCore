package com.redeheavy.heavycore.commons.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class HeavyPlugin {

    private String pluginName;
    private String mainDirectory;
    private String pluginVersion;
    private List<String> authors;


    public HeavyPlugin(@NonNull String pluginName, @NonNull String mainDirectory, String pluginVersion, List<String> authors) {
        this.pluginName = pluginName;
        this.mainDirectory = mainDirectory;
        this.pluginVersion = pluginVersion;
        this.authors = authors;
    }


}
