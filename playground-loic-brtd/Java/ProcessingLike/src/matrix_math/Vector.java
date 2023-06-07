package matrix_math;

import java.util.function.Consumer;
import java.util.function.Function;

public class Vector extends Matrix {

    private Vector(float... values) {
        super(valuesToArray(values));
    }

    public static Vector of(float... values) {
        return new Vector(values);
    }

    public float x() {
        return array[0][0];
    }

    public float y() {
        return array[1][0];
    }

    public float z() {
        return array[2][0];
    }

    public float w() {
        return array[3][0];
    }

    public float get(int index) {
        return array[index][0];
    }

    public Vector copy() {
        float[] copy = new float[rows];
        for (int j = 0; j < rows; j++) {
            copy[j] = array[j][0];
        }
        return new Vector(copy);
    }

    public Vector forEach(Consumer<Float> consumer) {
        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < cols; i++) {
                consumer.accept(array[j][i]);
            }
        }
        return this;
    }

    public Vector map(Function<Float, Float> function) {
        Vector copy = this.copy();
        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < cols; i++) {
                copy.array[j][i] = function.apply(array[j][i]);
            }
        }
        return copy;
    }

    public Vector mult(float value) {
        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < cols; i++) {
                array[j][i] *= value;
            }
        }
        return this;
    }

    private static float[][] valuesToArray(float... values) {
        final float[][] res = new float[values.length][1];
        for (int j = 0; j < res.length; j++) {
            res[j][0] = values[j];
        }
        return res;
    }

}
