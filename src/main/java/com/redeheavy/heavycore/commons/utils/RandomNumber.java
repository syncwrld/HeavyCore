package com.redeheavy.heavycore.commons.utils;

public class RandomNumber {

    public static int getInt(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    public static double getDouble(double min, double max) {
        return min + (Math.random() * ((max - min) + 1));
    }

    public static float getFloat(float min, float max) {
        return min + (float) (Math.random() * ((max - min) + 1));
    }

}
