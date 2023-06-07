package core;

public class PVector {

    public float x, y, z;

    public PVector() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public PVector(float x, float y) {
        this.x = x;
        this.y = y;
        this.z = 0;
    }

    public PVector(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public PVector set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public PVector set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public PVector add(PVector other) {
        this.x += other.x;
        this.y += other.y;
        this.z += other.z;
        return this;
    }

    public PVector add(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public PVector sub(PVector other) {
        this.x -= other.x;
        this.y -= other.y;
        this.z -= other.z;
        return this;
    }

    public PVector sub(float x, float y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public PVector mult(float value) {
        this.x *= value;
        this.y *= value;
        this.z *= value;
        return this;
    }

    public PVector div(float value) {
        this.x /= value;
        this.y /= value;
        this.z /= value;
        return this;
    }

    public PVector setMag(float mag) {
        float actual = this.mag();
        return this.mult(mag / actual);
    }

    public PVector rotate(float angle) {

        return this;
    }

    public static PVector fromAngle(float angle) {
        final float x = (float) (Math.cos(angle));
        final float y = (float) (Math.sin(angle));
        return new PVector(x, y, 0);
    }

    public static PVector random2D() {
        return fromAngle((float) (Math.random() * Math.PI * 2));
    }

    public static PVector add(PVector a, PVector b) {
        return a.copy().add(b);
    }

    public static PVector sub(PVector a, PVector b) {
        return a.copy().sub(b);
    }

    public static PVector mult(PVector a, float value) {
        return a.copy().mult(value);
    }

    public static PVector div(PVector a, float value) {
        return a.copy().div(value);
    }

    public static float dist(PVector a, PVector b) {
        return (float) Math.sqrt(
                (b.x - a.x) * (b.x - a.x)
                        + (b.y - a.y) * (b.y - a.y)
                        + (b.z - a.z) * (b.z - a.z));
    }

    public static float distSq(PVector a, PVector b) {
        return (b.x - a.x) * (b.x - a.x)
                + (b.y - a.y) * (b.y - a.y)
                + (b.z - a.z) * (b.z - a.z);
    }

    public float heading() {
        return (float) Math.atan2(y, x);
        // return (float) Math.atan(y / x);
    }

    public float mag() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public float magSq() {
        return x * x + y * y + z * z;
    }

    public PVector copy() {
        return new PVector(x, y, z);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(x);
        result = prime * result + Float.floatToIntBits(y);
        result = prime * result + Float.floatToIntBits(z);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PVector other = (PVector) obj;
        if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
            return false;
        if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
            return false;
        if (Float.floatToIntBits(z) != Float.floatToIntBits(other.z))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PVector [x=" + x + ", y=" + y + ", z=" + z + "]";
    }

}
