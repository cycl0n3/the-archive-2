package core;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class PImage {

    BufferedImage img;
    public int width, height;
    public int[] pixels;

    public PImage(int width, int height) {
        this.img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.width = width;
        this.height = height;
    }

    public PImage(String filePath) {
        try {
            img = ImageIO.read(new File(filePath));
            width = img.getWidth();
            height = img.getHeight();
            if (!img.getColorModel().hasAlpha()) {
                img = this.copy().img;
            }
        } catch (IOException e) {
            PCanvas.error(e.getMessage());
        }
    }

    public PImage(BufferedImage bufferedImage) {
        img = bufferedImage;
        width = bufferedImage.getWidth();
        height = bufferedImage.getHeight();
    }

    public PImage resize(int width, int height) {
        if (width <= 0) {
            width = (int) (this.width * height / (float) this.height);
        } else if (height <= 0) {
            height = (int) (this.height * width / (float) this.width);
        }

        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resized.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
        g.drawImage(img, 0, 0, width, height, null);
        g.dispose();
        this.img = resized;
        this.width = img.getWidth();
        this.height = img.getHeight();
        return this;
    }

    public int get(int x, int y) {
        return img.getRGB(x, y);
    }

    public int[] getRGBA(int x, int y) {
        return img.getRaster().getPixel(x, y, new int[4]);
    }

    public void loadPixels() {
        pixels = new int[width * height];
        int index = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[index++] = img.getRGB(x, y);
            }
        }
    }

    public void updatePixels() {
        int index = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                img.setRGB(x, y, pixels[index++]);
            }
        }
    }

    public PImage get(int x, int y, int width, int height) {
        return new PImage(img.getSubimage(x, y, width, height));
    }

    public PImage copy() {
        BufferedImage copyImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = copyImg.createGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return new PImage(copyImg);
    }


    public WritableRaster getWritableRaster() {
        return img.getRaster();
    }

    public BufferedImage getBufferedImage() {
        return img;
    }

    @Override
    public String toString() {
        return "PImage [width=" + width + ", height=" + height + "]";
    }

}
