package constants;

import java.awt.*;

import static core.PCanvas.constrain;

public enum ColorMode {

    RGB,
    HSB;

    public int makeColor(ColorMaxes colorMaxes, int a) {
        if (((a & 0xff000000) == 0) && (a <= 255)) {
            int grey = a < 0 ? 0 : a;
            float[] maxes = colorMaxes.get(this);
            if (this == RGB) {
                int gg = (int) (constrain(grey, 0, maxes[0]) / maxes[0] * 255);
                return 255 << 24 | gg << 16 | gg << 8 | gg;
            } else {
                // Supposition : hue and alpha only
                float hh = grey / maxes[2];
                return 255 << 24 | Color.HSBtoRGB(0f, 0f, hh);
            }
        } else {
            // Just return the same color
            return a;
        }
    }

    public int makeColor(ColorMaxes colorMaxes, float grey) {
        float[] maxes = colorMaxes.get(this);
        if (this == RGB) {
            int gg = (int) (constrain(grey, 0, maxes[0]) / maxes[0] * 255);
            return 255 << 24 | gg << 16 | gg << 8 | gg;
        } else {
            // Supposition : hue and alpha only
            float hh = constrain(grey, 0, maxes[0]) / maxes[0];
            return 255 << 24 | Color.HSBtoRGB(hh, 1f, 1f);
        }
    }

    public int makeColor(ColorMaxes colorMaxes, int a, float alpha) {
        float[] maxes = colorMaxes.get(this);
        int alph = (int) (constrain(alpha, 0, maxes[3]) / maxes[3] * 255);
        if (((a & 0xff000000) == 0) && (a <= 255)) {
            if (this == RGB) {
                int gg = (int) (constrain(a, 0, maxes[0]) / maxes[0] * 255);
                return alph << 24 | gg << 16 | gg << 8 | gg;
            } else {
                // Supposition : hue and alpha only
                float hh = constrain(a, 0, maxes[0]) / maxes[0];
                return alph << 24 | Color.HSBtoRGB(hh, 1f, 1f);
            }
        } else {
            // Change the alpha component of the given color
            return (a & 0x00FFFFFF) | alph << 24;
        }
    }

    public int makeColor(ColorMaxes colorMaxes, float grey, float alpha) {
        float[] maxes = colorMaxes.get(this);
        int alph = (int) (constrain(alpha, 0, maxes[3]) / maxes[3] * 255);
        if (this == RGB) {
            int gg = (int) (constrain(grey, 0, maxes[0]) / maxes[0] * 255);
            return alph << 24 | gg << 16 | gg << 8 | gg;
        } else {
            // Supposition : hue and alpha only
            float hh = constrain(grey, 0, maxes[0]) / maxes[0];
            return alph << 24 | Color.HSBtoRGB(hh, 1f, 1f);
        }
    }

    public int makeColor(ColorMaxes colorMaxes, float a, float b, float c) {
        float[] maxes = colorMaxes.get(this);
        if (this == RGB) {
            int aa = (int) (constrain(a, 0, maxes[0]) / maxes[0] * 255);
            int bb = (int) (constrain(b, 0, maxes[1]) / maxes[1] * 255);
            int cc = (int) (constrain(c, 0, maxes[2]) / maxes[2] * 255);
            return 255 << 24 | aa << 16 | bb << 8 | cc;
        } else {
            float hh = constrain(a, 0, maxes[0]) / maxes[0];
            float ss = constrain(b, 0, maxes[1]) / maxes[1];
            float bb = constrain(c, 0, maxes[2]) / maxes[2];
            return Color.HSBtoRGB(hh, ss, bb);
        }
    }

    public int makeColor(ColorMaxes colorMaxes, float a, float b, float c, float alpha) {
        float[] maxes = colorMaxes.get(this);
        int alph = (int) (constrain(alpha, 0, maxes[3]) / maxes[3] * 255);
        if (this == RGB) {
            int aa = (int) (constrain(a, 0, maxes[0]) / maxes[0] * 255);
            int bb = (int) (constrain(b, 0, maxes[1]) / maxes[1] * 255);
            int cc = (int) (constrain(c, 0, maxes[2]) / maxes[2] * 255);
            return alph << 24 | aa << 16 | bb << 8 | cc;
        } else {
            float hh = constrain(a, 0, maxes[0]) / maxes[0];
            float ss = constrain(b, 0, maxes[1]) / maxes[1];
            float bb = constrain(c, 0, maxes[2]) / maxes[2];
            return (Color.HSBtoRGB(hh, ss, bb) & 0x00FFFFFF) | alph << 24;
        }
    }

}
