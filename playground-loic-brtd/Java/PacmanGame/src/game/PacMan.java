package game;

import core.PGraphics;

import static game.Game.*;

public class PacMan extends MovingEntity {

    public int score;

    public PacMan(int x, int y, float speed) {
        super(x, y, speed, 0xFFFFFF00);
        score = 0;
    }

    @Override
    protected void onPositionUpdated() {
        board.get(x, y).doAction(this);
    }

    @Override
    public void show(PGraphics g) {
        PGraphics img = getImage();
        g.fill(color);
        g.noStroke();
        g.ellipseMode(CORNER_MODE);
        g.image(img, drawnX, drawnY);
        // Wrap around the board horizontally
        if (drawnX > WIDTH - UNIT) {
            g.image(img, drawnX - WIDTH, drawnY);
        } else if (drawnX < UNIT) {
            g.image(img, drawnX + WIDTH, drawnY);
        }
    }

    private PGraphics getImage() {
        switch (direction) {
            case UP:
                return UP_IMAGE;
            case DOWN:
                return DOWN_IMAGE;
            case LEFT:
                return LEFT_IMAGE;
            default:
                return RIGHT_IMAGE;
        }
    }

    private static PGraphics createRightImage() {
        PGraphics g = createBaseGraphics();
        g.arc(0, 0, DIAMETER, DIAMETER, 30, 330);
        return g;
    }

    private static PGraphics createLeftImage() {
        PGraphics g = createBaseGraphics();
        g.arc(0, 0, DIAMETER, DIAMETER, 210, 150);
        return g;
    }

    private static PGraphics createUpImage() {
        PGraphics g = createBaseGraphics();
        g.arc(0, 0, DIAMETER, DIAMETER, 300, 240);
        return g;
    }

    private static PGraphics createDownImage() {
        PGraphics g = createBaseGraphics();
        g.arc(0, 0, DIAMETER, DIAMETER, 120, 60);
        return g;
    }

    private static PGraphics createBaseGraphics() {
        PGraphics g = createGraphics(ceil(DIAMETER), ceil(DIAMETER));
        setSmooth(g);
        g.noStroke();
        g.ellipseMode(CORNER_MODE);
        g.angleMode(DEGREES);
        g.fill(0xFFFFFF00);
        return g;
    }

    private static final PGraphics RIGHT_IMAGE = createRightImage();
    private static final PGraphics LEFT_IMAGE = createLeftImage();
    private static final PGraphics UP_IMAGE = createUpImage();
    private static final PGraphics DOWN_IMAGE = createDownImage();
}