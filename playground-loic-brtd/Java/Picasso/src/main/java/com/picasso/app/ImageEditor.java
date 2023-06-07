package com.picasso.app;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageEditor {

    private final File originalFile;
    private BufferedImage bufferedImage;
    private boolean loadedProperly;

    public ImageEditor(File file) {
        this.originalFile = file;

        try {
            bufferedImage = ImageIO.read(originalFile);
            loadedProperly = true;
        } catch (IOException e) {
            System.err.println("Can't create Image for " + file);
        }
    }

    public boolean isLoadedProperly() {
        return loadedProperly;
    }

    public String getName() {
        return originalFile.getName();
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }
}
