package examples;

import core.PApplet;
import core.PImage;
import nn.NeuralNetwork;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class CatsDogsExample extends PApplet {

    String JSON_FILE = "src/examples/catsdogs.json";
    String IMAGES_DIR_PATH = "/home/loic/Documents/data/catsdogs/resized";

    List<Training> trainingData;
    NeuralNetwork nn;

    @Override
    protected void setup() {
        size(300, 300);

        trainingData = loadTrainingDataset(IMAGES_DIR_PATH);

        // nn = new NeuralNetwork(28 * 28, 100, 2);
        // nn.setLearningRate(0.1f);
        // doTheTraining();

        nn = NeuralNetwork.load(JSON_FILE);
        println("Model loaded");

        noLoop();
    }

    @Override
    protected void mousePressed() {
        Training data = random(trainingData);
        image(data.img, 0, 0, width, height);
        float[] guess = nn.predict(data.toInputs());
        Type guessType = maxIndex(guess) == 0 ? Type.CAT : Type.DOG;
        println(guess);
        println("guessed", guessType);
        println("should be", data.type);
        redraw();
    }

    @Override
    protected void keyPressed() {
        if (key == 's' || key == 'S') {
            nn.save(JSON_FILE);
            println("Model saved");
        } else if (key == 'l' || key == 'L') {
            nn = NeuralNetwork.load(JSON_FILE);
            println("Model loaded");
        }
    }

    public int maxIndex(float[] array) {
        int maxIndex = 0;
        float max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                maxIndex = i;
                max = array[i];
            }
        }
        return maxIndex;
    }

    private void doTheTraining() {
        for (int i = 0; i < trainingData.size(); i++) {
            println((i + 1) * 100 / trainingData.size() + "%");
            Training data = trainingData.get(i);
            float[] inputs = data.toInputs();
            float[] targets = data.toTargets();
            nn.train(inputs, targets);
        }
    }

    private List<Training> loadTrainingDataset(String path) {
        List<Training> list;
        File dir = new File(path);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            list = new ArrayList<>(directoryListing.length);
            println("Found " + directoryListing.length + " files");
            for (File file : directoryListing) {
                PImage img = new PImage(file.getPath());
                Type type = file.getName().startsWith("dog") ? Type.DOG : Type.CAT;
                list.add(new Training(img, type));
            }
            Collections.shuffle(list);
            println("Data loaded");
        } else {
            println("Not a directory");
            throw new IllegalStateException();
        }
        return list;
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

    enum Type {
        CAT, DOG;
    }

    static class Training {

        final PImage img;
        final Type type;
        public Training(PImage img, Type type) {
            this.img = img;
            this.type = type;
        }

        private float[] toInputs() {
            PImage image = img;
            image.grayScale();
            image.loadPixels();
            float[] inputs = new float[image.pixels.length];
            for (int i = 0; i < image.pixels.length; i++) {
                inputs[i] = red(image.pixels[i]) / 255f;
            }
            return inputs;
        }

        private float[] toTargets() {
            float[] targets = new float[2];
            int index = type == Type.CAT ? 0 : 1;
            targets[index] = 1;
            return targets;
        }

    }

    public static void main(String[] args) {
        PApplet.run();
    }
}
