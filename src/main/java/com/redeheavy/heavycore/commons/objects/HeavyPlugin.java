package com.redeheavy.heavycore.commons.objects;

import com.redeheavy.heavycore.commons.enums.PlatformType;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class HeavyPlugin {

    private String pluginName;
    private String mainDirectory;
    private String pluginVersion;
    private List<String> authors;
    private PlatformType platformType;


    public HeavyPlugin(@NonNull String pluginName, @NonNull String mainDirectory, @NonNull PlatformType platform, String pluginVersion, List<String> authors) {
        this.pluginName = pluginName;
        this.mainDirectory = mainDirectory;
        this.pluginVersion = pluginVersion;
        this.platformType = platform;
        this.authors = authors;
    }


}
