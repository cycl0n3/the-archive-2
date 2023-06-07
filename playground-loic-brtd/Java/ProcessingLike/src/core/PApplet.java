package core;

import components.Button;
import components.*;
import constants.PConstants;
import constants.RendererType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PApplet extends PCanvas implements PConstants {

    private static final Dimension frameMinSize = new Dimension(300, 220);
    private static final String CACHE_FILE_NAME = ".p_applet_cache";
    private final JFrame frame;
    private JPanel contentPane;
    private boolean isFullScreen;

    public PApplet() {
        frame = makeFrame();
        showCanvas = true;
        isFullScreen = false;
    }

    public void start() {
        super.start();
        showFrame();
    }

    @Override
    public void size(int width, int height, RendererType rendererType) {
        super.size(width, height, rendererType);
        if (width > 600 || height > 600) {
            contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        }
    }

    @Override
    public void fullScreen(RendererType rendererType) {
        super.fullScreen(rendererType);
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        frame.setUndecorated(true);
        isFullScreen = true;
    }

    protected void noCanvas() {
        showCanvas = false;
        if (frame.isVisible()) {
            frame.setVisible(false);
        }
    }

    private void showFrame() {
        if (showCanvas) {
            boolean useCachedPosition = false;
            int posX = 0, posY = 0;
            if (!isFullScreen) {
                File cache = new File(CACHE_FILE_NAME);
                if (cache.exists() && cache.isFile()) {
                    try {
                        Properties props = new Properties();
                        props.load(new FileInputStream(cache));
                        posX = Integer.parseInt(props.getProperty("frame_x", "0"));
                        posY = Integer.parseInt(props.getProperty("frame_y", "0"));
                        useCachedPosition = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            canvas().setPreferredSize(new Dimension(width, height));
            frame.pack();
            if (useCachedPosition) {
                frame.setLocation(posX, posY);
            } else {
                frame.setLocationRelativeTo(null);
            }
            frame.setVisible(true);
        }
    }

    @Override
    protected void updateMousePositionOnPanel() {
        // Uses the mousePosition on the screen
        if (showCanvas) {
            final Rectangle frameBounds = frame.getBounds();
            final Rectangle rootPaneBounds = frame.getRootPane().getBounds();
            final Rectangle panelBounds = canvas().getBounds();
            mouseX = screenMouseX - (frameBounds.x + rootPaneBounds.x + panelBounds.x);
            mouseY = screenMouseY - (frameBounds.y + rootPaneBounds.y + panelBounds.y);
        }
    }

    private JFrame makeFrame() {
        contentPane = new JPanel(new GridBagLayout());
        contentPane.setOpaque(false);
        contentPane.add(canvas());
        canvas().setBackground(new Color(204, 204, 204));
        canvas().addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    exitProcedure();
                }
            }
        });
        canvas().addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                canvas().requestFocusInWindow();
            }
        });

        final JFrame f = new JFrame(this.getClass().getSimpleName());
        f.setContentPane(contentPane);
        f.setBackground(new Color(221, 221, 221));
        f.setResizable(false);
        f.setMinimumSize(frameMinSize);
        f.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        f.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                exitProcedure();
            }
        });
        return f;
    }

    private void exitProcedure() {
        onClose();
        if (!isFullScreen) {
            try {
                Properties props = new Properties();
                props.setProperty("frame_x", frame.getX() + "");
                props.setProperty("frame_y", frame.getY() + "");
                props.store(new FileOutputStream(CACHE_FILE_NAME), "Cached properties for PApplet");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }

    public void exit() {
        System.exit(0);
    }

    protected void onClose() {
        // To be overriden
    }

    // Vectors

    public static PVector createVector() {
        return new PVector();
    }

    public static PVector createVector(float x, float y) {
        return new PVector(x, y);
    }

    public static PVector createVector(float x, float y, float z) {
        return new PVector(x, y, z);
    }

    // Slider

    public Slider createSlider(int min, int max) {
        return new Slider(min, max, null, null).on(canvas());
    }

    public Slider createSlider(int min, int max, float value) {
        return new Slider(min, max, value, null).on(canvas());
    }

    public Slider createSlider(int min, int max, float value, float step) {
        return new Slider(min, max, value, step).on(canvas());
    }

    // Spinner

    public Spinner createSpinner(float min, float max, float value, float step) {
        return new Spinner(min, max, value, step).on(canvas());
    }

    public Spinner createSpinner(float value, float step) {
        return new Spinner(null, null, value, step).on(canvas());
    }

    public Spinner createSpinner(float value) {
        return new Spinner(null, null, value, 1).on(canvas());
    }

    public Spinner createSpinner() {
        return new Spinner(null, null, 0, 1).on(canvas());
    }

    // Html

    public Html createHtml(String text) {
        return new Html(text).on(canvas());
    }

    public Html createHtml() {
        return new Html("").on(canvas());
    }

    public Html createP(String text) {
        return new Paragraph(text).on(canvas());
    }

    public Html createP() {
        return new Paragraph("").on(canvas());
    }

    // Input

    public Input createInput(String text) {
        return new Input(text).on(canvas());
    }

    public Input createInput() {
        return new Input("").on(canvas());
    }

    // Button

    public Button createButton() {
        return new Button("").on(canvas());
    }

    public Button createButton(String text) {
        return new Button(text).on(canvas());
    }

    // Radio

    public Radio createRadio() {
        return new Radio().on(canvas());
    }

    // Break line

    public LineBreak breakLine() {
        return new LineBreak(0).on(canvas());
    }

    public LineBreak breakLine(int height) {
        return new LineBreak(height).on(canvas());
    }

    // Quick start

    public static void run() {
        // Get main class name
        String mainClassName = "";
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        if (trace.length > 0) {
            mainClassName = trace[trace.length - 1].getClassName();
        } else {
            error("Cannot find main class name");
        }

        // Get main class
        Class<?> mainClass = null;
        try {
            mainClass = Class.forName(mainClassName);
        } catch (ClassNotFoundException e) {
            error("Cannot find main class");
        }

        // Check for the PApplet type
        PApplet app = null;
        if (PApplet.class.isAssignableFrom(mainClass)) {
            try {
                app = (PApplet) mainClass.newInstance();
            } catch (InstantiationException e) {
                error("Cannot instantiate the main class");
            } catch (IllegalAccessException e) {
                error("No access to the constructor");
            }
        } else {
            error("The main class must extend ProcessingLike");
        }

        // Start the app
        app.start();
    }

}
