package org.lobertrand.imgproc.tools;

import org.lobertrand.imgproc.core.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Show {

    private Show() {
    }

    public static void frame(Image image) {
        var frame = new JFrame();
        var panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                var g2 = (Graphics2D) g;
                g2.drawImage(image.toBufferedImage(), 0, 0, null);
            }
        };
        panel.setPreferredSize(new Dimension(image.width(), image.height()));
        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });

        frame.setVisible(true);
    }

}
