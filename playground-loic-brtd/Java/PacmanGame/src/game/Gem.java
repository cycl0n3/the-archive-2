package game;

import static game.Game.*;

import core.PGraphics;

public abstract class Gem {
    // Coordinates of the gem's center
    public final int x, y;
    public final float d;
    public final float r;

    public Gem(int x, int y, float d) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.r = d / 2;
    }

    public void doEffect() {
    }

    public void show(PGraphics g){
    }

    // Implementations

    public static class Dot extends Gem {
        Dot(int x, int y) {
            super(x, y, UNIT * 0.2f);
        }

        public void show(PGraphics g) {
            g.ellipseMode(CENTER_MODE);
            g.noStroke();
            g.strokeWeight(0);
            g.fill(0xFFFCBA94);
            g.circle(x, y, d);
        }
    }

    public static class Ball extends Gem {
        public Ball(int x, int y) {
            super(x, y, UNIT * 0.6f);
        }

        @Override
        public void doEffect() {
            board.forEachGhost(ghost -> ghost.changeBehaviour(new Behaviour.Frightened(ghost)));
        }


        public void show(PGraphics g) {
            g.ellipseMode(CENTER_MODE);
            g.strokeWeight(0);
            g.noStroke();
            g.fill(0xFFFCBA94);
            g.circle(x, y, d);
        }
    }
}
