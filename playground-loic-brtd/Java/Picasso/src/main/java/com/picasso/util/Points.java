package com.picasso.util;

import java.awt.*;
import java.util.function.IntBinaryOperator;

public class Points {

    private Points() {
    }

    public static Point add(Point a, Point b) {
        return operation(a, b, Integer::sum);
    }

    public static Point sub(Point a, Point b) {
        return operation(a, b, (u, v) -> u - v);
    }

    public static Point max(Point a, Point b) {
        return operation(a, b, Math::max);
    }

    private static Point operation(Point a, Point b, IntBinaryOperator operator) {
        return new Point(operator.applyAsInt(a.x, b.x), operator.applyAsInt(a.y, b.y));
    }
}
