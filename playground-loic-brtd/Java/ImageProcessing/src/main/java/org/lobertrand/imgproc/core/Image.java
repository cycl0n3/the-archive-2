package org.lobertrand.imgproc.core;

import org.lobertrand.imgproc.filter.ImageFilter;

import java.awt.image.BufferedImage;

public interface Image {

    int width();

    int height();

    Image resize(int width, int height);

    Image copy();

    BufferedImage toBufferedImage();

    default Image map(PixelMapper pixelMapper) {
        Image result = copy();
        var rgba = new int[4];
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                var originalPixel = getPixel(x, y, rgba);
                var mappedPixel = pixelMapper.map(originalPixel, x, y);
                result.setPixel(x, y, mappedPixel);
            }
        }
        return result;
    }

    @FunctionalInterface
    interface PixelMapper {
        int[] map(int[] pixel, int x, int y);
    }

    default int[][][] to3DArray() {
        int[] pixel = new int[4];
        int[][][] result = new int[height()][width()][4];
        for (int i = 0; i < height(); i++) {
            for (int j = 0; j < width(); j++) {
                pixel = getPixel(j, i, pixel);
                System.arraycopy(getPixel(j, i, pixel), 0, result[i][j], 0, 4);
            }
        }
        return result;
    }

    default int[][] toMonochrome2DArray() {
        int[] pixel = new int[4];
        int[][] array = new int[height()][width()];
        for (int i = 0; i < height(); i++) {
            for (int j = 0; j < width(); j++) {
                pixel = getPixel(j, i, pixel);
                array[i][j] = (pixel[0] + pixel[1] + pixel[2]) / 3;
            }
        }
        return array;
    }

    Image filter(ImageFilter filter);

    void setPixel(int x, int y, int[] rgba);

    int[] getPixel(int x, int y, int[] rgba);
}
