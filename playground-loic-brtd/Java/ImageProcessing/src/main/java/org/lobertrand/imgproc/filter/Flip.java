package org.lobertrand.imgproc.filter;

import org.lobertrand.imgproc.core.Image;

public class Flip implements ImageFilter {

    private final boolean horizontal;

    private Flip(boolean horizontal) {
        this.horizontal = horizontal;
    }

    @Override
    public Image applyTo(Image image) {
        return image.map((pixel, x, y) -> {
            int flippedX = horizontal ? image.width() - 1 - x : x;
            int flippedY = horizontal ? y : image.height() - 1 - y;
            return image.getPixel(flippedX, flippedY, pixel);
        });
    }

    public static Flip horizontally() {
        return new Flip(true);
    }

    public static Flip vertically() {
        return new Flip(false);
    }
}
