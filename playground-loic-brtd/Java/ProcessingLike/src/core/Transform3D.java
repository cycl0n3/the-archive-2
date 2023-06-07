package core;

import matrix_math.Matrix;

public class Transform3D {

    private Matrix projection;
    private Matrix rotation;
    private Matrix translation;
    private Matrix scale;

    public Transform3D() {
        projection = Matrix.identity(4);
        rotation = Matrix.identity(4);
        translation = Matrix.identity(4);
        scale = Matrix.identity(4);
    }

    public Transform3D(Transform3D other) {
        projection = other.projection;
        rotation = other.rotation;
        translation = other.translation;
        scale = other.scale;
    }

    public Transform3D copy() {
        return new Transform3D(this);
    }

    public Matrix getProjection() {
        return projection;
    }

    public void setProjection(Matrix projection) {
        this.projection = projection;
    }

    public Matrix getRotation() {
        return rotation;
    }

    public void setRotation(Matrix rotation) {
        this.rotation = rotation;
    }

    public Matrix getTranslation() {
        return translation;
    }

    public void setTranslation(Matrix translation) {
        this.translation = translation;
    }

    public Matrix getScale() {
        return scale;
    }

    public void setScale(Matrix scale) {
        this.scale = scale;
    }

}
