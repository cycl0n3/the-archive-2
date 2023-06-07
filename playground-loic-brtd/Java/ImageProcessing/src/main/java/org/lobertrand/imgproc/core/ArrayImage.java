package org.lobertrand.imgproc.core;

import org.lobertrand.imgproc.filter.ImageFilter;
import org.lobertrand.imgproc.tools.Convert;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ArrayImage implements Image {

    private final int[][][] array;

    private ArrayImage(BufferedImage bufferedImage) {
        boolean hasNoAlpha = !bufferedImage.getColorModel().hasAlpha();
        array = new int[bufferedImage.getHeight()][bufferedImage.getWidth()][4];
        var raster = bufferedImage.getRaster();
        for (int y = 0; y < bufferedImage.getHeight(); y++) {
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                array[y][x] = raster.getPixel(x, y, new int[4]);
                if (hasNoAlpha) {
                    array[y][x][3] = 255;
                }
            }
        }
    }

    private ArrayImage(int[][][] array) {
        this.array = new int[array.length][array[0].length][4];
        for (int y = 0; y < array.length; y++) {
            for (int x = 0; x < array[y].length; x++) {
                System.arraycopy(array[y][x], 0, this.array[y][x], 0, 4);
            }
        }
    }

    @Override
    public int width() {
        return array[0].length;
    }

    @Override
    public int height() {
        return array.length;
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
        g.drawImage(toBufferedImage(), 0, 0, width, height, null);
        g.dispose();
        return new ArrayImage(copyImg);
    }

    @Override
    public Image copy() {
        return new ArrayImage(array);
    }

    @Override
    public BufferedImage toBufferedImage() {
        var buffImg = new BufferedImage(width(), height(), BufferedImage.TYPE_INT_ARGB);
        var raster = buffImg.getRaster();
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                raster.setPixel(x, y, array[y][x]);
            }
        }
        return buffImg;
    }

    @Override
    public Image filter(ImageFilter filter) {
        return filter.applyTo(this);
    }

    @Override
    public void setPixel(int x, int y, int[] rgba) {
        System.arraycopy(rgba, 0, array[y][x], 0, 4);
    }

    @Override
    public int[] getPixel(int x, int y, int[] rgba) {
        System.arraycopy(array[y][x], 0, rgba, 0, 4);
        return rgba;
    }

    public static Image load(String path) throws IOException {
        var bufferedImage = ImageIO.read(new File(path));
        return new ArrayImage(bufferedImage);
    }

    public static Image fromBufferedImage(BufferedImage bufferedImage) {
        return new ArrayImage(bufferedImage);
    }
}
