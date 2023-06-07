# Processing Like

Recreation of the Processing creative coding environment.

### Example of a simple program using this framework

```java
package test;

import core.PApplet;
import core.PImage;

import java.util.Arrays;

public class SortingImagePixels extends PApplet {

    PImage img;

    @Override
    protected void setup() {
        // Loading and resizing an image (600 pixels for the width)
        img = loadImage("image.jpg");
        img.resize(600, 0);
        // Size of the canvas
        size(img.width, img.height);
        // Drawing an image on the canvas
        image(img, 0, 0);
    }

    @Override
    protected void draw() {
        img.loadPixels();
        Arrays.sort(img.pixels);
        img.updatePixels();
        image(img, 0, 0);
    }

    public static void main(String[] args) {
        PApplet.run();
    }
}
```