package core;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Abstraction of a Timer. Uses a javax.swing.Timer if we're showing the
 * window, or a java.util.Timer if not, to keep the thread alive.
 */
public class PTimer {

    enum Policy {
        ANIMATION, NO_ANIMATION;
    }

    private int framesPerSecond;
    private int period;
    private Policy policy;
    private Runnable action;
    private java.util.Timer utilTimer;
    private javax.swing.Timer swingTimer;
    private boolean startedOnce;
    private boolean running;

    private PTimer(int fps, Runnable action) {
        Objects.requireNonNull(action);
        this.policy = Policy.ANIMATION;
        this.action = action;
        this.swingTimer = new javax.swing.Timer(period, e -> action.run());
        setFrameRate(fps);
    }

    public static PTimer create(int fps, Runnable action) {
        return new PTimer(fps, action);
    }

    public void setFrameRate(int framesPerSecond) {
        if (framesPerSecond <= 0) {
            throw new IllegalArgumentException("Frame rate must be a positive integer");
        }
        this.framesPerSecond = framesPerSecond;
        this.period = 1000 / framesPerSecond;

        if (policy == Policy.ANIMATION) {
            swingTimer.setDelay(period);
            swingTimer.setInitialDelay(period);
        } else {
            stop();
            start(policy);
        }
    }

    public void start(Policy policy) {
        running = true;
        this.policy = policy;
        if (policy == Policy.ANIMATION) {
            if (!swingTimer.isRunning()) {
                if (startedOnce) swingTimer.restart();
                else swingTimer.start();
            }
        } else {
            utilTimer = new Timer();
            utilTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    action.run();
                }
            }, period, period);
        }
        startedOnce = true;
    }

    public boolean isRunning() {
        return running;
    }

    public void stop() {
        if (policy == Policy.ANIMATION) {
            if (swingTimer.isRunning()) {
                swingTimer.stop();
            }
        } else {
            utilTimer.cancel();
        }
        running = false;
    }

}
