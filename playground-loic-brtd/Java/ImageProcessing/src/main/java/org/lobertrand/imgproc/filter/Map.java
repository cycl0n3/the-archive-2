package org.lobertrand.imgproc.filter;

import org.lobertrand.imgproc.core.Image;

import java.util.function.DoubleUnaryOperator;

public class Map implements ImageFilter {

    private final DoubleUnaryOperator unaryOperator;
    private final int iterationLength;

    public Map(DoubleUnaryOperator unaryOperator, boolean mapOnAlpha) {
        this.unaryOperator = unaryOperator;
        this.iterationLength = mapOnAlpha ? 4 : 3;
    }


    @Override
    public Image applyTo(Image image) {
        return image.map((pixel, x, y) -> {
            for (int i = 0; i < iterationLength; i++) {
                pixel[i] = (int) unaryOperator.applyAsDouble(pixel[i]);
            }
            return pixel;
        });
    }

    public static Map onRGB(DoubleUnaryOperator function) {
        return new Map(function, false);
    }

    public static Map onRGBA(DoubleUnaryOperator function) {
        return new Map(function, true);
    }
}
