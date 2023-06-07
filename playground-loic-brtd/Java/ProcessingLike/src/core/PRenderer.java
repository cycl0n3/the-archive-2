package core;

import constants.ArcMode;
import constants.ShapeEndMode;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface PRenderer {

    // Styles

    StyleSettings getStyle();

    Graphics2D getUnderlyingGraphics();

    BufferedImage getImage();

    // Drawing

    void background(int color);

    void clear();
    
    void rect(float x, float y, float w, float h, float r);

    void ellipse(float x, float y, float w, float h);

    void arc(float x, float y, float w, float h, float start, float stop, ArcMode mode);

    void point(float x, float y, float z);

    void line(float x1, float y1, float z1, float x2, float y2, float z2);

    void beginShape();

    void vertex(float x, float y);

    void endShape(ShapeEndMode mode);

    void box(float w, float h, float d);

    void text(String text, float x, float y);

    void image(PImage img, float x, float y, float width, float height, float alpha);

    void image(PGraphics g, float x, float y, float width, float height, float alpha);

    // Stack

    void push();

    void pop();

    // Transformations

    void resetTransform();

    void printMatrix();

    void ortho();

    void translate(float dx, float dy, float dz);

    void scale(float scaleX, float scaleY, float scaleZ);

    void rotate(float angle);

    void rotateX(float angle);

    void rotateY(float angle);

    void rotateZ(float angle);

    // Colors

    int color(int a);

    int color(float a);

    int color(int a, float alpha);

    int color(float grey, float alpha);

    int color(float a, float b, float c);

    int color(float a, float b, float c, float alpha);

}
