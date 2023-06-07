package game;

import javax.swing.*;

import static core.PCanvas.*;
import static game.Game.board;
import static game.Game.player;

public abstract class Behaviour {

    protected Ghost ghost;

    public Behaviour(Ghost ghost) {
        this.ghost = ghost;
    }

    public void initialize() {
    }

    public void update() {
    }

    public void terminate() {
    }

    // Implementations

    public static class Frightened extends Behaviour {

        private Timer timer = null;

        public Frightened(Ghost ghost) {
            super(ghost);
        }

        @Override
        public void initialize() {
            ghost.color = 0xFF0000FF;
            ghost.speed = 1 / 30f;
            timer = new Timer(8_000, e -> {
                ghost.color = ghost.BASE_COLOR;
                ghost.speed = ghost.BASE_SPEED;
                timer = null;
                ghost.changeBehaviour(new ChasingPlayer(ghost));
            });
            timer.setRepeats(false);
            timer.start();
        }

        @Override
        public void update() {
            ghost.changeDirection(ghost.chooseRandomDirection());
        }

        @Override
        public void terminate() {
            if (timer != null) {
                timer.stop();
                timer = null;
            }
        }
    }

    public static class ExitingFortress extends Behaviour {

        private Timer timer;

        public ExitingFortress(Ghost ghost) {
            super(ghost);
        }

        @Override
        public void initialize() {
            timer = new Timer(1_000, e -> ghost.changeBehaviour(new RandomTarget(ghost)));
            timer.setRepeats(false);
            timer.start();
        }

        @Override
        public void update() {
            ghost.changeDirection(ghost.chooseDirectionTowardsTarget(10, 1));
        }

        @Override
        public void terminate() {
            if (timer != null) {
                timer.stop();
                timer = null;
            }
        }
    }

    public static class RandomTarget extends Behaviour {

        public RandomTarget(Ghost ghost) {
            super(ghost);
        }

        @Override
        public void initialize() {
            ghost.randomTargetX = floor(random(board.cols));
            ghost.randomTargetY = floor(random(board.rows));
        }

        @Override
        public void update() {
            ghost.changeDirection(ghost.chooseDirectionTowardsTarget(ghost.randomTargetX, ghost.randomTargetY));
        }
    }

    public static class ChasingPlayer extends Behaviour {

        public ChasingPlayer(Ghost ghost) {
            super(ghost);
        }

        @Override
        public void update() {
            ghost.changeDirection(ghost.chooseDirectionTowardsTarget(player.x, player.y));
        }
    }

}
