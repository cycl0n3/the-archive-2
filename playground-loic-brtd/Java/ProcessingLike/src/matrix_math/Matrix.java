package matrix_math;

import java.util.function.Consumer;
import java.util.function.Function;

public class Matrix {

    protected float[][] array;
    protected int cols, rows;

    protected Matrix(float[][] array) {
        this.array = array;
        rows = this.array.length;
        cols = this.array[0].length;
    }

    public static Matrix of(float[][] array) {
        return new Matrix(array);
    }

    public static Matrix identity(int size) {
        final float[][] res = new float[size][size];
        for (int n = 0; n < size; n++) {
            res[n][n] = 1;
        }
        return new Matrix(res);
    }

    public static Matrix prod(Matrix a, Matrix b) {
        if (a.cols != b.rows) {
            throw new IllegalArgumentException("Columns of 'a' must match rows of 'b'");
        }
        final float[][] res = new float[a.rows][b.cols];
        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < b.cols; j++) {
                for (int k = 0; k < a.cols; k++) {
                    res[i][j] += a.array[i][k] * b.array[k][j];
                }
            }
        }
        return new Matrix(res);
    }

    public static Vector prod(Matrix a, Vector b) {
        return prod(a, (Matrix) b).toVector();
    }

    public Matrix copy() {
        float[][] copy = new float[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                copy[i][j] = array[i][j];
            }
        }
        return new Matrix(copy);
    }

    public Matrix map(Function<Float, Float> function) {
        Matrix copy = this.copy();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                copy.array[i][j] = function.apply(array[i][j]);
            }
        }
        return copy;
    }

    public float get(int col, int row) {
        return array[row][col];
    }

    public Matrix forEach(Consumer<Float> consumer) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                consumer.accept(array[i][j]);
            }
        }
        return this;
    }

    public Matrix mul(float value) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] *= value;
            }
        }
        return this;
    }

    public Matrix add(float value) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] += value;
            }
        }
        return this;
    }

    public Matrix add(Matrix other) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] += other.array[i][j];
            }
        }
        return this;
    }

    public Matrix sub(float value) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] -= value;
            }
        }
        return this;
    }

    public Matrix div(float value) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] /= value;
            }
        }
        return this;
    }

    public Vector toVector() {
        if (cols != 1) {
            throw new IllegalArgumentException("There must be only one column to convert to a Vector");
        }
        final float[] values = new float[array.length];
        for (int i = 0; i < values.length; i++) {
            values[i] = array[i][0];
        }
        return Vector.of(values);
    }

    public void print() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("rows x cols: " + rows + " x " + cols + System.lineSeparator());
        for (int i = 0; i < rows; i++) {
            builder.append(i == 0 ? "[[" : " [");
            for (int j = 0; j < cols; j++) {
                builder.append(" ").append(array[i][j]);
                if (j != cols - 1) {
                    builder.append(",");
                }
            }
            builder.append(i == rows - 1 ? " ]]" : " ]");
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }

}
