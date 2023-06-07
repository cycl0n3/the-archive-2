package core;

import constants.*;

import java.awt.*;

public class PGraphics {

    PRenderer renderer;

    public int width;
    public int height;

    PGraphics() {}

    public PGraphics(int width, int height, RendererType rendererType) {
        initRenderer(width, height, rendererType);
    }

    void initRenderer(int width, int height, RendererType rendererType) {
        this.renderer = rendererType == RendererType.P3D
                ? new P3DRenderer(width, height)
                : new P2DRendered(width, height);
        this.width = width;
        this.height = height;
    }

    static void error(String message) {
        throw new PError(message);
    }

    static void manageError(Throwable t) {
        final boolean debugMode = true;
        final StringBuilder builder = new StringBuilder();
        builder.append(t.getClass().getSimpleName() + ": " + t.getMessage() + System.lineSeparator());
        for (StackTraceElement e : t.getStackTrace()) {
            if (debugMode) {
                if (e.getLineNumber() > 0) {
                    builder.append("\t" + "at " + e.getClassName() + "." + e.getMethodName()
                            + "(" + e.getFileName() + ":" + e.getLineNumber() + ")"
                            + System.lineSeparator());
                }
            } else {
                if (e.getLineNumber() > 0 && e.getClassName().equals(PGraphics.class.getName())) {
                    builder.append(" (" + e.getFileName() + ":" + e.getLineNumber() + ")");
                }
            }
        }
        System.err.println(builder.toString());
        System.exit(1);
    }

    public Graphics2D getUnderlyingGraphics() {
        return renderer.getUnderlyingGraphics();
    }

    public void smooth() {
        renderer.getUnderlyingGraphics().setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
    }

    public void noSmooth() {
        renderer.getUnderlyingGraphics().setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
    }

    public void beginDraw() {
        renderer.resetTransform();
    }

    public void endDraw() {}

    // Stroke

    public void strokeWeight(float width) {
        width = Math.max(0, width);
        final BasicStroke current = renderer.getStyle().getStroke();
        final BasicStroke newer = new BasicStroke(width, current.getEndCap(), current.getLineJoin());
        renderer.getStyle().setStroke(newer);
        renderer.getUnderlyingGraphics().setStroke(newer);
    }

    public void strokeCap(StrokeCap cap) {
        final BasicStroke current = renderer.getStyle().getStroke();
        final BasicStroke newer = new BasicStroke(current.getLineWidth(), cap.getValue(), current.getLineJoin());
        renderer.getStyle().setStroke(newer);
        renderer.getUnderlyingGraphics().setStroke(newer);
    }

    public void strokeJoin(StrokeJoin join) {
        final BasicStroke current = renderer.getStyle().getStroke();
        final BasicStroke newer = new BasicStroke(current.getLineWidth(), current.getEndCap(), join.getValue());
        renderer.getStyle().setStroke(newer);
        renderer.getUnderlyingGraphics().setStroke(newer);
    }

    public void stroke(int color) {
        renderer.getStyle().setStrokeColor(renderer.color(color));
    }

    public void noStroke() {
        renderer.getStyle().setStrokeColor(0x00000000);
    }

    public void stroke(float grey) {
        renderer.getStyle().setStrokeColor(renderer.color(grey));
    }

    public void stroke(float grey, float alpha) {
        renderer.getStyle().setStrokeColor(renderer.color(grey, alpha));
    }

    public void stroke(int a, float alpha) {
        renderer.getStyle().setStrokeColor(renderer.color(a, alpha));
    }

    public void stroke(float a, float b, float c) {
        renderer.getStyle().setStrokeColor(renderer.color(a, b, c));
    }

    public void stroke(float a, float b, float c, float alpha) {
        renderer.getStyle().setStrokeColor(renderer.color(a, b, c, alpha));
    }

    // Fill

    public void fill(int a) {
        renderer.getStyle().setFillColor(renderer.color(a));
    }

    public void noFill() {
        renderer.getStyle().setFillColor(0);
    }

    public void fill(float grey) {
        renderer.getStyle().setFillColor(renderer.color(grey));
    }

    public void fill(int a, float alpha) {
        renderer.getStyle().setFillColor(renderer.color(a, alpha));
    }

