package org.lobertrand.imgproc.filter;

import org.lobertrand.imgproc.core.Image;

public class Grayscale implements ImageFilter {

    private Grayscale() {
    }

    @Override
    public Image applyTo(Image image) {
        return image.map((pixel, x, y) -> {
            int average = Math.round((pixel[0] + pixel[1] + pixel[2]) / 3f);
            pixel[0] = average;
            pixel[1] = average;
            pixel[2] = average;
            return pixel;
        });
    }

    public static Grayscale averageRGB() {
        return new Grayscale();
    }
}
