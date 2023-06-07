package components;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class Spinner {

    private JSpinner spinner;

    public Spinner(Float min, Float max, float value, float step) {
        min = min != null ? min : Float.NEGATIVE_INFINITY;
        max = max != null ? max : Float.POSITIVE_INFINITY;

        if (Double.compare(step, 0) <= 0) {
            throw new IllegalArgumentException("Spinner step must be strictly positive");
        }

        spinner = new JSpinner(new SpinnerNumberModel(value, (float) min, (float) max, step));
        spinner.setPreferredSize(new Dimension(100, 40));
        spinner.setFont(Components.FONT);
        spinner.setBorder(BorderFactory.createEmptyBorder());
        spinner.setForeground(Color.BLACK);
        final JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor) editor;
            spinnerEditor.getTextField().setHorizontalAlignment(JTextField.LEFT);
        }
        editor.setBackground(Color.WHITE);
        editor.setBorder(new CompoundBorder(Components.BORDER, Components.PADDING));
    }

    public float value() {
        return Float.valueOf(spinner.getValue().toString());
    }

    public Spinner changed(ChangeListener changeListener) {
        spinner.addChangeListener(changeListener);
        return this;
    }

    public Spinner on(JComponent component) {
        component.add(spinner);
        return this;
    }

}
