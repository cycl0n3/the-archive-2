package core;

import constants.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.StringJoiner;

public class StyleSettings {

    private int fillColor;
    private int strokeColor;
    private ColorMode colorMode;
    private ColorMaxes colorMaxes;
    private BasicStroke stroke;
    private ShapeMode rectMode;
    private ShapeMode ellipseMode;
    private Font font;
    private TextAlign textAlign;
    private AngleMode angleMode;
    private AffineTransform transform;
    private boolean filled;
    private boolean stroked;

    public StyleSettings() {
        fillColor = 0xFFFFFFFF;
        filled = true;
        strokeColor = 0xFF000000;
        stroked = true;
        colorMode = ColorMode.RGB;
        colorMaxes = new ColorMaxes();
        stroke = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        rectMode = ShapeMode.CORNER;
        ellipseMode = ShapeMode.CENTER;
        font = new Font("Monospaced", Font.PLAIN, 12);
        textAlign = new TextAlign();
        angleMode = AngleMode.RADIANS;
        transform = new AffineTransform();
    }

    public StyleSettings(StyleSettings other) {
        // Immutable objects
        fillColor = other.fillColor;
        filled = other.filled;
        strokeColor = other.strokeColor;
        stroked = other.stroked;
        colorMode = other.colorMode;
        colorMaxes = other.colorMaxes;
        stroke = other.stroke;
        rectMode = other.rectMode;
        ellipseMode = other.ellipseMode;
        font = other.font;
        textAlign = other.textAlign;
        angleMode = other.angleMode;
        // Mutable objects
        transform = new AffineTransform(other.transform);
    }

    public StyleSettings copy() {
        return new StyleSettings(this);
    }

    public int getFillColor() {
        return fillColor;
    }

    public void setFillColor(int fillColor) {
        filled = fillColor != 0;
        this.fillColor = fillColor;
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(int strokeColor) {
        stroked = strokeColor != 0;
        this.strokeColor = strokeColor;
    }

    public ColorMode getColorMode() {
        return colorMode;
    }

    public void setColorMode(ColorMode colorMode) {
        this.colorMode = colorMode;
    }

    public ColorMaxes getColorMaxes() {
        return colorMaxes;
    }

    public void setColorMaxes(ColorMaxes colorMaxes) {
        this.colorMaxes = colorMaxes;
    }

    public BasicStroke getStroke() {
        return stroke;
    }

    public void setStroke(BasicStroke stroke) {
        this.stroke = stroke;
    }

    public ShapeMode getRectMode() {
        return rectMode;
    }

    public void setRectMode(ShapeMode rectMode) {
        this.rectMode = rectMode;
    }

    public ShapeMode getEllipseMode() {
        return ellipseMode;
    }

    public void setEllipseMode(ShapeMode ellipseMode) {
        this.ellipseMode = ellipseMode;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public TextAlign getTextAlign() {
        return textAlign;
    }

    public void setTextAlign(TextAlign textAlign) {
        this.textAlign = textAlign;
    }

    public AffineTransform getTransform() {
        return transform;
    }

    public void setTransform(AffineTransform transform) {
        this.transform = transform;
    }

    public AngleMode getAngleMode() {
        return angleMode;
    }

    public void setAngleMode(AngleMode angleMode) {
        this.angleMode = angleMode;
    }

    // Helpers

    public boolean isFilled() {
        return filled;
    }

    public boolean isStroked() {
        return stroked;
    }

    @Override
    public String toString() {
        return new StringJoiner(",\n   ", StyleSettings.class.getSimpleName() + "[\n   ", "\n]")
                .add("fillColor=" + fillColor)
                .add("strokeColor=" + strokeColor)
                .add("colorMode=" + colorMode)
                .add("colorMaxes=" + colorMaxes)
                .add("stroke=" + stroke)
                .add("rectMode=" + rectMode)
                .add("ellipseMode=" + ellipseMode)
                .add("font=" + font)
                .add("textAlign=" + textAlign)
                .add("angleMode=" + angleMode)
                .add("transform=" + transform)
                .add("filled=" + filled)
                .add("stroked=" + stroked)
                .toString();
    }
}
