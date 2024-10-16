package com.project.bootcamp_project.util;

import java.util.Objects;

public class Console {
    private static final String MODE = System.getenv("MODE").toUpperCase();
    private static final String RESET = "\033[0m";
    private static final String GREEN = "\033[0;32m";
    private static final String BLUE = "\033[0;34m";

    public static void Log(String message) {
        if (Objects.equals(MODE, "DEV")) {
            System.out.println(GREEN + message + RESET);
        }
    }

    public static void Info(String message) {
        if (Objects.equals(MODE, "DEV")) {
            System.out.println(BLUE + message + RESET);
        }
    }

    public static void Error(String message) {
        if (Objects.equals(MODE, "DEV")) {
            System.err.println(message);
        }
    }
}
