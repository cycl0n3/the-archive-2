package constants;

public interface PConstants {

    StrokeCap ROUND = StrokeCap.ROUND;
    StrokeCap PROJECT = StrokeCap.PROJECT;
    StrokeCap SQUARE = StrokeCap.SQUARE;

    StrokeJoin BEVEL = StrokeJoin.BEVEL;
    StrokeJoin MITER = StrokeJoin.MITER;
    StrokeJoin ROUND_JOIN = StrokeJoin.ROUND;

    ColorMode RGB = ColorMode.RGB;
    ColorMode HSB = ColorMode.HSB;

    ShapeMode CORNER_MODE = ShapeMode.CORNER;
    ShapeMode CENTER_MODE = ShapeMode.CENTER;

    ArcMode OPEN = ArcMode.OPEN;
    ArcMode CHORD = ArcMode.CHORD;
    ArcMode PIE = ArcMode.PIE;

    Alignment TOP = Alignment.TOP;
    Alignment CENTER = Alignment.CENTER;
    Alignment BOTTOM = Alignment.BOTTOM;
    Alignment BASELINE = Alignment.BASELINE;
    Alignment LEFT = Alignment.LEFT;
    Alignment RIGHT = Alignment.RIGHT;

    AngleMode RADIANS = AngleMode.RADIANS;
    AngleMode DEGREES = AngleMode.DEGREES;

    ShapeEndMode CLOSE = ShapeEndMode.CLOSE;

    MouseButton LEFT_BTN = MouseButton.LEFT;
    MouseButton WHEEL = MouseButton.CENTER;
    MouseButton RIGHT_BTN = MouseButton.RIGHT;

}
