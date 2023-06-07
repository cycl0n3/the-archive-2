package matrix;

import java.util.Arrays;
import java.util.Locale;

import static java.lang.String.*;
import static java.lang.String.format;

public class Matrix {

    public final int rows, cols;
    private final float[][] internal;

    private Matrix(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Rows and columns must be positive integers");
        }
        this.rows = rows;
        this.cols = cols;
        this.internal = new float[rows][cols];
    }

    public static Matrix ofSize(int rows, int cols) {
        return new Matrix(rows, cols);
    }

    public static Matrix fromArray(float[][] a) {
        Matrix m = new Matrix(a.length, a[0].length);
        for (int i = 0; i < m.rows; i++) {
            System.arraycopy(a[i], 0, m.internal[i], 0, m.cols);
        }
        return m;
    }

    public static Matrix fromArray(float[] a) {
        Matrix m = new Matrix(a.length, 1);
        for (int i = 0; i < m.rows; i++) {
            m.internal[i][0] = a[i];
        }
        return m;
    }

    public float[][] toArray() {
        float[][] copy = new float[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(internal[i], 0, copy[i], 0, cols);
        }
        return copy;
    }

    public float[] toFlatArray() {
        float[] res = new float[rows * cols];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res[index++] = internal[i][j];
            }
        }
        return res;
    }

    public float get(int rows, int cols) {
        return internal[rows][cols];
    }

    public Matrix fill(FloatSupplier supplier) {
        Matrix m = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                m.internal[i][j] = supplier.get();
            }
        }
        return m;
    }

    public Matrix fill(float value) {
        Matrix m = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                m.internal[i][j] = value;
            }
        }
        return m;
    }

    public Matrix t() {
        Matrix m = new Matrix(cols, rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                m.internal[j][i] = internal[i][j];
            }
        }
        return m;
    }

    private Matrix elementWiseOperation(Matrix b, FloatBinaryOperator operator) {
        if (rows != b.rows || cols != b.cols) {
            String msg = format("Dimensions do not match: (%d,%d) vs (%d,%d)", rows, cols, b.rows, b.cols);
            throw new IllegalArgumentException(msg);
        }
        Matrix m = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                m.internal[i][j] = operator.apply(internal[i][j], b.internal[i][j]);
            }
        }
        return m;
    }

    public Matrix hadamard(Matrix b) {
        return elementWiseOperation(b, (u, v) -> u * v);
    }

    public Matrix add(Matrix b) {
        return elementWiseOperation(b, Float::sum);
    }

    public Matrix sub(Matrix b) {
        return elementWiseOperation(b, (u, v) -> u - v);
    }

    public Matrix mul(Matrix b) {
        if (cols != b.rows) {
            String msg = format("Columns of a must match rows of b (%d != %d)", cols, b.rows);
            throw new IllegalArgumentException(msg);
        }
        Matrix c = new Matrix(rows, b.cols);
        for (int i = 0; i < c.rows; i++) {
            for (int j = 0; j < c.cols; j++) {
                float sum = 0;
                for (int k = 0; k < cols; k++) {
                    sum += internal[i][k] * b.internal[k][j];
                }
                c.internal[i][j] = sum;
            }
        }
        return c;
    }

    public Matrix map(FloatUnaryOperator operator) {
        Matrix m = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                m.internal[i][j] = operator.apply(internal[i][j]);
            }
        }
        return m;
    }

    public Matrix scale(float x) {
        return map(e -> e * x);
    }

    public Matrix add(float x) {
        return map(e -> e + x);
    }

    public Matrix sub(float x) {
        return map(e -> e - x);
    }

    public Matrix div(float x) {
        return map(e -> e / x);
    }

    public Matrix print() {
        System.out.println(toString());
        return this;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(internal)
                .replaceAll("], ", "],\n ");
    }

}
