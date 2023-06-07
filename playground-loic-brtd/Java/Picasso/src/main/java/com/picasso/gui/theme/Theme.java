package com.picasso.gui.theme;

import java.awt.*;

public interface Theme {
    Font mainFont();

    void mainFont(Font mainFont);

    Color background();

    void background(Color background);

    Color onBackground();

    void onBackground(Color onBackground);

    Color menu();

    void menu(Color menu);

    Color onMenu();

    void onMenu(Color onMenu);

    Color focusedMenu();

    void focusedMenu(Color focusedMenu);

    Color onFocusedMenu();

    void onFocusedMenu(Color onFocusedMenu);

    Color menuBorder();

    void onMenuBorder(Color menuBorder);

    Color thumb();

    void thumb(Color thumb);

    Color focusedThumb();

    void focusedThumb(Color focusedThumb);
}
