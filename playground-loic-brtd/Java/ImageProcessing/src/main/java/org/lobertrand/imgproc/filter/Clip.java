package org.lobertrand.imgproc.filter;

import org.lobertrand.imgproc.core.Image;

public class Clip implements ImageFilter {

    private final int min;
    private final int max;

    private Clip(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public Image applyTo(Image image) {
        return image.map((pixel, x, y) -> {
            for (int i = 0; i < 4; i++) {
                pixel[i] = constrain(pixel[i], min, max);
            }
            return pixel;
        });
    }

    public static int constrain(int c, int min, int max) {
        return c < 0 ? min : Math.min(c, max);
    }

    public static Clip between(int min, int max) {
        return new Clip(min, max);
    }

    public static Clip pixels() {
        return new Clip(0, 255);
    }
}
