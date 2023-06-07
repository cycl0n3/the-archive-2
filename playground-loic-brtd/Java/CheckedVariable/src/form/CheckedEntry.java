package form;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Arrays;
import java.util.function.Function;

import static javax.swing.BorderFactory.*;

public class CheckedEntry<T> extends JPanel {

    enum State {
        NEUTRAL, ERROR, SUCCESS
    }

    public static final CompoundBorder SUCCESS_BORDER = createCompoundBorder(
            createLineBorder(new Color(100, 200, 100), 2),
            createEmptyBorder(4, 8, 4, 8)
    );
    public static final CompoundBorder ERROR_BORDER = createCompoundBorder(
            createLineBorder(new Color(255, 50, 50), 2),
            createEmptyBorder(4, 8, 4, 8)
    );
    public static final CompoundBorder NEUTRAL_BORDER = createCompoundBorder(
            createLineBorder(new Color(0, 0, 0, 0), 2),
            createEmptyBorder(4, 8, 4, 8)
    );

    private final JTextField field;
    private final JLabel errorLabel;
    private State state = State.NEUTRAL;
    private T value = null;

    public CheckedEntry(String pseudo, Function<CheckedEntry<T>, T> checker) {
        super(new GridLayout(3, 1));
        JLabel title;
        add(title = new JLabel(pseudo));
        add(field = new JTextField());
        add(errorLabel = new JLabel());

        title.setFont(new Font("Arial", Font.BOLD, 14));

        field.setPreferredSize(new Dimension(200, 36));
        field.setBorder(NEUTRAL_BORDER);
        field.setFont(new Font("Arial", Font.PLAIN, 14));

        errorLabel.setForeground(new Color(255, 50, 50));
        errorLabel.setFont(new Font("Arial", Font.BOLD, 12));

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                value = checker.apply(CheckedEntry.this);
            }
        });
    }

    public String getText() {
        return field.getText();
    }

    public void setText(String text) {
        field.setText(text);
    }

    public State getState() {
        return state;
    }

    public T getValue() {
        return value;
    }

    public void setErrorState(String message) {
        errorLabel.setText(message);
        field.setBorder(ERROR_BORDER);
        state = State.ERROR;
    }

    public void setSuccessState() {
        errorLabel.setText("");
        field.setBorder(SUCCESS_BORDER);
        state = State.SUCCESS;
    }

    public void setNeutralState() {
        errorLabel.setText("");
        field.setBorder(NEUTRAL_BORDER);
        state = State.NEUTRAL;
    }

    public static <T> boolean all(State state, CheckedEntry<T>... entries) {
        return Arrays.stream(entries)
                .noneMatch(entry -> entry.getState() != state);
    }

}
