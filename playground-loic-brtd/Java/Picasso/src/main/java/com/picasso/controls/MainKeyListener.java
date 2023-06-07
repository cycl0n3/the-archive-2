package com.picasso.controls;

import com.picasso.gui.PDesktop;
import com.picasso.gui.PRootPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;

public class MainKeyListener implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {
        PDesktop desktop = PRootPanel.getRootPanel().getDesktop();
        if (e.isControlDown()) {
            if (e.getKeyCode() == VK_W) {
                if (desktop.hasFramesOpen())
                    desktop.closeSelectedFrame();
                else System.exit(0);
            }
        } else {
            switch (e.getKeyCode()) {
                case VK_ADD:
                case VK_PLUS:
                    desktop.zoom();
                    break;
                case VK_SUBTRACT:
                case VK_MINUS:
                    desktop.unzoom();
                    break;
                default:
                    break;
            }
        }
    }

    private boolean isCtrl(int key, KeyEvent e) {
        return e.isControlDown() && e.getKeyCode() == key;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
