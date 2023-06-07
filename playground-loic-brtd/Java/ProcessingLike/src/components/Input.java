package components;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Input {

    private final JTextField textField;

    public Input(String initialText) {
        textField = new JTextField(initialText);
        textField.setPreferredSize(new Dimension(250, 40));
        textField.setFont(Components.FONT);
        textField.setBorder(new CompoundBorder(Components.BORDER, Components.PADDING));
    }

    public String value() {
        return textField.getText();
    }

    public Input value(String text) {
        textField.setText(text);
        return this;
    }

    public Input input(ActionListener actionListener) {
        System.out.println("input");
        textField.addActionListener(actionListener);
        return this;
    }

    public Input width(int width) {
        textField.setPreferredSize(new Dimension(width, textField.getPreferredSize().height));
        return this;
    }

    public Input keyPressed(Runnable runnable) {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                runnable.run();
            }
        });
        return this;
    }

    public Input on(JComponent component) {
        component.add(textField);
        return this;
    }

}
