package com.picasso.gui;

import com.picasso.gui.theme.BaseTheme;
import com.picasso.gui.theme.ThemeManager;
import com.picasso.util.Maths;
import com.picasso.util.Points;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PDesktop extends JPanel {

    private static final ThemeManager themes = ThemeManager.getInstance();

    private List<PImageEditorPanel> imagePanels;
    private Point previousLocation;

    public PDesktop() {
        imagePanels = new ArrayList<>();
        setBackground(themes.current().background());
        setLayout(new DesktopLayout());
    }

    public void addImageEditor(PImageEditorPanel imageEditorPanel) {
        imagePanels.add(0, imageEditorPanel);
        add(imageEditorPanel, 0);
        Point editorLocation = findInitialLocation(new Point(10, 10), imageEditorPanel);
        imageEditorPanel.setLocation(editorLocation);
        addMouseInteractions(imageEditorPanel);
        imageEditorPanel.onClose(() -> closeFrame(imageEditorPanel));
        updateUI();
    }

    private void closeFrame(PImageEditorPanel imageEditorPanel) {
        remove(imageEditorPanel);
        imagePanels.remove(imageEditorPanel);
        repaint();
    }

    public boolean hasFramesOpen() {
        return !imagePanels.isEmpty();
    }

    public void closeSelectedFrame() {
        getSelectedPanel().ifPresent(this::closeFrame);
    }

    private void addMouseInteractions(PImageEditorPanel panel) {
        JComponent handle = panel.getTitleBar();
        handle.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                previousLocation = e.getPoint();
                selectPanel(panel);
            }
        });
        panel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                selectPanel(panel);
            }
        });
        handle.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point offset = Points.sub(e.getPoint(), previousLocation);
                Point newLocation = Points.add(panel.getLocation(), offset);
                Dimension desktopSize = PDesktop.this.getSize();
                Dimension panelSize = panel.getSize();
                int x = Maths.constrain(newLocation.x, 0, desktopSize.width - panelSize.width);
                int y = Maths.constrain(newLocation.y, 0, desktopSize.height - panelSize.height);
                panel.setLocation(x, y);
                updateUI();
            }
        });
    }

    private Optional<PImageEditorPanel> getSelectedPanel() {
        return imagePanels.isEmpty() ? Optional.empty()
                : Optional.of(imagePanels.get(0));
    }

    private void selectPanel(PImageEditorPanel imageEditorPanel) {
        imagePanels.remove(imageEditorPanel);
        imagePanels.add(0, imageEditorPanel);
        setComponentZOrder(imageEditorPanel, 0);
        repaint();
    }

    private Point findInitialLocation(Point tryPos, PImageEditorPanel imagePanel) {
        boolean anyOverlap = false;
        for (PImageEditorPanel p : imagePanels) {
            if (p != imagePanel && p.getLocation().equals(tryPos)) {
                anyOverlap = true;
                break;
            }
        }
        if (anyOverlap) {
            return findInitialLocation(Points.add(tryPos, new Point(20, 30)), imagePanel);
        }
        return tryPos;
    }

    public void zoom() {
        getSelectedPanel().ifPresent(panel -> panel.setScale(panel.getScale() * 2));
        updateUI();
    }

    public void unzoom() {
        getSelectedPanel().ifPresent(panel -> panel.setScale(panel.getScale() / 2));
        updateUI();
    }

    @Override
    public boolean isOptimizedDrawingEnabled() {
        // Required to overlap children components
        return false;
    }
}
