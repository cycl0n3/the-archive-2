package nn;

import com.google.gson.GsonBuilder;
import matrix.Matrix;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.util.stream.Collectors.*;

public class NeuralNetwork {

    private final int inputSize;
    private final int hiddenSize;
    private final int outputSize;

    private Matrix weightsIH;
    private Matrix weightsHO;

    private Matrix biasH;
    private Matrix biasO;

    private float learningRate = 0.2f;

    public NeuralNetwork(int inputSize, int hiddenSize, int outputSize) {
        this.inputSize = inputSize;
        this.hiddenSize = hiddenSize;
        this.outputSize = outputSize;
        this.weightsIH = Matrix.ofSize(hiddenSize, inputSize).fill(Utils::randomFloat);
        this.weightsHO = Matrix.ofSize(outputSize, hiddenSize).fill(Utils::randomFloat);
        this.biasH = Matrix.ofSize(hiddenSize, 1).fill(Utils::randomFloat);
        this.biasO = Matrix.ofSize(outputSize, 1).fill(Utils::randomFloat);
    }

    public float[] predict(float[] inputArray) {
        Matrix inputs = Matrix.fromArray(inputArray);

        Matrix hidden = weightsIH
                .mul(inputs)
                .add(biasH)
                .map(Activation::sigmoid);

        Matrix outputs = weightsHO
                .mul(hidden)
                .add(biasO)
                .map(Activation::sigmoid);

        return outputs.toFlatArray();
    }

    public void train(float[] inputArray, float[] targetArray) {
        Matrix inputs = Matrix.fromArray(inputArray);
        Matrix hidden = weightsIH
                .mul(inputs)
                .add(biasH)
                .map(Activation::sigmoid);
        Matrix outputs = weightsHO
                .mul(hidden)
                .add(biasO)
                .map(Activation::sigmoid);

        Matrix targets = Matrix.fromArray(targetArray);
        Matrix outputErrors = targets.sub(outputs);
        Matrix gradients = outputs
                .map(Activation::dsigmoid)
                .hadamard(outputErrors)
                .scale(learningRate);
        Matrix deltaHO = gradients.mul(hidden.t());
        weightsHO = weightsHO.add(deltaHO);
        biasO = biasO.add(gradients);

        Matrix hiddenErrors = weightsHO.t().mul(outputErrors);
        Matrix hiddenGradient = hidden
                .map(Activation::dsigmoid)
                .hadamard(hiddenErrors)
                .scale(learningRate);
        Matrix deltaIH = hiddenGradient.mul(inputs.t());
        weightsIH = weightsIH.add(deltaIH);
        biasH = biasH.add(hiddenGradient);
    }

    public void setLearningRate(float learningRate) {
        this.learningRate = learningRate;
    }

    public String toJson() {
        return new GsonBuilder().create().toJson(this);
    }

    public static NeuralNetwork fromJson(String jsonString) {
        return new GsonBuilder().create().fromJson(jsonString, NeuralNetwork.class);
    }

    public void save(String jsonFile) {
        Path path = Paths.get(jsonFile);
        byte[] strToBytes = toJson().getBytes();
        try {
            Files.write(path, strToBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static NeuralNetwork load(String jsonFile) {
        String jsonString;
        try {
            jsonString = Files.lines(Paths.get(jsonFile)).collect(joining());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return fromJson(jsonString);
    }

}
