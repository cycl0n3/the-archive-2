package examples;

import core.PApplet;
import nn.NeuralNetwork;

/**
 * Heavily inspired by Daniel Shiffman's playlist about creating
 * a neural network from scratch :
 * https://www.youtube.com/playlist?list=PLRqwX-V7Uu6aCibgK1PTWWu9by6XFdCfh
 */
public class XorExample extends PApplet {

    public static final Training[] XOR_TRAINING = {
            new Training(new float[]{0, 0}, new float[]{0}),
            new Training(new float[]{0, 1}, new float[]{1}),
            new Training(new float[]{1, 0}, new float[]{1}),
            new Training(new float[]{1, 1}, new float[]{0})
    };
    public static final Training[] OR_TRAINING = {
            new Training(new float[]{0, 0}, new float[]{0}),
            new Training(new float[]{0, 1}, new float[]{1}),
            new Training(new float[]{1, 0}, new float[]{1}),
            new Training(new float[]{1, 1}, new float[]{1})
    };
    NeuralNetwork nn;
    Training[] trainingData = XOR_TRAINING;
    public static final String JSON_FILE = "src/examples/xor.json";

    @Override
    protected void setup() {
        size(400, 400);
        init();
    }

    public void init() {
        nn = new NeuralNetwork(2, 3, 1);
        nn.setLearningRate(0.2f);
    }

    @Override
    protected void draw() {
        background(0);

        for (int i = 0; i < 1000; i++) {
            Training data = random(trainingData);
            nn.train(data.inputs, data.targets);
        }

        float res = 10;
        float cols = width / res;
        float rows = height / res;
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                float x1 = i / cols;
                float x2 = j / rows;
                float[] inputs = {x1, x2};
                float[] guess = nn.predict(inputs);
                noStroke();
                fill(guess[0] * 255);
                rect(i * res, j * res, res, res);
            }
        }
    }

    @Override
    protected void mousePressed() {
        init();
    }

    @Override
    protected void keyPressed() {
        if (key == 's' || key == 'S') {
            nn.save(JSON_FILE);
        } else if (key == 'l' || key == 'L') {
            nn = NeuralNetwork.load(JSON_FILE);
        }
    }

    static class Training {
        float[] inputs;
        float[] targets;

        public Training(float[] inputs, float[] targets) {
            this.inputs = inputs;
            this.targets = targets;
        }
    }

    public static void main(String[] args) {
        PApplet.run();
    }

}
