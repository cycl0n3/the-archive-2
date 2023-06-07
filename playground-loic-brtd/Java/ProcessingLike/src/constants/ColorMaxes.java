package constants;

import java.util.HashMap;

import static constants.ColorMode.HSB;
import static constants.ColorMode.RGB;

public class ColorMaxes {

    private final HashMap<ColorMode, float[]> maxes;

    public ColorMaxes() {
        maxes = new HashMap<>();
        maxes.put(RGB, new float[] { 255, 255, 255, 255 });
        maxes.put(HSB, new float[] { 360, 100, 100, 1 });
    }

    public ColorMaxes set(ColorMode mode, float max) {
        final ColorMaxes copy = copy();
        copy.maxes.get(mode)[0] = max;
        copy.maxes.get(mode)[1] = max;
        copy.maxes.get(mode)[2] = max;
        copy.maxes.get(mode)[3] = max;
        return copy;
    }

    public ColorMaxes set(ColorMode mode, float max1, float max2, float max3) {
        final ColorMaxes copy = copy();
        copy.maxes.get(mode)[0] = max1;
        copy.maxes.get(mode)[1] = max2;
        copy.maxes.get(mode)[2] = max3;
        copy.maxes.get(mode)[3] = maxes.get(mode)[3];
        return copy;
    }

    public ColorMaxes set(ColorMode mode, float max1, float max2, float max3, float maxAlpha) {
        final ColorMaxes copy = copy();
        copy.maxes.get(mode)[0] = max1;
        copy.maxes.get(mode)[1] = max2;
        copy.maxes.get(mode)[2] = max3;
        copy.maxes.get(mode)[3] = maxAlpha;
        return copy;
    }

    public float[] get(ColorMode mode) {
        final float[] src = maxes.get(mode);
        final float[] dest = new float[src.length];
        System.arraycopy(src, 0, dest, 0, src.length);
        return dest;
    }

    private ColorMaxes copy() {
        final ColorMaxes copy = new ColorMaxes();
        System.arraycopy(maxes.get(RGB), 0, copy.maxes.get(RGB), 0, 4);
        System.arraycopy(maxes.get(HSB), 0, copy.maxes.get(HSB), 0, 4);
        return copy;
    }

}
