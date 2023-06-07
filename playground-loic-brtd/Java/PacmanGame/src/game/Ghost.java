package game;

import core.PGraphics;

import javax.swing.*;

import static game.Game.*;

public class Ghost extends MovingEntity {

    public final int BASE_COLOR;
    public final float BASE_SPEED;
    public game.Behaviour behaviour;

    public Ghost(int x, int y, float speed, int color) {
        super(x, y, speed, color);
        BASE_COLOR = color;
        BASE_SPEED = speed;
        changeBehaviour(new Behaviour.ExitingFortress(this));
        onPositionUpdated();
        new Timer(10_000, e -> {
            if (behaviour instanceof Behaviour.RandomTarget) {
                changeBehaviour(new Behaviour.ChasingPlayer(this));
            } else if (behaviour instanceof Behaviour.ChasingPlayer){
                changeBehaviour(new Behaviour.RandomTarget(this));
            }
        }).start();
    }

    public void changeBehaviour(game.Behaviour behaviour) {
        if (this.behaviour != null) {
            this.behaviour.terminate();
        }
        this.behaviour = behaviour;
        this.behaviour.initialize();
    }

    int randomTargetX, randomTargetY;

    @Override
    protected void onPositionUpdated() {
        behaviour.update();
    }

    @Override
    public void update() {
        super.update();
        if (!(behaviour instanceof Behaviour.Frightened) && this.touches(player)) {
            frozen = true;
            over = true;
            behaviour.terminate();
        }
    }

    Direction chooseDirectionTowardsTarget(int tx, int ty) {
        Direction[] possibleMoves = findPossibleMoves();
        Direction bestMove = possibleMoves[0];
        float shortestDistanceToTarget = board.cols + board.rows;
        for (Direction move : possibleMoves) {
            float d = dist(x + move.dx, y + move.dy, tx, ty);
            if (d < shortestDistanceToTarget) {
                bestMove = move;
                shortestDistanceToTarget = d;
            }
        }
        return bestMove;
    }

    Direction chooseRandomDirection() {
        Direction[] possibleMoves = findPossibleMoves();
        return random(possibleMoves);
    }

    @Override
    public void show(PGraphics g) {
        g.fill(color);
        g.noStroke();
        g.ellipseMode(CORNER_MODE);
        g.circle(drawnX, drawnY, DIAMETER);
        // Wrap around the board horizontally
        if (drawnX > WIDTH - UNIT) {
            g.circle(drawnX - WIDTH, drawnY, DIAMETER);
        } else if (drawnX < UNIT) {
            g.circle(drawnX + WIDTH, drawnY, DIAMETER);
        }
    }

}