package constants;

import java.awt.geom.Arc2D;

public enum ArcMode {

    OPEN(Arc2D.OPEN, Arc2D.OPEN),
    PIE(Arc2D.PIE, Arc2D.PIE),
    CHORD(Arc2D.CHORD, Arc2D.CHORD),
    DEFAULT(Arc2D.PIE, Arc2D.OPEN);

    private int fillMode;
    private int strokeMode;

    ArcMode(int fillMode, int strokeMode) {
        this.fillMode = fillMode;
        this.strokeMode = strokeMode;
    }

    public int getFillMode() {
        return fillMode;
    }

    public int getStrokeMode() {
        return strokeMode;
    }

}
