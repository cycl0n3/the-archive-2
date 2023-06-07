package org.lobertrand.imgproc;

import org.lobertrand.imgproc.core.ArrayImage;
import org.lobertrand.imgproc.filter.Clip;
import org.lobertrand.imgproc.filter.Convolution;
import org.lobertrand.imgproc.filter.Map;
import org.lobertrand.imgproc.tools.Show;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        var background = ArrayImage.load("/home/loic/Images/wallpapers/calm_sunset.jpg");
        var profile = ArrayImage.load("/home/loic/Images/assets/pdp/linkedin/profile_transparent_small.png");

        var image = background
                 .resize(1000, 0)
                .filter(Map.onRGB(p -> Math.pow(p - 5, 1.1)))
                .filter(Convolution.donut(12, 13))
                .filter(Clip.pixels());

        Show.frame(image);
    }
}