# Toy neural network

Matrix math classes and neural network in Java, directly inspired by the work of Daniel Shiffman (The Coding Train YouTube channel).

### Neural network API
```java
// Defining a neural network (input layer has 2 nodes,
// hidden layer has 3 nodes and output layer has 1 node)
nn = new NeuralNetwork(2, 3, 1);
nn.setLearningRate(0.2f);

// Giving the neural network an example of inputs and
// the expected outputs (should be done multiple times)
nn.train(data.inputs, data.targets);

// Gessing outputs from given inputs
float[] inputs = {x1, x2};
float[] guess = nn.predict(inputs);

// Saving or loading a model from/to a JSON file
nn.save("model.json");
nn = NeuralNetwork.load("model.json");
```

### Matrix math API
```java
float[][] arrayA = {
        {4, 2, 1},
        {6, 8, 3}
};
float[][] arrayB = {
        {7, 3, 2},
        {9, 6, 7},
        {2, 4, 1}
};
Matrix a = Matrix.fromArray(arrayA);
Matrix b = Matrix.fromArray(arrayB);
a.print();
b.print();

a.t()
 .scale(5)
 .add(a)
 .print()
 .sub(a)
 .mul(b)
 .print();

Random rand = new Random();
Matrix.ofSize(3, 3).fill(rand::nextFloat).print();

System.out.println(Arrays.deepToString(a.toArray()));
```