package com.picasso.gui.theme;

import com.picasso.util.Fonts;

import javax.swing.*;
import java.awt.*;

import static java.awt.Color.*;

public class BaseTheme implements Theme {

    private Font mainFont;
    private Color background;
    private Color onBackground;
    private Color menu;
    private Color onMenu;
    private Color focusedMenu;
    private Color onFocusedMenu;
    private Color menuBorder;
    private Color thumb;
    private Color focusedThumb;

    public BaseTheme() {
        // Default theme parameters
        mainFont(Fonts.loadFont("/font/noto_sans/NotoSans-Regular.ttf")
                      .map(f -> f.deriveFont(13f))
                      .orElseGet(() -> new Font("Arial", Font.PLAIN, 12)));
        background(decode("#222222"));
        onBackground(decode("#bbbbbb"));
        menu(decode("#333333"));
        onMenu(onBackground);
        focusedMenu(decode("#0055cc"));
        onFocusedMenu(decode("#ffffff"));
        onMenuBorder(decode("#444444"));
        thumb(decode("#555555"));
        focusedThumb(decode("#666666"));
    }

    @Override
    public Font mainFont() {
        return mainFont;
    }

    @Override
    public void mainFont(Font mainFont) {
        this.mainFont = mainFont;
    }

    @Override
    public Color background() {
        return background;
    }

    @Override
    public void background(Color background) {
        this.background = background;
    }

    @Override
    public Color onBackground() {
        return onBackground;
    }

    @Override
    public void onBackground(Color onBackground) {
        this.onBackground = onBackground;
    }

    @Override
    public Color menu() {
        return menu;
    }

    @Override
    public void menu(Color menu) {
        this.menu = menu;
    }

    @Override
    public Color onMenu() {
        return onMenu;
    }

    @Override
    public void onMenu(Color onMenu) {
        this.onMenu = onMenu;
    }

    @Override
    public Color focusedMenu() {
        return focusedMenu;
    }

    @Override
    public void focusedMenu(Color focusedMenu) {
        this.focusedMenu = focusedMenu;
        UIManager.put("Menu.selectionBackground", this.focusedMenu);
        UIManager.put("MenuItem.selectionBackground", this.focusedMenu);
    }

    @Override
    public Color onFocusedMenu() {
        return onFocusedMenu;
    }

    @Override
    public void onFocusedMenu(Color onFocusedMenu) {
        this.onFocusedMenu = onFocusedMenu;
        UIManager.put("Menu.selectionForeground", this.onFocusedMenu);
        UIManager.put("MenuItem.selectionForeground", this.onFocusedMenu);
    }

    @Override
    public Color menuBorder() {
        return menuBorder;
    }

    @Override
    public void onMenuBorder(Color menuBorder) {
        this.menuBorder = menuBorder;
    }

    @Override
    public Color thumb() {
        return thumb;
    }

    @Override
    public void thumb(Color thumb) {
        this.thumb = thumb;
    }

    @Override
    public Color focusedThumb() {
        return focusedThumb;
    }

    @Override
    public void focusedThumb(Color focusedThumb) {
        this.focusedThumb = focusedThumb;
    }
}
