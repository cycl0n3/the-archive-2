package com.picasso.gui;

import java.awt.*;

public class DesktopLayout implements LayoutManager {

    @Override
    public void addLayoutComponent(String name, Component comp) {
    }

    @Override
    public void removeLayoutComponent(Component comp) {
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        Insets in = parent.getInsets();
        return new Dimension(in.left + in.right, in.top + in.bottom);
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return preferredLayoutSize(parent);
    }

    @Override
    public void layoutContainer(Container parent) {
        Dimension parentSize = parent.getSize();
        for (Component c : parent.getComponents()) {
            if (c.isVisible()) {
                Dimension compSize = c.getPreferredSize();
                int x = c.getX();
                int y = c.getY();
                int w = Math.min(compSize.width, parentSize.width - x);
                int h = Math.min(compSize.height, parentSize.height - y);
                c.setBounds(x, y, w, h);
            }
        }
    }

}