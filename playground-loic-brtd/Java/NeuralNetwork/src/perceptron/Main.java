package perceptron;

import core.PApplet;

/**
 * Heavily inspired by the work of Daniel Shiffman
 * https://www.youtube.com/watch?v=ntKn5TPHHAk
 */
public class Main extends PApplet {

    Perceptron perceptron;
    Point[] points;
    int learningIndex = 0;

    protected void setup() {
        size(500, 500);

        points = new Point[100];
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point();
        }

        perceptron = new Perceptron(3, 0.01f);
    }

    protected void draw() {
        background(255);
        stroke(0);

        Point p1 = new Point(-1, f(-1));
        Point p2 = new Point(1, f(1));
        line(p1.pixelX(), p1.pixelY(), p2.pixelX(), p2.pixelY());
        for (Point p : points) {
            p.show();
        }

        Point p3 = new Point(-1, perceptron.guessY(-1));
        Point p4 = new Point(1, perceptron.guessY(1));
        line(p3.pixelX(), p3.pixelY(), p4.pixelX(), p4.pixelY());


        for (Point p : points) {
            float[] inputs = {p.x, p.y, p.bias};
            float target = p.label;
            float guess = perceptron.guess(inputs);
            if (guess == target) {
                fill(0, 255, 0);
            } else {
                fill(255, 0, 0);
            }
            noStroke();
            circle(p.pixelX(), p.pixelY(), 8);
        }

        Point p = points[(learningIndex++) % points.length];
        float[] inputs = {p.x, p.y, p.bias};
        float target = p.label;
        perceptron.train(inputs, target);
        println(perceptron.weights);
    }

    float f(float x) {
        // y = mx + b
        return -0.3f * x - 0.2f;
    }

    class Point {
        float x;
        float y;
        float bias = 1;
        float label;

        Point() {
            x = random(-1, 1);
            y = random(-1, 1);
            label = y > f(x) ? 1 : -1;
        }

        Point(float x, float y) {
            this.x = x;
            this.y = y;
        }

        float pixelX() {
            return map(x, -1, 1, 0, width);
        }

        float pixelY() {
            return map(y, -1, 1, height, 0);
        }

        void show() {
            stroke(0);
            fill(label == 1 ? 255 : 0);
            circle(pixelX(), pixelY(), 16);
        }
    }

    public static void main(String[] args) {
        PApplet.run();
    }
}
