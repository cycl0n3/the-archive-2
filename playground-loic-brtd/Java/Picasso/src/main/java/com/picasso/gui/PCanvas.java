package com.picasso.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PCanvas extends JPanel {

    private BufferedImage image;
    private float scale;
    private int width, height;

    public PCanvas(BufferedImage image) {
        this.image = image;
        setScale(1);
    }

    public void setScale(float scale) {
        this.scale = Math.max(scale, 0);
        this.width = (int) (image.getWidth() * scale);
        this.height = (int) (image.getHeight() * scale);
        this.setPreferredSize(new Dimension(width, height));
        updateUI();
    }

    public float getScale() {
        return scale;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, width, height, null);
    }
}
