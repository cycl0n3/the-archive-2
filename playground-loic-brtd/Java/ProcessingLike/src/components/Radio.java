package components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Radio {

    private final ButtonGroup group;
    private final JPanel wrapper;
    private final List<JRadioButton> buttons;

    public Radio() {
        group = new ButtonGroup();
        wrapper = new JPanel(new FlowLayout());
        buttons = new ArrayList<>();

        wrapper.setOpaque(false);
    }

    public Radio option(String option) {
        final JRadioButton btn = new JRadioButton(option);
        btn.setFont(Components.FONT);
        btn.setOpaque(false);
        group.add(btn);
        buttons.add(btn);
        wrapper.add(btn);
        if (buttons.size() == 1) {
            buttons.get(0).setSelected(true);
        }
        return this;
    }

    public String value() {
        for (JRadioButton btn : buttons) {
            if (btn.isSelected()) {
                return btn.getText();
            }
        }
        return null;
    }

    public Radio value(String option) {
        for (JRadioButton btn : buttons) {
            if (btn.getText().equals(option)) {
                btn.setSelected(true);
                break;
            }
        }
        return this;
    }

    public Radio on(JComponent component) {
        component.add(wrapper);
        return this;
    }

}
