package components;

import core.PCanvas;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class Slider {

    private final JSlider slider;
    private final double step;

    public Slider(int min, int max, Float value, Float step) {
        this.step = step != null ? step : 1.0;

        if (Double.compare(this.step, 0) <= 0) {
            throw new IllegalArgumentException("Slider step must be strictly positive");
        }

        slider = new JSlider();
        slider.setMinimum(floatToInt(min));
        slider.setMaximum(floatToInt(max));
        slider.setValue(floatToInt(value != null ? value : (min + max) / 2));
        slider.setSnapToTicks(true);
        slider.setOpaque(false);
    }

    private int floatToInt(float d) {
        return (int) (d / this.step);
    }

    public float value() {
        return (float) (slider.getValue() * step);
    }

    public Slider value(float value) {
        value = PCanvas.constrain(value, slider.getMinimum(), slider.getMaximum());
        slider.setValue(floatToInt(value));
        return this;
    }

    public Slider width(int width) {
        slider.setPreferredSize(new Dimension(width, slider.getPreferredSize().height));
        return this;
    }

    public Slider changed(ChangeListener changeListener) {
        slider.addChangeListener(changeListener);
        return this;
    }

    public Slider vertical() {
        slider.setOrientation(SwingConstants.VERTICAL);
        return this;
    }

    public Slider on(JComponent component) {
        component.add(slider);
        return this;
    }

}
