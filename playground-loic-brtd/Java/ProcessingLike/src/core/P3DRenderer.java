package core;

import matrix_math.Matrix;
import matrix_math.Vector;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.Stack;

public class P3DRenderer extends P2DRendered {

    Matrix transform3D;
    private Stack<Matrix> transform3DStack;
    private boolean isOrthographic;
    private final Rectangle2D.Float rect = new Rectangle2D.Float();

    public P3DRenderer(int width, int height) {
        super(width, height);
        transform3D = Matrix.identity(4);
        transform3DStack = new Stack<>();
        isOrthographic = false;
    }

    // Drawing

    @Override
    public void rect(float x, float y, float w, float h, float r) {
        if (style.isFilled() || style.isStroked()) {
            style.getRectMode().adaptBounds(rect, x, y, w, h);
            final Vector v0 = project(Vector.of(rect.x, rect.y, 0, 0));
            final Vector v1 = project(Vector.of(rect.x + rect.width, rect.y, 0, 0));
            final Vector v2 = project(Vector.of(rect.x + rect.width, rect.y + rect.height, 0, 0));
            final Vector v3 = project(Vector.of(rect.x, rect.y + rect.height, 0, 0));

            final Path2D.Float path = new Path2D.Float();
            path.moveTo(v0.x(), v0.y());
            path.lineTo(v1.x(), v1.y());
            path.lineTo(v2.x(), v2.y());
            path.lineTo(v3.x(), v3.y());
            path.closePath();
            if (style.isFilled()) {
                g2.setColor(new Color(style.getFillColor(), true));
                g2.fill(path);
            }

            super.line(v0.x(), v0.y(), 0, v1.x(), v1.y(), 0);
            super.line(v1.x(), v1.y(), 0, v2.x(), v2.y(), 0);
            super.line(v2.x(), v2.y(), 0, v3.x(), v3.y(), 0);
            super.line(v3.x(), v3.y(), 0, v0.x(), v0.y(), 0);
        }
    }

    // @Override
    // public void ellipse(float x, float y, float w, float h) {
    //
    // }
    //
    // @Override
    // public void arc(float x, float y, float w, float h, float start, float stop,
    // ArcMode mode) {
    //
    // }

    @Override
    public void point(float x, float y, float z) {
        final Vector p = project(Vector.of(x, y, z, 1));
        super.point(p.x(), p.y(), 0);
    }

    @Override
    public void line(float x1, float y1, float z1, float x2, float y2, float z2) {
        final Vector p1 = project(Vector.of(x1, y1, z1, 1));
        final Vector p2 = project(Vector.of(x2, y2, z2, 1));
        super.line(p1.x(), p1.y(), 0, p2.x(), p2.y(), 0);
    }

    // @Override
    // public void beginShape() {
    //
    // }
    //
    // @Override
    // public void vertex(float x, float y) {
    //
    // }
    //
    // @Override
    // public void endShape(ShapeEndMode mode) {
    //
    // }

    @Override
    public void box(float w, float h, float d) {
        w = w / 2;
        h = h / 2;
        d = d / 2;

        line(-w, -h, -d, w, -h, -d);
        line(-w, -h, -d, -w, h, -d);
        line(-w, -h, -d, -w, -h, d);

        line(w, h, d, -w, h, d);
        line(w, h, d, w, -h, d);
        line(w, h, d, w, h, -d);

        line(w, -h, -d, w, h, -d);
        line(w, -h, -d, w, -h, d);
        line(-w, -h, d, w, -h, d);

        line(-w, -h, d, -w, h, d);
        line(-w, h, -d, w, h, -d);
        line(-w, h, -d, -w, h, d);
    }

    // @Override
    // public void text(String text, int x, int y) {
    //
    // }
    //
    // @Override
    // public void image(Image img, float x, float y, float width, float height,
    // float alpha) {
    //
    // }

    // Stack

    @Override
    public void push() {
        super.push();
        transform3DStack.push(transform3D.copy());
    }

    @Override
    public void pop() {
        super.pop();
        transform3D = transform3DStack.pop();
    }

    // Transformations

    @Override
    public void resetTransform() {
        super.resetTransform();
        g2.translate(size.width / 2, size.height / 2);
        transform3D = Matrix.identity(4);
    }

    @Override
    public void printMatrix() {
        System.out.println(transform3D);
    }

    @Override
    public void ortho() {
        isOrthographic = true;
    }

    @Override
    public void translate(float dx, float dy, float dz) {
        transform3D = Matrix.prod(
                transform3D,
                Matrix.of(new float[][] {
                        { 1, 0, 0, dx },
                        { 0, 1, 0, dy },
                        { 0, 0, 1, dz },
                        { 0, 0, 0, 1 }
                }));
    }

    @Override
    public void scale(float scaleX, float scaleY, float scaleZ) {
        transform3D = Matrix.prod(
                transform3D,
                Matrix.of(new float[][] {
                        { scaleX, 0, 0, 0 },
                        { 0, scaleY, 0, 0 },
                        { 0, 0, scaleZ, 0 },
                        { 0, 0, 0, 1 }
                }));
    }

    @Override
    public void rotateX(float angle) {
        transform3D = Matrix.prod(
                transform3D,
                Matrix.of(new float[][] {
                        { 1, 0, 0, 0 },
                        { 0, cos(angle), -sin(angle), 0 },
                        { 0, sin(angle), cos(angle), 0 },
                        { 0, 0, 0, 1 }
                }));
    }

    @Override
    public void rotateY(float angle) {
        transform3D = Matrix.prod(
                transform3D,
                Matrix.of(new float[][] {
                        { cos(angle), 0, sin(angle), 0 },
                        { 0, 1, 0, 0 },
                        { -sin(angle), 0, cos(angle), 0 },
                        { 0, 0, 0, 1 }
                }));
    }

    @Override
    public void rotateZ(float angle) {
        transform3D = Matrix.prod(
                transform3D,
                Matrix.of(new float[][] {
                        { cos(angle), -sin(angle), 0, 0 },
                        { sin(angle), cos(angle), 0, 0 },
                        { 0, 0, 1, 0 },
                        { 0, 0, 0, 1 }
                }));
    }

    // Helper methods

    private float sin(float angle) {
        return (float) Math.sin(style.getAngleMode().toRadians(angle));
    }

    private float cos(float angle) {
        return (float) Math.cos(style.getAngleMode().toRadians(angle));
    }

    private Vector project(Vector v) {
        v = Matrix.prod(transform3D, v);
        if (isOrthographic) {
            return orthographicProjection(v);
        } else {
            return weakPerspective(v);
        }
    }

    private Vector orthographicProjection(Vector v) {
        return Vector.of(v.x(), v.y(), 0);
    }

    private Vector weakPerspective(Vector v) {
        float cameraZ = 1f;
        float z = 1f / (cameraZ - v.z() / size.width);
        Matrix weak = Matrix.of(new float[][] {
                { z, 0, 0, 0 },
                { 0, z, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
        });
        return Matrix.prod(weak, v);
    }

}