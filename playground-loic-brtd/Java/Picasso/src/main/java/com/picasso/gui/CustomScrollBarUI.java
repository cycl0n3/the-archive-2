package com.picasso.gui;

import com.picasso.gui.theme.BaseTheme;
import com.picasso.gui.theme.ThemeManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class CustomScrollBarUI extends BasicScrollBarUI {

    private static final ThemeManager themes = ThemeManager.getInstance();

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return new JButton() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension();
            }
        };
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return new JButton() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension();
            }
        };
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
        g.setColor(themes.current().background());
        g.fillRect(0, 0, c.getWidth(), c.getHeight());
    }

    private boolean checkSizes(JComponent c, Rectangle r) {
        JScrollBar sb = (JScrollBar) c;
        if (sb.getOrientation() == Adjustable.VERTICAL) {
            return r.width > r.height;
        } else {
            return r.height > r.width;
        }
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
        if (!c.isEnabled() || checkSizes(c, r)) return;
        g.setColor(isThumbRollover()
                ? themes.current().focusedThumb()
                : themes.current().thumb());
        g.fillRect(r.x, r.y, r.width, r.height);
        g.dispose();
    }

    @Override
    protected void setThumbBounds(int x, int y, int width, int height) {
        super.setThumbBounds(x, y, width, height);
        scrollbar.repaint();
    }
}