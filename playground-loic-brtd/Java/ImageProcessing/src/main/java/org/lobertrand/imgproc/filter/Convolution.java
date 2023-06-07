package org.lobertrand.imgproc.filter;

import org.lobertrand.imgproc.core.Image;

import java.util.Arrays;

public class Convolution implements ImageFilter {

    private final int[][] matrix;
    private final int radius;

    private Convolution(int[][] matrix) {
        this.matrix = matrix;
        if (matrix.length % 2 == 0) {
            throw new IllegalArgumentException("Matrix must have odd dimensions");
        }
        if (matrix.length != matrix[0].length) {
            throw new IllegalArgumentException("Matrix must be a square");
        }
        radius = matrix.length / 2;
    }

    @Override
    public Image applyTo(Image image) {
        int[] averagePixel = new int[]{0, 0, 0, 255};
        return image.map((pixel, x, y) -> {
            int n = 0;
            for (int yOffset = -radius; yOffset <= radius; yOffset++) {
                for (int xOffset = -radius; xOffset <= radius; xOffset++) {
                    int xLookup = x + xOffset;
                    int yLookup = y + yOffset;
                    if (isInBounds(image, xLookup, yLookup)) {
                        int coefficient = this.matrix[yOffset + radius][xOffset + radius];
                        pixel = image.getPixel(xLookup, yLookup, pixel);
                        weightedSumInPlace(averagePixel, pixel, coefficient);
                        n += coefficient;
                    }
                }
            }
            if (n != 0) {
                divideInPlace(averagePixel, n);
            }
            return averagePixel;
        });
    }

    private void weightedSumInPlace(int[] averagePixel, int[] localPixel, int coefficient) {
        for (int i = 0; i < 3; i++) {
            averagePixel[i] += localPixel[i] * coefficient;
        }
    }

    private static void divideInPlace(int[] averagePixels, int n) {
        for (int i = 0; i < 3; i++) {
            averagePixels[i] /= n;
        }
    }

    private boolean isInBounds(Image image, int x, int y) {
        return x >= 0 && x < image.width() && y >= 0 && y < image.height();
    }

    public static Convolution matrix(int[][] matrix) {
        return new Convolution(matrix);
    }

    public static Convolution square(int radius) {
        int size = radius * 2 + 1;
        int[][] matrix = new int[size][size];
        for (int[] rows : matrix) {
            Arrays.fill(rows, 1);
        }
        return new Convolution(matrix);
    }

    public static Convolution circle(int radius) {
        int radiusSq = radius * radius;
        int size = radius * 2 + 1;
        int[][] matrix = new int[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                int distSq = (x - radius) * (x - radius) + (y - radius) * (y - radius);
                matrix[y][x] = distSq <= radiusSq ? 1 : 0;
            }
        }
        return new Convolution(matrix);
    }

    public static Convolution frame(int innerRadius, int outerRadius) {
        int borderWidth = outerRadius - innerRadius;
        int size = outerRadius * 2 + 1;
        int[][] matrix = new int[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                boolean inBorder = x < borderWidth || x >= size - borderWidth
                        || y < borderWidth || y >= size - borderWidth;
                matrix[y][x] = inBorder ? 1 : 0;
            }
        }
        return new Convolution(matrix);
    }

    public static Convolution donut(int innerRadius, int outerRadius) {
        int outRadSq = outerRadius * outerRadius;
        int inRadSq = innerRadius * innerRadius;
        int size = outerRadius * 2 + 1;
        int[][] matrix = new int[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                int distSq = (x - outerRadius) * (x - outerRadius) + (y - outerRadius) * (y - outerRadius);
                matrix[y][x] = between(distSq, inRadSq, outRadSq) ? 1 : 0;
            }
        }
        return new Convolution(matrix);
    }

    private static boolean between(int value, int min, int max) {
        return min <= value && value <= max;
    }

}
