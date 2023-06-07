package com.picasso.gui;

import com.picasso.controls.MainKeyListener;
import com.picasso.gui.theme.BaseTheme;
import com.picasso.gui.theme.ThemeManager;

import javax.swing.*;
import java.awt.*;

public class PFrame extends JFrame {

    private static final ThemeManager themes = ThemeManager.getInstance();
    private static PFrame mainFrame;

    private PFrame() {
        setBackground(themes.current().background());
        setJMenuBar(PMenuBar.createMain());
        setContentPane(PRootPanel.create());
        setPreferredSize(new Dimension(700, 500));
        setMinimumSize(new Dimension(500, 300));
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Picasso");
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);
        addKeyListener(new MainKeyListener());
    }

    public static PFrame getMain() {
        if (mainFrame == null)
            mainFrame = createMain();
        return mainFrame;
    }

    private static PFrame createMain() {
        return new PFrame();
    }
}
