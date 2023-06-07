package examples;

import core.PApplet;
import core.PImage;
import nn.NeuralNetwork;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static java.util.stream.Collectors.*;
import static mnist.MnistReader.*;

public class MnistExample extends PApplet {

    String IMAGES_PATH = "src/mnist/train-images.idx3-ubyte";
    String LABELS_PATH = "src/mnist/train-labels.idx1-ubyte";

    int SIZE = 28;

    List<float[]> inputs;
    List<float[]> targets;
    float unit;

    NeuralNetwork nn;
    public static final String JSON_FILE = "src/examples/mnist.json";

    @Override
    protected void setup() {
        size(300, 300);
        textAlign(CENTER, CENTER);
        textSize(150);

        inputs = getImages(IMAGES_PATH).stream()
                .map(this::formatInput)
                .collect(toList());
        targets = Arrays.stream(getLabels(LABELS_PATH))
                .mapToObj(this::formatTarget)
                .collect(toList());
        unit = floor((width * 1f) / SIZE + 1);

        // nn = new NeuralNetwork(SIZE * SIZE, 100, 10);
        // doTheTraining();
        // nn.save(JSON_FILE);
        // println("Model trained and saved");

        nn = NeuralNetwork.load(JSON_FILE);
        println("Model loaded");

        background(0);
    }

    @Override
    protected void draw() {
        // drawImage(inputs.get(7 % inputs.size()));

        if (mouseIsPressed) {
            stroke(255);
            strokeWeight(20);
            line(pmouseX, pmouseY, mouseX, mouseY);
        }
    }

    @Override
    protected void mouseReleased() {
        makeAGuess();
    }

    @Override
    protected void mousePressed() {
        background(0);
    }

    private void makeAGuess() {
        float[] in = canvasToInputs();
        float[] guess = nn.predict(in);
        printFormatted(guess);
        float max = guess[0];
        int indexMax = 0;
        for (int i = 0; i < guess.length; i++) {
            if (guess[i] > max) {
                max = guess[i];
                indexMax = i;
            }
        }
        fill(0, 150, 150);
        text(indexMax, width / 2f, height / 2f);
    }

    private void drawImage(float[] img) {
        background(0);
        noStroke();
        int index = 0;
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                fill(img[index++] * 255);
                rect(x * unit, y * unit, unit, unit);
            }
        }
    }

    public void doTheTraining() {
        for (int i = 0; i < inputs.size(); i++) {
            float[] in = this.inputs.get(i);
            float[] out = this.targets.get(i);
            nn.train(in, out);
        }
    }

    public float[] formatInput(int[][] a) {
        float[] res = new float[a.length * a[0].length];
        int index = 0;
        for (int[] rows : a) {
            for (int value : rows) {
                res[index++] = value / 255f;
            }
        }
        return res;
    }

    public float[] formatTarget(int label) {
        float[] res = new float[10];
        res[label] = 1;
        return res;
    }

    public float[] canvasToInputs() {
        PImage image = createImage();
        image.resize(SIZE, SIZE);
        image.loadPixels();
        float[] res = new float[image.pixels.length];
        for (int i = 0; i < image.pixels.length; i++) {
            res[i] = red(image.pixels[i]) / 255f;
        }
        return res;
    }

    public void printFormatted(float[] array) {
        String res = "[";
        for (int i = 0; i < array.length; i++) {
            res += i + ": ";
            res += String.format(Locale.ENGLISH, "%.2f", array[i]);
            if (i != array.length - 1) {
                res += ", ";
            }
        }
        res += "]";
        System.out.println(res);
    }

    public static void main(String[] args) {
        PApplet.run();
    }
}
