package test;

import core.PApplet;
import core.PImage;

import java.util.Arrays;

public class SortPixels extends PApplet {

    PImage img = loadImage("/home/loic/Images/wallpapers/vaporwave.jpg");

    @Override
    protected void setup() {
        img.resize(600, 0);
        size(img.width, img.height);
        image(img, 0, 0);
        noLoop();
    }

    @Override
    protected void draw() {
        img.loadPixels();
        // shuffle(img.pixels);
        Arrays.sort(img.pixels);
        img.updatePixels();
        image(img, 0, 0);
    }

    public static void main(String[] args) {
        PApplet.run();
    }

}
