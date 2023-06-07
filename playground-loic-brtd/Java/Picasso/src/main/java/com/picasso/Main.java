package com.picasso;

import com.picasso.app.FileMenu;
import com.picasso.gui.PFrame;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            PFrame.getMain().setVisible(true);
            FileMenu.open();
        });
    }

}
