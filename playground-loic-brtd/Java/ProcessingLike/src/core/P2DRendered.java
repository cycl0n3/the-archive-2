package core;

import constants.ArcMode;
import constants.ShapeEndMode;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class P2DRendered implements PRenderer {

    private static final AffineTransform IDENTITY = new AffineTransform();

    protected final Graphics2D g2;
    protected final BufferedImage image;
    protected final Dimension size;
    protected StyleSettings style;
    protected final Stack<StyleSettings> styleStack;

    // Cached shapes (to avoid creating new shapes at each frame)
    private final Arc2D.Float arc = new Arc2D.Float();
    private final Ellipse2D.Float ellipse = new Ellipse2D.Float();
    private final RoundRectangle2D.Float roundRect = new RoundRectangle2D.Float();
    private final Line2D.Float line = new Line2D.Float();
    private final Rectangle2D.Float rect = new Rectangle2D.Float();
    private final Path2D.Float path = new Path2D.Float();
    private final Point.Float point = new Point.Float();

    public P2DRendered(int width, int height) {
        size = new Dimension(width, height);
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        style = new StyleSettings();
        styleStack = new Stack<>();
        g2.setFont(style.getFont());
        g2.setStroke(style.getStroke());
        g2.setTransform(style.getTransform());
    }

    // Styles

    @Override
    public StyleSettings getStyle() {
        return style;
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }

    @Override
    public Graphics2D getUnderlyingGraphics() {
        return g2;
    }

    // Drawing

    @Override
    public void background(int color) {
        final AffineTransform oldTransform = g2.getTransform();
        g2.setTransform(IDENTITY);
        g2.setColor(new Color(color, true));
        g2.fillRect(0, 0, size.width, size.height);
        g2.setTransform(oldTransform);
    }

    @Override
    public void clear() {
        final Composite oldComposite = g2.getComposite();
        final AffineTransform oldTransform = g2.getTransform();
        g2.setComposite(AlphaComposite.Clear);
        g2.setTransform(IDENTITY);
        g2.fillRect(0, 0, size.width, size.height);
        g2.setComposite(oldComposite);
        g2.setTransform(oldTransform);
    }

    @Override
    public void rect(float x, float y, float w, float h, float r) {
        style.getRectMode().adaptBounds(rect, x, y, w, h);
        if (r == 0) {
            renderShape(rect);
        } else {
            r = PCanvas.constrain(r * 2, 0, Math.min(rect.width, rect.height));
            roundRect.setRoundRect(rect.x, rect.y, rect.width, rect.height, r, r);
            renderShape(roundRect);
        }
    }

    @Override
    public void ellipse(float x, float y, float w, float h) {
        style.getEllipseMode().adaptBounds(rect, x, y, w, h);
        ellipse.setFrame(rect);
        renderShape(ellipse);
    }

    @Override
    public void arc(float x, float y, float w, float h, float start, float stop, ArcMode mode) {
        style.getEllipseMode().adaptBounds(rect, x, y, w, h);

        start = style.getAngleMode().toDegrees(start);
        stop = style.getAngleMode().toDegrees(stop);

        final int startAngle = -((int) start);
        int arcAngle = ((int) start - (int) stop) % 360;
        if (arcAngle > 0)
            arcAngle -= 360;

        if (style.isFilled()) {
            arc.setArc(rect.x, rect.y, rect.width, rect.height, startAngle, arcAngle, mode.getFillMode());
            g2.setColor(new Color(style.getFillColor(), true));
            g2.fill(arc);
        }
        if (style.isStroked()) {
            arc.setArc(rect.x, rect.y, rect.width, rect.height, startAngle, arcAngle, mode.getStrokeMode());
            g2.setColor(new Color(style.getStrokeColor(), true));
            g2.draw(arc);
        }
    }

    @Override
    public void point(float x, float y, float z) {
        if (style.isStroked()) {
            final float w = style.getStroke().getLineWidth();
            g2.setColor(new Color(style.getStrokeColor(), true));
            if (w == 1 && g2.getTransform().getScaleX() == 1 && g2.getTransform().getScaleY() == 1) {
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
                g2.fillRect((int) x, (int) y, 1, 1);
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            } else {
                ellipse.setFrame(x - w / 2, y - w / 2, w, w);
                g2.fill(ellipse);
            }
        }
    }

    @Override
    public void line(float x1, float y1, float z1, float x2, float y2, float z2) {
        if (style.isStroked()) {
            line.setLine(x1, y1, x2, y2);
            g2.setColor(new Color(style.getStrokeColor(), true));
            g2.draw(line);
        }
    }

    private final List<Point2D.Float> vertices = new ArrayList<>();

    @Override
    public void beginShape() {
        vertices.clear();
    }

    @Override
    public void vertex(float x, float y) {
        vertices.add(new Point2D.Float(x, y));
    }

    @Override
    public void endShape(ShapeEndMode mode) {
        path.reset();
        if (!vertices.isEmpty()) {
            path.moveTo(vertices.get(0).x, vertices.get(0).y);
            for (int i = 1; i < vertices.size(); i++) {
                final Point2D.Float p = vertices.get(i);
                path.lineTo(p.x, p.y);
            }
            if (mode == ShapeEndMode.CLOSE) {
                path.closePath();
            }
        }
        renderShape(path);
    }

    @Override
    public void box(float w, float h, float d) {
        PGraphics.error("box() can only be called in a 3D context.");
    }

    @Override
    public void text(String text, float x, float y) {
        style.getTextAlign().adaptOrigin(point, x, y, text, g2);
        g2.setColor(new Color(style.getFillColor(), true));
        g2.drawString(text, point.x, point.y);
    }

    @Override
    public void image(PImage img, float x, float y, float width, float height, float alpha) {
        if (img != null) {
            image(img.img, x, y, width, height, alpha);
        }
    }

    @Override
    public void image(PGraphics g, float x, float y, float width, float height, float alpha) {
        image(g.renderer.getImage(), x, y, width, height, alpha);
    }

    private void image(Image img, float x, float y, float width, float height, float alpha) {
        if (alpha > 0) {
            float alphaMax = style.getColorMaxes().get(style.getColorMode())[3];
            Composite old = null;
            if (alpha < alphaMax) {
                old = g2.getComposite();
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha / alphaMax));
            }
            if (width == -1 && height == -1) {
                g2.drawImage(img, (int) x, (int) y, null);
            } else {
                g2.drawImage(img, (int) x, (int) y, (int) width, (int) height, null);
            }
            if (alpha < alphaMax) {
                g2.setComposite(old);
            }
        }
    }

    // Stack

    @Override
    public void push() {
        style.setTransform(g2.getTransform());
        styleStack.push(style.copy());
    }

    @Override
    public void pop() {
        if (!styleStack.isEmpty()) {
            style = styleStack.pop();
            // Update graphic context with the latest properties
            g2.setStroke(style.getStroke());
            g2.setFont(style.getFont());
            g2.setTransform(style.getTransform());
        } else {
            PGraphics.error("Cannot use pop() more times than push()");
        }
    }

    // Transformations

    @Override
    public void resetTransform() {
        g2.setTransform(IDENTITY);
    }

    @Override
    public void printMatrix() {
        System.out.println(g2.getTransform());
    }

    @Override
    public void ortho() {
        PGraphics.error("ortho() can only be called in a 3D context.");
    }

    @Override
    public void translate(float dx, float dy, float dz) {
        g2.translate(dx, dy);
    }

    @Override
    public void scale(float scaleX, float scaleY, float scaleZ) {
        g2.scale(scaleX, scaleY);
    }

    @Override
    public void rotate(float angle) {
        g2.rotate(style.getAngleMode().toRadians(angle));
    }

    @Override
    public void rotateX(float angle) {
        PGraphics.error("rotateX() can only be called in a 3D context.");
    }

    @Override
    public void rotateY(float angle) {
        PGraphics.error("rotateY() can only be called in a 3D context.");
    }

    @Override
    public void rotateZ(float angle) {
        PGraphics.error("rotateZ() can only be called in a 3D context.");
    }

    // Helper method

    protected void renderShape(Shape shape) {
        if (style.isFilled()) {
            g2.setColor(new Color(style.getFillColor(), true));
            g2.fill(shape);
        }
        if (style.isStroked()) {
            g2.setColor(new Color(style.getStrokeColor(), true));
            g2.draw(shape);
        }
    }

    @Override
    public int color(int a) {
        return style.getColorMode().makeColor(style.getColorMaxes(), a);
    }

    @Override
    public int color(float a) {
        return style.getColorMode().makeColor(style.getColorMaxes(), a);
    }

    @Override
    public int color(int a, float alpha) {
        return style.getColorMode().makeColor(style.getColorMaxes(), a, alpha);
    }

    @Override
    public int color(float grey, float alpha) {
        return style.getColorMode().makeColor(style.getColorMaxes(), grey, alpha);
    }

    @Override
    public int color(float a, float b, float c) {
        return style.getColorMode().makeColor(style.getColorMaxes(), a, b, c);
    }

    @Override
    public int color(float a, float b, float c, float alpha) {
        return style.getColorMode().makeColor(style.getColorMaxes(), a, b, c, alpha);
    }

}
