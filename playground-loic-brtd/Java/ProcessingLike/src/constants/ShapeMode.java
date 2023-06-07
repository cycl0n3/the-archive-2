package constants;

import java.awt.geom.Rectangle2D;

public enum ShapeMode {

    CENTER,
    CORNER;

    public void adaptBounds(Rectangle2D.Float bounds, float x, float y, float w, float h) {
        if (this == CENTER) {
            bounds.setFrame(x - w / 2, y - h / 2, w, h);
        } else {
            bounds.setFrame(x, y, w, h);
        }
    }

}
