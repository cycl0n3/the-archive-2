package constants;

import java.awt.event.MouseEvent;

public enum MouseButton {

    LEFT,
    CENTER,
    RIGHT,
    NO_BUTTON;

    public static MouseButton fromMouseEvent(MouseEvent e) {
        switch (e.getButton()) {
        case MouseEvent.BUTTON1:
            return LEFT;
        case MouseEvent.BUTTON2:
            return CENTER;
        case MouseEvent.BUTTON3:
            return RIGHT;
        default:
            return NO_BUTTON;
        }
    }
}
