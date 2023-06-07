package core;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ColorManagement {

    public static int color(float greyscale) {
        int grey = PCanvas.constrain((int) greyscale, 0, 255);
        int res = 255 << 24; // alpha
        res += grey << 16; // red
        res += grey << 8; // green
        res += grey; // blue
        return res;
    }

    public static int color(int rgb) {
        if (((rgb & 0xff000000) == 0) && (rgb <= 255)) {
            int grey = PCanvas.constrain(rgb, 0, 255);
            int res = 255 << 24; // alpha
            res += grey << 16; // red
            res += grey << 8; // green
            res += grey; // blue
            return res;
        } else {
            return rgb;
        }
    }

    public static int color(float greyscale, float alpha) {
        int g = PCanvas.constrain((int) greyscale, 0, 255);
        int a = PCanvas.constrain((int) alpha, 0, 255);
        int res = a << 24; // alpha
        res += g << 16; // red
        res += g << 8; // green
        res += g; // blue
        return res;
    }

    public static int color(int rgb, float alpha) {
        if (((rgb & 0xff000000) == 0) && (rgb <= 255)) {
            int grey = PCanvas.constrain(rgb, 0, 255);
            int a = PCanvas.constrain((int) alpha, 0, 255);
            int res = a << 24; // alpha
            res += grey << 16; // red
            res += grey << 8; // green
            res += grey; // blue
            return res;
        } else {
            int a = PCanvas.constrain((int) alpha, 0, 255);
            int res = a << 24; // alpha
            res += red(rgb) << 16; // red
            res += green(rgb) << 8; // green
            res += blue(rgb); // blue
            return res;
        }
    }

    public static int color(int red, int green, int blue) {
        red = PCanvas.constrain(red, 0, 255);
        green = PCanvas.constrain(green, 0, 255);
        blue = PCanvas.constrain(blue, 0, 255);
        int res = 255 << 24; // alpha
        res += red << 16; // red
        res += green << 8; // green
        res += blue; // blue
        return res;
    }

    public static int color(int red, int green, int blue, int alpha) {
        red = PCanvas.constrain(red, 0, 255);
        green = PCanvas.constrain(green, 0, 255);
        blue = PCanvas.constrain(blue, 0, 255);
        alpha = PCanvas.constrain(alpha, 0, 255);
        int res = alpha << 24; // alpha
        res += red << 16; // red
        res += green << 8; // green
        res += blue; // blue
        return res;
    }

    public static String hex(int color) {
        return String.format("0x%08X", color);
    }

    public static int alpha(int color) {
        return (color >> 24) & 0xFF;
    }

    public static int red(int color) {
        return (color >> 16) & 0xFF;
    }

    public static int green(int color) {
        return (color >> 8) & 0xFF;
    }

    public static int blue(int color) {
        return (color) & 0x000000FF;
    }

    public static int hue(int color) {
        return 0;
    }

    public static int saturation(int color) {
        return 0;
    }

    public static int brightness(int color) {
        return 0;
    }

    private static void visualize(int color) {
        BufferedImage i = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics g = i.getGraphics();
        // if (((color & 0xff000000) == 0) && (color <= 255)) {
        // g.setColor(new Color(color(color), true));
        // } else {
        // g.setColor(new Color(color, true));
        // }
        g.setColor(new Color(color, true));
        g.fillRect(0, 0, 200, 200);

        JPanel p = new JPanel() {
            private static final long serialVersionUID = 1L;

            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(i, 0, 0, null);
            };
        };
        p.setPreferredSize(new Dimension(200, 200));
        p.setBackground(Color.WHITE);
        JFrame f = new JFrame("Color: " + hex(color));
        f.setContentPane(p);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        p.repaint();
    }

    public static void main(String[] args) {
        // int c = 0x00FF10FF;
        // System.out.println(hex(c));
        // System.out.println("alpha: " + alpha(c));
        // System.out.println("red: " + red(c));
        // System.out.println("green: " + green(c));
        // System.out.println("blue: " + blue(c));
        // System.out.println(hex(color(255, 64)));
        // System.out.println(hex(color(255, 64)));
        int c1 = 0x00000000;
        int c2 = color(0);
        System.out.println(c1 == c2);
        System.out.println(hex(c1));
        System.out.println(hex(c2));
        // visualize(c1);
        // visualize(c2);

        visualize(color(255, 50, 120, 100));
        visualize(color(255, 50, 120));
        visualize(color(0, 100, 255));
        System.out.println(alpha(color(0, 0, 0)));

    }

    public static int greyScale(int i) {
        return color((red(i) + green(i) + blue(i)) / 3);
    }

}
