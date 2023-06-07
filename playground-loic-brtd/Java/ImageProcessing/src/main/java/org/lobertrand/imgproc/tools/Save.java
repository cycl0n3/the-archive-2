package org.lobertrand.imgproc.tools;

import org.lobertrand.imgproc.core.Image;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public final class Save {

    private Save() {
    }

    public static void asPNG(Image image, String path) {
        var file = new File(path);
        var buffImg = image.toBufferedImage();
        try {
            ImageIO.write(buffImg, "png", file);
            System.out.println("Saved " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
