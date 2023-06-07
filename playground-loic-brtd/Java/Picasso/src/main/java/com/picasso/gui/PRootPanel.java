package com.picasso.gui;

import com.picasso.gui.theme.ThemeManager;

import javax.swing.*;
import java.awt.*;

public class PRootPanel extends JPanel {

    private static final ThemeManager themes = ThemeManager.getInstance();

    private static PRootPanel rootPanel;

    private final PDesktop desktop;

    private PRootPanel() {
        setLayout(new BorderLayout());
        setBackground(themes.current().background());
        desktop = new PDesktop();
        add(desktop, BorderLayout.CENTER);
    }

    public static PRootPanel create() {
        rootPanel = new PRootPanel();
        return rootPanel;
    }

    public PDesktop getDesktop() {
        return desktop;
    }

    public static PRootPanel getRootPanel() {
        return rootPanel;
    }
}
