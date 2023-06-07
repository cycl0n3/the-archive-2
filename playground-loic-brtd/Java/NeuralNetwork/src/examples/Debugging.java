package examples;

import core.PApplet;
import nn.NeuralNetwork;

public class Debugging extends PApplet {

    NeuralNetwork nn;

    @Override
    protected void setup() {
        noCanvas();
        nn = new NeuralNetwork(2, 2, 1);
    }

    @Override
    protected void draw() {
        float[] inputs = new float[]{random(1), random(1)};
        println("-----------");
        println(inputs);
        println(nn.predict(inputs));
    }

    public static void main(String[] args) {
        PApplet.run();
    }

}
