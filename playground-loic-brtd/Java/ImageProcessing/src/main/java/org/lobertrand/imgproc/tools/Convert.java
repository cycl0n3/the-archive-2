package org.lobertrand.imgproc.tools;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Convert {

    public static BufferedImage bufferedImageToARGB(BufferedImage img) {
        BufferedImage copyImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = copyImg.createGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return copyImg;
    }

}
