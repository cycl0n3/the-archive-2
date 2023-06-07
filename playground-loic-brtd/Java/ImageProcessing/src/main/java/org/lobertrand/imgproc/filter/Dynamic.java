package org.lobertrand.imgproc.filter;

import org.lobertrand.imgproc.core.Image;

public class Dynamic implements ImageFilter {

    private Dynamic() {
    }

    @Override
    public Image applyTo(Image image) {
        var bounds = computeMinMax(image);
        float span = bounds.max - bounds.min;
        return image.map((pixel, x, y) -> {
            for (int i = 0; i < 4; i++) {
                pixel[i] = Math.round(255 * (pixel[i] - bounds.min) / span);
            }
            return pixel;
        });
    }

    private Bounds computeMinMax(Image image) {
        int[] pixel = new int[4];
        int min = 0, max = 0;
        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                pixel = image.getPixel(x, y, pixel);
                for (int i = 0; i < 4; i++) {
                    min = Math.min(min, pixel[i]);
                    max = Math.max(max, pixel[i]);
                }
            }
        }
        return new Bounds(min, max);
    }

    public static Dynamic expansion() {
        return new Dynamic();
    }

    private static class Bounds {
        final int min;
        final int max;

        private Bounds(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }
}
