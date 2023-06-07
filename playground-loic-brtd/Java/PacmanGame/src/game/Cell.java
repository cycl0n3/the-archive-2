package game;

import static game.Game.*;

import core.PGraphics;

import javax.swing.*;

public abstract class Cell {

    public void show(PGraphics g, int x, int y) { }

    public void doAction(PacMan p) { }

    public abstract boolean isAccessible();

    public static Cell from(char c, int x, int y) {
        switch (c) {
            case ' ':
                return new Empty(null);
            case '.':
                return new Empty(new Gem.Dot(x, y));
            case 'x':
                return new Fortress();
            case 'o':
                return new Empty(new Gem.Ball(x, y));
            case '@':
                return new Door();
            default:
                return new Wall();
        }
    }

    // Implementations

    public static class Door extends Cell {

        boolean closed;

        public Door () {
            closed = false;
            Timer timer = new Timer(2000, e -> {
                closed = true;
            });
            timer.setRepeats(false);
            timer.start();
        }

        @Override
        public void show(PGraphics g, int x, int y) {
            if (closed) {
                g.stroke(255);
                g.strokeWeight(6);
                g.line(x * UNIT, y * UNIT + HALF_UNIT, x * UNIT + UNIT, y * UNIT + HALF_UNIT);
            }
        }

        @Override
        public boolean isAccessible() {
            return !closed;
        }
    }

    public static class Fortress extends Cell {

        boolean accessible;

        public Fortress() {
            accessible = true;
            Timer timer = new Timer(2000, e -> {
                accessible = false;
            });
            timer.setRepeats(false);
            timer.start();
        }

        @Override
        public boolean isAccessible() {
            return accessible;
        }
    }

    public static class Wall extends Cell {

        public boolean isAccessible() {
            return false;
        }

    }

    public static class Empty extends Cell {
        public Gem gem;

        public Empty(Gem gem) {
            this.gem = gem;
        }

        public void show(PGraphics g, int x, int y) {
            if (gem != null) {
                gem.show(g);
            }
        }

        public boolean isAccessible() {
            return true;
        }

        @Override
        public void doAction(PacMan p) {
            if (gem != null) {
                gem.doEffect();
                gem = null;
                p.score++;
            }
        }

    }
}
