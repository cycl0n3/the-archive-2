package org.lobertrand.imgproc;

import org.lobertrand.imgproc.core.ArrayImage;
import org.lobertrand.imgproc.filter.Convolution;
import org.lobertrand.imgproc.tools.Show;

import java.io.IOException;

public class PerformanceTest {

    public static void main(String[] args) throws IOException {
        var VAPORWAVE = "/home/loic/Images/wallpapers/vaporwave.jpg";

        var image = ArrayImage.load(VAPORWAVE)
                .resize(700, -1);

        long sum = 0;
        long n = 1;
        for (int i = 0; i < n; i++) {
            long timeBefore = System.currentTimeMillis();
            image = image.filter(Convolution.circle(20));
            long timeAfter = System.currentTimeMillis();
            long duration = timeAfter - timeBefore;
            sum += duration;
        }
        long average = sum / n;
        System.out.println("average = " + average + " ms");

        // 1862 ms with BufferImage
        // 1808 ms with BufferImage
        // 796 ms with ArrayImage
        // 779 ms with ArrayImage

        Show.frame(image);
    }

}
