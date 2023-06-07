package org.lobertrand.imgproc.filter;

import org.lobertrand.imgproc.core.Image;

@FunctionalInterface
public interface ImageFilter {

    /**
     * Applies a filter to a copy of the image in argument and returns it.
     *
     * @param image image to filter
     * @return new image with the filter applied
     */
    Image applyTo(Image image);

    /**
     * Returns a filter which repeats this filter n times;
     *
     * @param n number of repetition
     * @return filter repeated n times
     */
    default ImageFilter repeated(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be a positive number or zero");
        }
        return image -> {
            Image result = image;
            for (int i = 0; i < n; i++) {
                result = this.applyTo(result);
            }
            return result;
        };
    }

    /**
     * Returns a filter which applies this filter and then the other.
     *
     * @param other filter to apply after this one
     * @return composed filter
     */
    default ImageFilter then(ImageFilter other) {
        return image -> other.applyTo(this.applyTo(image));
    }

    static ImageFilter ordered(ImageFilter... filters) {
        return image -> {
            Image result = image;
            for (ImageFilter filter : filters) {
                result = filter.applyTo(result);
            }
            return result;
        };
    }
}
