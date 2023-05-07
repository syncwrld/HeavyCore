package com.redeheavy.heavycore.commons.objects;

import com.redeheavy.heavycore.commons.enums.PlatformType;
import com.redeheavy.heavycore.commons.registry.HeavyRegistry;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class HeavyPlugin  {

    private String pluginName;
    private String rootPackage;
    private String pluginVersion;
    private String author;
    private List<String> authors;
    private PlatformType platformType;

    public HeavyPlugin(@NonNull String pluginName, @NonNull String rootPackage, @NonNull PlatformType platform, String pluginVersion, List<String> authors) {
        this.pluginName = pluginName;
        this.rootPackage = rootPackage;
        this.pluginVersion = pluginVersion;
        this.platformType = platform;
        this.authors = authors;

        new HeavyRegistry(this);
    }

    public HeavyPlugin(@NonNull String pluginName, @NonNull String rootPackage, @NonNull PlatformType platform, String pluginVersion, String author) {
        this.pluginName = pluginName;
        this.rootPackage = rootPackage;
        this.pluginVersion = pluginVersion;
        this.platformType = platform;
        this.author = author;

        new HeavyRegistry(this);
    }

}
