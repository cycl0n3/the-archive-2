package com.picasso.gui.theme;

import java.util.ArrayList;
import java.util.List;

public class ThemeManager {

    public static ThemeManager instance;

    private Theme currentTheme = new BaseTheme();
    private List<Runnable> changeListeners = new ArrayList<>();

    private ThemeManager() {}

    public static synchronized ThemeManager getInstance() {
        if (instance == null) {
            instance = new ThemeManager();
        }
        return instance;
    }

    public void setTheme(Theme theme) {
        this.currentTheme = theme;
        changeListeners.forEach(Runnable::run);
    }

    public Theme current() {
        return currentTheme;
    }

    public void onChange(Runnable runnable) {
        changeListeners.add(runnable);
    }

}
