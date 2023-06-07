package org.lobertrand.imgproc.filter;


import org.lobertrand.imgproc.core.ArrayImage;
import org.lobertrand.imgproc.core.Image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Blend implements ImageFilter {

    private final Image other;
    private final float amount;

    private Blend(Image other, float amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("Amount must be a float between 0 and 1");
        }
        this.other = other;
        this.amount = amount;
    }

    @Override
    public Image applyTo(Image image) {
        // return image.map((pixel, x, y) -> {
        //     if (y >= other.height() || x >= other.width()) {
        //         return pixel;
        //     }
        //     int[] otherPixel = other.getPixel(x, y, new int[4]);
        //     return blendAOnB(otherPixel, pixel);
        // });

        var bufferedImage = image.toBufferedImage();
        Graphics2D g2 = (Graphics2D) bufferedImage.getGraphics();
        Composite old = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, amount));
        g2.drawImage(other.toBufferedImage(), 0, 0, null);
        g2.setComposite(old);
        g2.dispose();
        return ArrayImage.fromBufferedImage(bufferedImage);
    }

    private static int[] blendAOnB(int[] a, int[] b) {
        int alphaA = a[3];
        int alphaB = b[3];
        int alphaOut = alphaA + alphaB * (1 - alphaA);
        int[] output = new int[]{0, 0, 0, alphaOut};
        for (int n = 0; n < 3; n++) {
            int numerator = a[n] * alphaA + b[n] * alphaB * (1 - alphaA);
            output[n] = numerator / alphaOut;
        }
        for (int n = 0; n < 4; n++) {
            output[n] = constrain(output[n], 0, 255);
        }
        return output;
    }

    public static int constrain(int c, int min, int max) {
        return c < 0 ? min : Math.min(c, max);
    }

    public static Blend image(Image other, float amount) {
        return new Blend(other, amount);
    }

    public static Blend image(Image other) {
        return new Blend(other, 1);
    }
}
