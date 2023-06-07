package constants;

import java.awt.*;

public enum StrokeJoin {

    MITER(BasicStroke.JOIN_MITER),
    BEVEL(BasicStroke.JOIN_BEVEL),
    ROUND(BasicStroke.JOIN_ROUND);

    private int value;

    StrokeJoin(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
