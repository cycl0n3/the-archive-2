package com.picasso.gui;

import com.picasso.app.ImageEditor;
import com.picasso.gui.theme.BaseTheme;
import com.picasso.gui.theme.ThemeManager;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.border.DropShadowBorder;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static java.awt.GridBagConstraints.*;

public class PImageEditorPanel extends JXPanel {

    private static final ThemeManager themes = ThemeManager.getInstance();

    private JComponent titleBar;
    private PCanvas canvas;

    private List<Runnable> onClose = new ArrayList<>();

    public PImageEditorPanel(ImageEditor imageEditor) {
        // Overall layout
        setLayout(new BorderLayout());
        setOpaque(false);
        setBorder(createBorder());

        // Title bar
        titleBar = new JPanel(new GridBagLayout());
        titleBar.setBackground(themes.current().menu());
        JLabel titleLabel = new JLabel(imageEditor.getName());
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(themes.current().onMenu());
        titleLabel.setFont(themes.current().mainFont());
        titleBar.add(titleLabel, gridBag(0, 0, 1, 1, CENTER, 1, HORIZONTAL));
        PIcon cross = new PIcon("/image/cross.png");
        cross.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (cross.isHovered())
                    onClose.forEach(Runnable::run);
            }
        });
        titleBar.add(cross, gridBag(1, 0, 1, 1, LINE_END, 0, NONE));
        add(titleBar, BorderLayout.NORTH);

        // Image
        canvas = new PCanvas(imageEditor.getBufferedImage());
        JScrollPane scrollPane = createScrollPane(canvas);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JScrollPane createScrollPane(PCanvas canvas) {
        JScrollPane scrollPane = new JScrollPane(canvas);
        scrollPane.setBackground(themes.current().menu());
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        JScrollBar vsb = scrollPane.getVerticalScrollBar();
        JScrollBar hsb = scrollPane.getHorizontalScrollBar();
        vsb.setUnitIncrement(12);
        hsb.setUnitIncrement(12);
        vsb.setPreferredSize(new Dimension(12, vsb.getPreferredSize().height));
        hsb.setPreferredSize(new Dimension(hsb.getPreferredSize().width, 12));
        vsb.setUI(new CustomScrollBarUI());
        hsb.setUI(new CustomScrollBarUI());
        return scrollPane;
    }

    private GridBagConstraints gridBag(int x, int y, int w, int h, int anchor, int wx, int fill) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = w;
        c.gridheight = h;
        c.anchor = anchor;
        c.weightx = wx;
        c.fill = fill;
        c.insets = new Insets(4, 4, 4, 4);
        return c;
    }

    private Border createBorder() {
        Border lineBorder = new LineBorder(themes.current().menuBorder());
        DropShadowBorder shadow = new DropShadowBorder();
        shadow.setShadowColor(Color.BLACK);
        shadow.setShowLeftShadow(true);
        shadow.setShowRightShadow(true);
        shadow.setShowBottomShadow(true);
        // shadow.setShowTopShadow(true);
        shadow.setShadowOpacity(0.3f);
        shadow.setShadowSize(8);
        shadow.setCornerSize(8);
        return lineBorder; // new CompoundBorder(shadow, lineBorder);
    }

    public void setScale(float scale) {
        if (scale > 0.1 && scale < 128) {
            canvas.setScale(scale);
            updateUI();
        }
    }

    public float getScale() {
        return canvas.getScale();
    }

    public void onClose(Runnable runnable) {
        onClose.add(runnable);
    }

    public JComponent getTitleBar() {
        return titleBar;
    }

    public PCanvas getCanvas() {
        return canvas;
    }
}
