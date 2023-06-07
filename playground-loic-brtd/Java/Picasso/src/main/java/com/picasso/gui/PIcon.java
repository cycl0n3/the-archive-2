package com.picasso.gui;

import com.picasso.gui.theme.BaseTheme;
import com.picasso.gui.theme.ThemeManager;
import org.jdesktop.swingx.JXPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class PIcon extends JXPanel {

    private static final ThemeManager themes = ThemeManager.getInstance();

    private BufferedImage img;
    private final int padding = 4;
    private boolean hovered = false;

    public PIcon(String resourcePath) {
        setOpaque(false);
        InputStream inputStream = PIcon.class.getResourceAsStream(resourcePath);
        try {
            img = ImageIO.read(inputStream);
        } catch (IOException e) {
            System.err.println("Can't create Image for " + resourcePath);
            return;
        }
        setPreferredSize(new Dimension(img.getWidth() + padding * 2, img.getHeight() + padding * 2));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovered = false;
                repaint();
            }
        });
    }

    public boolean isHovered() {
        return hovered;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Dimension d = getPreferredSize();
        if (hovered) {
            g2.setColor(themes.current().focusedMenu());
            g2.fillOval(0, 0, d.width, d.height);
        } else {
            g2.setColor(themes.current().menuBorder());
            g2.drawOval(0, 0, d.width - 1, d.height - 1);
        }
        g2.drawImage(img, padding, padding, null);
    }
}
