package com.redeheavy.heavycore.commons.registry;

import com.redeheavy.heavycore.commons.objects.HeavyPlugin;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class RegistryMap {

    @Getter
    static LinkedList<HeavyPlugin> activePlugins = new LinkedList<>();

    @Getter
    static LinkedList<HeavyPlugin> disabledPlugins = new LinkedList<>();

    @Getter
    static ArrayList<HeavyPlugin> updateList = new ArrayList<>();

    public static void addValid(HeavyPlugin plugin) {
        activePlugins.add(plugin);
    }

    public static void removeValid(HeavyPlugin plugin) {
        activePlugins.remove(plugin);
    }

    public static void addToUpdateList(HeavyPlugin heavyPlugin) {
        updateList.add(heavyPlugin);
    }

}