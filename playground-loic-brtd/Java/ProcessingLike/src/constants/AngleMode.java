package constants;

public enum AngleMode {

    RADIANS,
    DEGREES;

    public float toRadians(float angle) {
        if (this == RADIANS)
            return angle;
        else
            return (float) Math.toRadians(angle);
    }

    public float toDegrees(float angle) {
        if (this == RADIANS)
            return (float) Math.toDegrees(angle);
        else
            return angle;
    }

}
