package components;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Button {

    private final JButton button;

    public Button(String text) {
        button = new JButton(text);
        button.setFont(Components.FONT);
    }

    public String text() {
        return button.getText();
    }

    public Button text(String text) {
        button.setText(text);
        return this;
    }

    public Button mousePressed(Runnable runnable) {
        button.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                runnable.run();
            }
        });
        return this;
    }

    public Button mouseReleased(Runnable runnable) {
        button.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                runnable.run();
            }
        });
        return this;
    }

    public Button on(JComponent component) {
        component.add(button);
        return this;
    }

}
