package matrix.test;

import matrix.Matrix;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {

    public static final float[][] ARRAY_123_456 = {
            {1, 2, 3},
            {4, 5, 6}
    };
    public static final float[][] ARRAY_987_654 = {
            {9, 8, 7},
            {6, 5, 4}
    };
    public static final float[] ARRAY_123 = {1, 2, 3};

    @Nested
    class OfSize {

        @Test
        public void validDimensions() {
            Matrix m = Matrix.ofSize(3, 2);
            assertEquals(3, m.rows);
            assertEquals(2, m.cols);
            assertEquals(0, m.get(0, 0));
            assertEquals(0, m.get(0, 1));
            assertEquals(0, m.get(1, 0));
            assertEquals(0, m.get(1, 1));
            assertEquals(0, m.get(2, 0));
            assertEquals(0, m.get(2, 1));
        }

        @Test
        public void negativeDimensions() {
            assertThrows(IllegalArgumentException.class, () -> Matrix.ofSize(-1, -1));
            assertThrows(IllegalArgumentException.class, () -> Matrix.ofSize(1, -1));
            assertThrows(IllegalArgumentException.class, () -> Matrix.ofSize(-1, 1));
        }

        @Test
        public void zeroDimensions() {
            assertThrows(IllegalArgumentException.class, () -> Matrix.ofSize(0, 0));
            assertThrows(IllegalArgumentException.class, () -> Matrix.ofSize(1, 0));
            assertThrows(IllegalArgumentException.class, () -> Matrix.ofSize(0, 1));
        }
    }

    @Nested
    class FromArray {

        @Test
        public void array1D() {
            Matrix m = Matrix.fromArray(ARRAY_123);
            assertEquals(3, m.rows);
            assertEquals(1, m.cols);
            assertEquals(1, m.get(0, 0));
            assertEquals(2, m.get(1, 0));
            assertEquals(3, m.get(2, 0));
        }

        @Test
        public void array2D() {
            Matrix m = Matrix.fromArray(ARRAY_123_456);
            assertEquals(2, m.rows);
            assertEquals(3, m.cols);
            assertEquals(1, m.get(0, 0));
            assertEquals(2, m.get(0, 1));
            assertEquals(3, m.get(0, 2));
            assertEquals(4, m.get(1, 0));
            assertEquals(5, m.get(1, 1));
            assertEquals(6, m.get(1, 2));
        }

        @Test
        public void array1DNull() {
            assertThrows(NullPointerException.class,
                    () -> Matrix.fromArray((float[]) null));
        }

        @Test
        public void array2DNull() {
            assertThrows(NullPointerException.class,
                    () -> Matrix.fromArray((float[][]) null));
        }

    }

    @Nested
    class ToArray {

        @Test
        public void array1D() {
            Matrix m = Matrix.fromArray(ARRAY_123);
            float[][] a = m.toArray();
            assertEquals(3, a.length);
            assertEquals(1, a[0].length);
            assertEquals(1, a[0][0]);
            assertEquals(2, a[1][0]);
            assertEquals(3, a[2][0]);
        }

        @Test
        public void array2D() {
            Matrix m = Matrix.fromArray(ARRAY_123_456);
            float[][] a = m.toArray();
            assertEquals(2, a.length);
            assertEquals(3, a[0].length);
            assertEquals(3, a[1].length);
            assertEquals(1, a[0][0]);
            assertEquals(2, a[0][1]);
            assertEquals(3, a[0][2]);
            assertEquals(4, a[1][0]);
            assertEquals(5, a[1][1]);
            assertEquals(6, a[1][2]);
        }

    }

    @Nested
    class ToFlatArray {

        @Test
        public void array1D() {
            Matrix m = Matrix.fromArray(ARRAY_123);
            float[] a = m.toFlatArray();
            assertEquals(a.length, 3);
            assertEquals(1, a[0]);
            assertEquals(2, a[1]);
            assertEquals(3, a[2]);
        }

        @Test
        public void array2D() {
            Matrix m = Matrix.fromArray(ARRAY_123_456);
            float[] a = m.toFlatArray();
            assertEquals(a.length, 6);
            assertEquals(1, a[0]);
            assertEquals(2, a[1]);
            assertEquals(3, a[2]);
            assertEquals(4, a[3]);
            assertEquals(5, a[4]);
            assertEquals(6, a[5]);
        }

    }

    @Nested
    class Fill {

        @Test
        public void number() {
            Matrix m = Matrix.ofSize(2, 3).fill(42);
            assertEquals(42, m.get(0, 0));
            assertEquals(42, m.get(0, 1));
            assertEquals(42, m.get(0, 2));
            assertEquals(42, m.get(1, 0));
            assertEquals(42, m.get(1, 1));
            assertEquals(42, m.get(1, 2));
        }

        @Test
        public void generator() {
            AtomicInteger i = new AtomicInteger();
            Matrix m = Matrix.ofSize(2, 3).fill(i::incrementAndGet);
            assertEquals(1, m.get(0, 0));
            assertEquals(2, m.get(0, 1));
            assertEquals(3, m.get(0, 2));
            assertEquals(4, m.get(1, 0));
            assertEquals(5, m.get(1, 1));
            assertEquals(6, m.get(1, 2));
        }

    }

    @Nested
    class Transposition {

        @Test
        public void matrix1D() {
            Matrix m = Matrix.fromArray(ARRAY_123);
            Matrix t = m.t();
            assertEquals(1, t.rows);
            assertEquals(3, t.cols);
            assertEquals(1, t.get(0, 0));
            assertEquals(2, t.get(0, 1));
            assertEquals(3, t.get(0, 2));
        }

        @Test
        public void matrix2D() {
            Matrix m = Matrix.fromArray(ARRAY_123_456);
            Matrix t = m.t();
            assertEquals(3, t.rows);
            assertEquals(2, t.cols);
            assertEquals(1, t.get(0, 0));
            assertEquals(4, t.get(0, 1));
            assertEquals(2, t.get(1, 0));
            assertEquals(5, t.get(1, 1));
            assertEquals(3, t.get(2, 0));
            assertEquals(6, t.get(2, 1));
        }

    }

    @Nested
    class Hadamard {

        @Test
        public void matrix2D() {
            Matrix a = Matrix.fromArray(ARRAY_123_456);
            Matrix b = Matrix.fromArray(ARRAY_987_654);
            Matrix res = a.hadamard(b);
            assertEquals(2, res.rows);
            assertEquals(3, res.cols);
            assertEquals(9, res.get(0, 0));
            assertEquals(16, res.get(0, 1));
            assertEquals(21, res.get(0, 2));
            assertEquals(24, res.get(1, 0));
            assertEquals(25, res.get(1, 1));
            assertEquals(24, res.get(1, 2));
        }

    }

    @Nested
    class Add {

        @Test
        public void otherMatrix() {
            Matrix a = Matrix.fromArray(ARRAY_123_456);
            Matrix b = Matrix.fromArray(ARRAY_987_654);
            Matrix res = a.add(b);
            assertEquals(2, res.rows);
            assertEquals(3, res.cols);
            assertEquals(10, res.get(0, 0));
            assertEquals(10, res.get(0, 1));
            assertEquals(10, res.get(0, 2));
            assertEquals(10, res.get(1, 0));
            assertEquals(10, res.get(1, 1));
            assertEquals(10, res.get(1, 2));
        }

        @Test
        public void scalar() {
            Matrix a = Matrix.fromArray(ARRAY_123_456);
            Matrix res = a.add(3);
            assertEquals(2, res.rows);
            assertEquals(3, res.cols);
            assertEquals(4, res.get(0, 0));
            assertEquals(5, res.get(0, 1));
            assertEquals(6, res.get(0, 2));
            assertEquals(7, res.get(1, 0));
            assertEquals(8, res.get(1, 1));
            assertEquals(9, res.get(1, 2));
        }

    }

    @Nested
    class Sub {

        @Test
        public void otherMatrix() {
            Matrix a = Matrix.fromArray(ARRAY_987_654);
            Matrix b = Matrix.fromArray(ARRAY_123_456);
            Matrix res = a.sub(b);
            assertEquals(2, res.rows);
            assertEquals(3, res.cols);
            assertEquals(8, res.get(0, 0));
            assertEquals(6, res.get(0, 1));
            assertEquals(4, res.get(0, 2));
            assertEquals(2, res.get(1, 0));
            assertEquals(0, res.get(1, 1));
            assertEquals(-2, res.get(1, 2));
        }

        @Test
        public void scalar() {
            Matrix a = Matrix.fromArray(ARRAY_123_456);
            Matrix res = a.sub(3);
            assertEquals(2, res.rows);
            assertEquals(3, res.cols);
            assertEquals(-2, res.get(0, 0));
            assertEquals(-1, res.get(0, 1));
            assertEquals(0, res.get(0, 2));
            assertEquals(1, res.get(1, 0));
            assertEquals(2, res.get(1, 1));
            assertEquals(3, res.get(1, 2));
        }

    }

    @Nested
    class Mul {

        @Test
        public void otherMatrix() {
            Matrix a = Matrix.fromArray(ARRAY_123_456);
            Matrix b = Matrix.fromArray(ARRAY_123);
            Matrix res = a.mul(b);
            assertEquals(2, res.rows);
            assertEquals(1, res.cols);
            assertEquals(14, res.get(0, 0));
            assertEquals(32, res.get(1, 0));
        }

    }

    @Nested
    class Scale {

        @Test
        public void scalar() {
            Matrix a = Matrix.fromArray(ARRAY_123_456);
            Matrix res = a.scale(2);
            assertEquals(2, res.rows);
            assertEquals(3, res.cols);
            assertEquals(2, res.get(0, 0));
            assertEquals(4, res.get(0, 1));
            assertEquals(6, res.get(0, 2));
            assertEquals(8, res.get(1, 0));
            assertEquals(10, res.get(1, 1));
            assertEquals(12, res.get(1, 2));
        }

    }


}
