package org.lobertrand.imgproc.core;

import org.lobertrand.imgproc.filter.ImageFilter;
import org.lobertrand.imgproc.tools.Convert;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BufferImage implements Image {

    private final BufferedImage bufferedImage;

    private BufferImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage.getColorModel().hasAlpha()
                ? bufferedImage : Convert.bufferedImageToARGB(bufferedImage);
    }

    @Override
    public int width() {
        return bufferedImage.getWidth();
    }

    @Override
    public int height() {
        return bufferedImage.getHeight();
    }

    @Override
    public Image resize(int width, int height) {
        if (width <= 0) {
            width = (this.width() * height) / this.height();
        } else if (height <= 0) {
            height = (this.height() * width) / this.width();
        }

        BufferedImage copyImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = copyImg.createGraphics();
        g.drawImage(bufferedImage, 0, 0, width, height, null);
        g.dispose();
        return new BufferImage(copyImg);
    }

    @Override
    public Image copy() {
        BufferedImage copyImg = new BufferedImage(width(), height(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = copyImg.createGraphics();
        g.drawImage(bufferedImage, 0, 0, null);
        g.dispose();
        return new BufferImage(copyImg);
    }

    @Override
    public BufferedImage toBufferedImage() {
        return bufferedImage;
    }

    @Override
    public Image filter(ImageFilter filter) {
        return filter.applyTo(this);
    }

    @Override
    public void setPixel(int x, int y, int[] rgba) {
        bufferedImage.getRaster().setPixel(x, y, rgba);
    }

    @Override
    public int[] getPixel(int x, int y, int[] rgba) {
        return bufferedImage.getRaster().getPixel(x, y, rgba);
    }

    public static Image load(String path) throws IOException {
        var bufferedImage = ImageIO.read(new File(path));
        return new BufferImage(bufferedImage);
    }

    public static Image fromBufferedImage(BufferedImage bufferedImage) {
        return new BufferImage(bufferedImage);
    }
}