    public void fill(float grey, float alpha) {
        renderer.getStyle().setFillColor(renderer.color(grey, alpha));
    }

    public void fill(float a, float b, float c, float alpha) {
        renderer.getStyle().setFillColor(renderer.color(a, b, c, alpha));
    }

    public void fill(float a, float b, float c) {
        renderer.getStyle().setFillColor(renderer.color(a, b, c));
    }

    // Color

    public void colorMode(ColorMode colorMode, float max1, float max2, float max3, float maxAlpha) {
        renderer.getStyle().setColorMode(colorMode);
        renderer.getStyle()
                .setColorMaxes(renderer.getStyle().getColorMaxes().set(colorMode, max1, max2, max3, maxAlpha));
    }

    public void colorMode(ColorMode colorMode, float max1, float max2, float max3) {
        renderer.getStyle().setColorMode(colorMode);
        renderer.getStyle()
                .setColorMaxes(renderer.getStyle().getColorMaxes().set(colorMode, max1, max2, max3));
    }

    public void colorMode(ColorMode colorMode, float max) {
        renderer.getStyle().setColorMode(colorMode);
        renderer.getStyle().setColorMaxes(renderer.getStyle().getColorMaxes().set(colorMode, max));
    }

    public void colorMode(ColorMode colorMode) {
        renderer.getStyle().setColorMode(colorMode);
    }

    // Color

    public int color(float a, float b, float c, float alpha) {
        return renderer.color(a, b, c, alpha);
    }

    public int color(float a, float b, float c) {
        return renderer.color(a, b, c);
    }

    public int color(float grey, float alpha) {
        return renderer.color(grey, alpha);
    }

    public int color(int grey, float alpha) {
        return renderer.color(grey, alpha);
    }

    public int color(float grey) {
        return renderer.color(grey);
    }

    public int color(int a) {
        return renderer.color(a);
    }

    // Background

    public void background(int a) {
        renderer.background(renderer.color(a));
    }

    public void background(float grey) {
        renderer.background(renderer.color(grey));
    }

    public void background(float r, float g, float b, float a) {
        renderer.background(renderer.color(r, g, b, a));
    }

    public void background(float r, float g, float b) {
        renderer.background(renderer.color(r, g, b));
    }

    public void background(float grey, float alpha) {
        renderer.background(renderer.color(grey, alpha));
    }

    public void background(int a, float alpha) {
        renderer.background(renderer.color(a, alpha));
    }

    public void clear() {
        renderer.clear();
    }

    // Ellipse

    public void ellipseMode(ShapeMode mode) {
        renderer.getStyle().setEllipseMode(mode);
    }

    public void ellipse(float x, float y, float w, float h) {
        renderer.ellipse(x, y, w, h);
    }

    public void circle(float x, float y, float d) {
        renderer.ellipse(x, y, d, d);
    }

    public void arc(float x, float y, float w, float h, float start, float stop, ArcMode mode) {
        renderer.arc(x, y, w, h, start, stop, mode);
    }

    public void arc(float x, float y, float w, float h, float start, float stop) {
        renderer.arc(x, y, w, h, start, stop, ArcMode.DEFAULT);
    }

    // Rect

    public void rectMode(ShapeMode mode) {
        renderer.getStyle().setRectMode(mode);
    }

    public void rect(float x, float y, float w, float h) {
        renderer.rect(x, y, w, h, 0);
    }

    public void rect(float x, float y, float w, float h, float r) {
        renderer.rect(x, y, w, h, r);
    }

    // Point

    public void point(float x, float y) {
        renderer.point(x, y, 0);
    }

    public void point(float x, float y, float z) {
        renderer.point(x, y, z);
    }

    // Line

    public void line(float x1, float y1, float x2, float y2) {
        renderer.line(x1, y1, 0, x2, y2, 0);
    }

    public void line(float x1, float y1, float z1, float x2, float y2, float z2) {
        renderer.line(x1, y1, z1, x2, y2, z2);
    }

    // Create a shape with vertices

    public void beginShape() {
        renderer.beginShape();
    }

    public void vertex(float x, float y) {
        renderer.vertex(x, y);
    }

    public void endShape(ShapeEndMode mode) {
        renderer.endShape(mode);
    }

    public void endShape() {
        renderer.endShape(null);
    }

