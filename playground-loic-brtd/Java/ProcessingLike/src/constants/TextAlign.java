package constants;

import java.awt.*;

public class TextAlign {

    private final Alignment horizAlign;
    private final Alignment vertAlign;

    public TextAlign() {
        horizAlign = Alignment.LEFT;
        vertAlign = Alignment.BASELINE;
    }

    public TextAlign(Alignment horizAlign, Alignment vertAlign) {
        this.horizAlign = horizAlign;
        this.vertAlign = vertAlign;
    }

    public TextAlign(TextAlign other) {
        horizAlign = other.horizAlign;
        vertAlign = other.vertAlign;
    }

    public void adaptOrigin(Point.Float point, float x, float y, String text, Graphics2D g2) {
        int textAscent = g2.getFontMetrics().getAscent();
        int textDescent = g2.getFontMetrics().getDescent();
        int textWidth = g2.getFontMetrics().stringWidth(text);

        // Horizontal alignment
        if (horizAlign == Alignment.RIGHT) {
            x = x - textWidth;
        } else if (horizAlign == Alignment.CENTER) {
            x = x - textWidth / 2f;
        }

        // Vertical alignment
        if (vertAlign == Alignment.TOP) {
            y = y + textAscent;
        } else if (vertAlign == Alignment.BOTTOM) {
            y = y - textDescent;
        } else if (vertAlign == Alignment.CENTER) {
            y = y - textDescent + (textAscent + textDescent) / 2f;
        }

        point.setLocation(x, y);
    }

    public Alignment getHorizAlign() {
        return horizAlign;
    }

    public Alignment getVertAlign() {
        return vertAlign;
    }

}
