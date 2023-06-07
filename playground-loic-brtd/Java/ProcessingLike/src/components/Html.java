package components;

import core.ColorManagement;

import javax.swing.*;
import java.awt.*;

public class Html {

    private final JLabel label;

    public Html(String text) {
        label = new JLabel(wrap(text));
        label.setOpaque(false);
        label.setFont(Components.FONT);
        label.setBorder(Components.PADDING);
    }

    protected String wrap(String text) {
        return "<html>" + text + "</html>";
    }

    public Html color(int color) {
        label.setForeground(new Color(ColorManagement.color(color), true));
        return this;
    }

    public Html html(String text) {
        label.setText(wrap(text));
        return this;
    }

    public Html html(Object object) {
        return html(String.valueOf(object));
    }

    public Html on(JComponent component) {
        component.add(label);
        return this;
    }

}
