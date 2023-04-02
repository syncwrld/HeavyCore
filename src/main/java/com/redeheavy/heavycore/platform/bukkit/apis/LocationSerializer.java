package com.redeheavy.heavycore.platform.bukkit.apis;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;

public final class LocationSerializer extends Location implements ConfigurationSerializable {

    public LocationSerializer(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public static String serializeLocation(Location loc) {
        return loc.getWorld().getName() + ','
                + loc.getX() + ','
                + loc.getY() + ','
                + loc.getZ() + ','
                + loc.getYaw() + ','
                + loc.getPitch();
    }

    public static Location deserializeLocation(String stringlocation) {
        String[] location = stringlocation.split(",");
        return new Location(
                Bukkit.getWorld(location[0]),
                Double.parseDouble(location[1]),
                Double.parseDouble(location[2]),
                Double.parseDouble(location[3]),
                Float.parseFloat(location[4]),
                Float.parseFloat(location[5]))
                ;
    }

    public Map<String, Object> serialize() {
        HashMap<String, Object> map = new HashMap<String, Object>();

        map.put("world", getWorld().getName());
        map.put("x", getX());
        map.put("y", getY());
        map.put("z", getZ());

        return map;
    }
}
