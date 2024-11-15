package com.example.asm1;

public class PrefKeyClass {
    private static final String PREFS_NAME = "setting";
    private static final String LANGUAGE_KEY = "language_index";
    private static final String THEME_KEY = "theme_index";
    private static final String MUSIC_KEY = "music_index";

    public static String getPrefsName() {
        return PREFS_NAME;
    }

    public static String getLanguageKey() {
        return LANGUAGE_KEY;
    }

    public static String getMusicKey() {
        return MUSIC_KEY;
    }

    public static String getThemeKey() {
        return THEME_KEY;
    }
}
