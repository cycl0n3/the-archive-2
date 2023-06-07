package game;

import static game.Game.*;

import java.util.ArrayList;
import java.util.List;

import core.PGraphics;

public abstract class MovingEntity {

    protected int x, y;
    public static final float DIAMETER = UNIT * 1.3f;
    public static final float CORNER_OFFSET =  - UNIT * 0.15f;
    public static final float RADIUS = DIAMETER / 2;
    public static final float RADIUS_SQUARED = RADIUS * RADIUS;
    protected int color;
    protected float speed;
    protected float drawnX, drawnY;

    protected Direction direction;
    private Direction desiredDirection;

    private int targetX, targetY;
    private float animationProgress;
    private boolean isAnimating;

    public MovingEntity(int x, int y, float speed, int color) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.color = color;
        this.desiredDirection = Direction.STATIC;
        this.direction = Direction.STATIC;
        this.targetX = x;
        this.targetY = y;
        this.animationProgress = 1;
        this.isAnimating = false;
        computeDrawnCornerPosition();
    }

    public void changeDirection(Direction dir) {
        this.desiredDirection = dir;
    }

    private void setPos(int x, int y) {
        this.x = (x + board.cols) % board.cols;
        this.y = y;
        onPositionUpdated();
    }

    protected void onPositionUpdated() {
    }

    public void update() {
        computeDrawnCornerPosition();
        if (isAnimating) {
            stepMovement();
        } else {
            if (board.get(x + desiredDirection.dx, y + desiredDirection.dy).isAccessible()) {
                direction = desiredDirection;
                startMovement();
            } else if (board.get(x + direction.dx, y + direction.dy).isAccessible()) {
                startMovement();
            }
        }
    }

    private void stepMovement() {
        animationProgress += speed;
        if (animationProgress >= 1) {
            animationProgress = 1;
            setPos(targetX, targetY);
            isAnimating = false;
        }
    }

    private void startMovement() {
        if (direction == Direction.STATIC) return;
        isAnimating = true;
        animationProgress = 0;
        targetX = x + direction.dx;
        targetY = y + direction.dy;
    }

    public Direction[] findPossibleMoves() {
        List<Direction> found = new ArrayList<>();
        for (Direction dir : Direction.NON_STATIC) {
            if (dir != direction.opposite()) {
                int posX = x + dir.dx;
                int posY = y + dir.dy;
                if (board.get(posX, posY).isAccessible()) {
                    found.add(dir);
                }
            }
        }
        // If no other option than turning around
        if (found.isEmpty()) {
            return new Direction[]{direction.opposite()};
        }
        return found.toArray(new Direction[0]);
    }

    private void computeDrawnCornerPosition() {
        drawnX = lerp(x * UNIT, targetX * UNIT, animationProgress) + CORNER_OFFSET;
        drawnY = lerp(y * UNIT, targetY * UNIT, animationProgress) + CORNER_OFFSET;
    }

    public boolean touches(MovingEntity entity) {
        if (entity == null) return false;
        float d = distSq(drawnX, drawnY, entity.drawnX, entity.drawnY);
        return d < MovingEntity.RADIUS_SQUARED;
    }

    public void show(PGraphics g) {

    }

}