package com.wora.common.util;

import com.wora.common.domain.Color;

public class Print {
    public static void print(Object message) {
        System.out.println("- " + message + ":  ");
    }

    public static void title(Object message) {
        System.out.println(Color.WHITE_BOLD_BRIGHT + " \t\t\t" + message + "\t\t\t\n" + Color.RESET);
    }

    public static void secondaryTitle(Object message) {
        System.out.println(Color.WHITE_BOLD_BRIGHT + " \t\t\t" + message + "\t\t\t\n" + Color.RESET);
    }

}
