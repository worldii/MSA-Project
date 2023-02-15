package com.example.chatservice.util;

public final class StringUtil {

    public static String limitText(String text) {
        if (text.length() >= 20) {
            return text.substring(0, 20) + "...";
        }

        return text;
    }
}
