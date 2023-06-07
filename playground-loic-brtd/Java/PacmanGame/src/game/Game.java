package game;

import core.PApplet;
import core.PGraphics;

public class Game extends PApplet {

    public static final int UNIT = 20;
    public static final int HALF_UNIT = UNIT / 2;
    public static int WIDTH, HEIGHT;
    public static Board board;
    public static PacMan player;
    public static boolean frozen = false;
    public static boolean over = false;

    @Override
    public void setup() {
        board = new Board("res/board.txt");
        WIDTH = board.cols * UNIT;
        HEIGHT = board.rows * UNIT + UNIT * 2;
        size(WIDTH, HEIGHT);

        player = new PacMan(10, 20, 1/10f);
        Ghost red = new Ghost(10, 12, 1 / 12f, 0xFFFC0204);
        Ghost cyan = new Ghost(9, 13, 1 / 15f, 0xFF04FEDC);
        Ghost orange = new Ghost(10, 13, 1 / 18f, 0xFFFCBA44);
        Ghost pink = new Ghost(11, 13, 1 / 16f, 0xFFFCBADC);

        board.setPlayer(player);
        board.addGhost(red);
        board.addGhost(cyan);
        board.addGhost(orange);
        board.addGhost(pink);

        setSmooth(this);
        angleMode(DEGREES);
        textFont("Monospaced");
    }

    public static void setSmooth(PGraphics g) {
       g.noSmooth();
    }

    @Override
    public void draw() {
        if (!frozen) {
            board.update();
        }

        background(0);
        board.show(this);

        fill(255);
        textSize(UNIT);
        textAlign(LEFT, CENTER);
        text("SCORE: " + player.score, HALF_UNIT, HEIGHT - UNIT);
        textSize(HALF_UNIT);

        if (over) {
            textSize(UNIT * 2);
            textAlign(CENTER, CENTER);
            text("GAME OVER", WIDTH / 2f, HEIGHT / 2f);
        }
    }

    @Override
    public void keyPressed() {
        if (keyCode == ARROW_UP) {
            player.changeDirection(Direction.UP);
        } else if (keyCode == ARROW_RIGHT) {
            player.changeDirection(Direction.RIGHT);
        } else if (keyCode == ARROW_DOWN) {
            player.changeDirection(Direction.DOWN);
        } else if (keyCode == ARROW_LEFT) {
            player.changeDirection(Direction.LEFT);
        } else if (keyCode == SPACE) {
            frozen = !frozen;
        }
    }

    public static void main(String[] args) {
        PApplet.run();
    }

}
