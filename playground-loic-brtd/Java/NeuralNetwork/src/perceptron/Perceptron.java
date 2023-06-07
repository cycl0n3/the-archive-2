package perceptron;

import static core.PCanvas.*;

/**
 * Heavily inspired by the work of Daniel Shiffman
 * https://www.youtube.com/watch?v=ntKn5TPHHAk
 */
public class Perceptron {

    float[] weights;
    float learningRate;

    Perceptron(int n, float lr) {
        weights = new float[n];
        learningRate = lr;
        for (int i = 0; i < weights.length; i++) {
            weights[i] = random(-1, 1);
        }
        println(weights);
    }

    int guess(float[] inputs) {
        if (inputs.length != weights.length)
            throw new IllegalArgumentException("Input length should be equal to weight length");
        float sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += inputs[i] * weights[i];
        }
        return sign(sum);
    }

    void train(float[] inputs, float target) {
        float guess = guess(inputs);
        float error = target - guess;
        for (int i = 0; i < weights.length; i++) {
            weights[i] += error * inputs[i] * learningRate;
        }
    }

    float guessY(float x) {
        float w2 = weights[2];
        float w1 = weights[1];
        float w0 = weights[0];
        return -(w2 / w1) - (w0 / w1) * x;
    }

    int sign(float x) {
        return x > 0 ? 1 : -1;
    }
}
