package com.redeheavy.heavycore.commons.database.enums;

public enum Signal {

    EQUALS,
    DIFFERENT;

    public static String getString(Signal signal) {
        switch (signal) {
            case EQUALS:
                return "=";
            case DIFFERENT:
                return "!=";
            default:
                return null;
        }
    }

}
