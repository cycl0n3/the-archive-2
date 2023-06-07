package components;

import javax.swing.*;
import java.awt.*;

public class LineBreak {

    private final JPanel panel;

    public LineBreak(int height) {
        panel = new JPanel();
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(10000000, height));
    }

    public LineBreak on(JComponent component) {
        int width = component.getPreferredSize().width;
        panel.setPreferredSize(new Dimension(width, panel.getPreferredSize().height));
        component.add(panel);
        return this;
    }

}
