package com.redeheavy.heavycore.platform.bukkit.apis;


import org.bukkit.ChatColor;

public class TextUtils {

    private final static int CENTER_PX = 154;

    public static String getCenteredMessage(String message) {
        if (message == null || message.equals("")) return "";
        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for (char c : message.toCharArray()) {
            if (c == '�') {
                previousCode = true;

            } else if (previousCode) {
                previousCode = false;
                isBold = c == 'l' || c == 'L';

            } else {
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while (compensated < toCompensate) {
            sb.append(" ");
            compensated += spaceLength;
        }

        return sb.toString() + message;

    }

    public static String translate(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