    // Box

    public void box(float size) {
        renderer.box(size, size, size);
    }

    public void box(float w, float h, float d) {
        renderer.box(w, h, d);
    }

    // Text

    public void textAlign(Alignment horizAlign) {
        final TextAlign current = renderer.getStyle().getTextAlign();
        renderer.getStyle().setTextAlign(new TextAlign(horizAlign, current.getVertAlign()));
    }

    public void textAlign(Alignment horizAlign, Alignment vertAlign) {
        renderer.getStyle().setTextAlign(new TextAlign(horizAlign, vertAlign));
    }

    public void textFont(String fontFamily) {
        final Font current = renderer.getStyle().getFont();
        final Font newer = new Font(fontFamily, current.getStyle(), current.getSize());
        renderer.getStyle().setFont(newer);
        renderer.getUnderlyingGraphics().setFont(newer);
    }

    public void textSize(float fontSize) {
        final Font current = renderer.getStyle().getFont();
        final Font newer = new Font(current.getFontName(), current.getStyle(), (int) fontSize);
        renderer.getStyle().setFont(newer);
        renderer.getUnderlyingGraphics().setFont(newer);
    }

    public void text(String text, float x, float y) {
        renderer.text(text, x, y);
    }

    public void text(Object object, float x, float y) {
        renderer.text(String.valueOf(object), x, y);
    }

    public int textWidth(String text) {
        return renderer.getUnderlyingGraphics().getFontMetrics().stringWidth(text);
    }

    public int textAscent() {
        return renderer.getUnderlyingGraphics().getFontMetrics().getAscent();
    }

    public int textDescent() {
        return renderer.getUnderlyingGraphics().getFontMetrics().getDescent();
    }

    // Angle mode

    public void angleMode(AngleMode mode) {
        renderer.getStyle().setAngleMode(mode);
    }

    // Transform

    public void printMatrix() {
        renderer.printMatrix();
    }

    public void ortho() {
        renderer.ortho();
    }

    public void translate(float dx, float dy) {
        renderer.translate(dx, dy, 0);
    }

    public void translate(float dx, float dy, float dz) {
        renderer.translate(dx, dy, dz);
    }

    public void rotate(float angle) {
        renderer.rotate(angle);
    }

    public void scale(float scaleX, float scaleY, float scaleZ) {
        renderer.scale(scaleX, scaleY, scaleZ);
    }

    public void scale(float scaleX, float scaleY) {
        renderer.scale(scaleX, scaleY, 1);
    }

    public void scale(float scale) {
        renderer.scale(scale, scale, scale);
    }

    public void rotateX(float angle) {
        renderer.rotateX(angle);
    }

    public void rotateY(float angle) {
        renderer.rotateY(angle);
    }

    public void rotateZ(float angle) {
        renderer.rotateZ(angle);
    }

    public void push() {
        renderer.push();
    }

    public void pop() {
        renderer.pop();
    }

    // Images

    public void image(PImage img, float x, float y) {
        renderer.image(img, x, y, -1, -1, 255);
    }

    public void image(PImage img, float alpha) {
        renderer.image(img, 0, 0, -1, -1, alpha);
    }

    public void image(PImage img, float x, float y, float alpha) {
        renderer.image(img, x, y, -1, -1, alpha);
    }

    public void image(PImage img, float x, float y, float width, float height) {
        renderer.image(img, x, y, width, height, 255);
    }

    public void image(PImage img, float x, float y, float width, float height, float alpha) {
        renderer.image(img, x, y, width, height, alpha);
    }

    public void image(PGraphics g, float x, float y) {
        renderer.image(g, x, y, -1, -1, 255);
    }

    public void image(PGraphics g, float alpha) {
        renderer.image(g, 0, 0, -1, -1, alpha);
    }

    public void image(PGraphics g, float x, float y, float alpha) {
        renderer.image(g, x, y, -1, -1, alpha);
    }

    public void image(PGraphics g, float x, float y, float width, float height) {
        renderer.image(g, x, y, width, height, 255);
    }

    public void image(PGraphics g, float x, float y, float width, float height, float alpha) {
        renderer.image(g, x, y, width, height, alpha);
    }

    // Debug

    public void printStyles() {
        System.out.println(renderer.getStyle());
    }
}
