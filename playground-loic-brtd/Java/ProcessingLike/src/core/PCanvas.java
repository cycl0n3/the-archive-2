package core;

import constants.MouseButton;
import constants.RendererType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static core.PTimer.Policy;
import static core.PTimer.create;

public class PCanvas extends PGraphics {

    private static final int DEFAULT_FRAME_RATE = 60;

    private final PTimer timer;
    private final FPSMeter fpsMeter;
    private JComponent canvas;
    protected boolean showCanvas;

    private boolean setupDone = false;
    private boolean shouldLoop = true;
    private boolean alreadySized = false;

    public int frameCount;
    public float frameRate;
    public float ellapsedMillis;
    public int mouseX, mouseY, pmouseX, pmouseY;
    public int screenMouseX, screenMouseY;
    public int screenWidth, screenHeight;
    public boolean mouseIsPressed = false;
    public MouseButton mouseButton = MouseButton.NO_BUTTON;

    public PCanvas() {
        super();
        timer = create(DEFAULT_FRAME_RATE, this::performTimerAction);
        canvas = makePanel();
        fpsMeter = new FPSMeter();
        addMouseListeners();
        addKeyListeners();
        updateScreenSize();
        super.initRenderer(100, 100, RendererType.P2D);
    }

    private void performTimerAction() {
        try {
            redraw();
        } catch (Throwable t) {
            manageError(t);
        }
    }

    public void start() {
        try {
            setup();
            setupDone = true;

            if (shouldLoop) {
                timer.start(showCanvas ? Policy.ANIMATION : Policy.NO_ANIMATION);
            } else {
                redraw();
            }
        } catch (Throwable t) {
            manageError(t);
        }
    }

    private JPanel makePanel() {
        @SuppressWarnings("serial") final JPanel p = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(renderer.getImage(), 0, 0, null);
            }
        };
        p.setIgnoreRepaint(true);
        p.setFocusable(true);
        p.requestFocusInWindow();
        return p;
    }

    private void addMouseListeners() {
        canvas.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                updateMousePositionOnPanel(e);
                PCanvas.this.mouseIsPressed = true;
                PCanvas.this.mouseDragged();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                updateMousePositionOnPanel(e);
                PCanvas.this.mouseMoved();
            }
        });
        canvas.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                updateMousePositionOnPanel(e);
                PCanvas.this.mouseIsPressed = true;
                PCanvas.this.mouseButton = MouseButton.fromMouseEvent(e);
                PCanvas.this.mousePressed();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                updateMousePositionOnPanel(e);
                PCanvas.this.mouseIsPressed = false;
                PCanvas.this.mouseReleased();
            }
        });
    }

    private void addKeyListeners() {
        canvas.addKeyListener(new KeyListener() {

            private void handleEvent(KeyEvent e) {
                PCanvas.this.keyCode = e.getKeyCode();
                PCanvas.this.key = e.getKeyChar();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (!pressedKeys.contains(e.getKeyCode())) {
                    handleEvent(e);
                    pressedKeys.add(e.getKeyCode());
                    PCanvas.this.keyPressed();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                handleEvent(e);
                pressedKeys.remove(e.getKeyCode());
                PCanvas.this.keyReleased();
            }

            @Override
            public void keyTyped(KeyEvent e) {
                handleEvent(e);
                PCanvas.this.keyTyped();
            }
        });
    }

    private void updateScreenSize() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.width;
        screenHeight = screenSize.height;
    }

    JComponent canvas() {
        return canvas;
    }

    // Error checking

    private void checkBeforeDraw(String methodName) {
        if (setupDone) {
            error("Method " + methodName + "() must be called before the draw() loop.");
        }
    }

    private void checkSizedOnlyOnce() {
        if (alreadySized) {
            error("Canvas can only be sized once (fullScreen() and size() both set the size of the canvas).");
        } else {
            alreadySized = true;
        }
    }

    // Setup only methods

    public static final RendererType P2D = RendererType.P2D;
    public static final RendererType P3D = RendererType.P3D;

    public void size(int width, int height, RendererType rendererType) {
        checkBeforeDraw("size");
        checkSizedOnlyOnce();
        super.initRenderer(width, height, rendererType);
    }

    public void size(int width, int height) {
        size(width, height, RendererType.P2D);
    }

    public void fullScreen(RendererType rendererType) {
        checkBeforeDraw("fullScreen");
        checkSizedOnlyOnce();
        super.initRenderer(screenWidth, screenHeight, rendererType);
    }

    public void fullScreen() {
        fullScreen(RendererType.P2D);
    }

    // Draw loop control

    public void toggleLoop() {
        if (timer.isRunning())
            noLoop();
        else
            loop();
    }

    public void noLoop() {
        shouldLoop = false;
        if (timer.isRunning()) {
            timer.stop();
        }
    }

    public void loop() {
        shouldLoop = true;
        if (!timer.isRunning()) {
            timer.start(showCanvas ? Policy.ANIMATION : Policy.NO_ANIMATION);
        }
    }

    public final void redraw() {
        ellapsedMillis = fpsMeter.ellapsedMillis();
        frameRate = fpsMeter.tick();
        updateMousePositionOnScreen();
        updateMousePositionOnPanel();
        frameCount++;
        beginDraw();
        draw();
        endDraw();
        canvas.repaint();
        savePreviousMousePosition();
    }

    // Mouse position

    private void updateMousePositionOnScreen() {
        final Point screenPosition = MouseInfo.getPointerInfo().getLocation();
        if (screenPosition != null) {
            screenMouseX = screenPosition.x;
            screenMouseY = screenPosition.y;
        }
    }

    protected void updateMousePositionOnPanel() {
        final Point panelPosition = canvas.getMousePosition(true);
        if (panelPosition != null) {
            mouseX = panelPosition.x;
            mouseY = panelPosition.y;
        }
    }

    private void updateMousePositionOnPanel(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    private void savePreviousMousePosition() {
        pmouseX = mouseX;
        pmouseY = mouseY;
    }

    public void frameRate(float framesPerSecond) {
        timer.setFrameRate((int) framesPerSecond);
    }

    // Main methods to override

    protected void setup() {
    }

    protected void draw() {
    }

    // Mouse

    protected void mousePressed() {
    }

    protected void mouseReleased() {
    }

    protected void mouseDragged() {
    }

    protected void mouseMoved() {
    }

    // Keyboard

    private final Set<Integer> pressedKeys = new HashSet<>();

    public char key;
    public int keyCode;

    public static final int ARROW_UP = KeyEvent.VK_UP;
    public static final int ARROW_RIGHT = KeyEvent.VK_RIGHT;
    public static final int ARROW_DOWN = KeyEvent.VK_DOWN;
    public static final int ARROW_LEFT = KeyEvent.VK_LEFT;
    public static final int ALT = KeyEvent.VK_ALT;
    public static final int CONTROL = KeyEvent.VK_CONTROL;
    public static final int SHIFT = KeyEvent.VK_SHIFT;
    public static final int SPACE = KeyEvent.VK_SPACE;

    protected void keyPressed() {
    }

    protected void keyReleased() {
    }

    protected void keyTyped() {
    }

    public boolean keyIsDown(int keyCode) {
        return pressedKeys.contains(keyCode);
    }

    // Pixels

    public int[] pixels;

    public void loadPixels() {
        pixels = new int[width * height];
        int index = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[index++] = renderer.getImage().getRGB(x, y);
            }
        }
    }

    public void updatePixels() {
        int index = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                renderer.getImage().setRGB(x, y, pixels[index++]);
            }
        }
    }

    // Cursor

    private static final BufferedImage BLANK_CURSOR_IMAGE = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
    private static final Cursor BLANK_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(BLANK_CURSOR_IMAGE,
            new Point(), "blank cursor");
    public static final Cursor ARROW = Cursor.getDefaultCursor();
    public static final Cursor CROSS = new Cursor(Cursor.CROSSHAIR_CURSOR);
    public static final Cursor HAND = new Cursor(Cursor.HAND_CURSOR);
    public static final Cursor MOVE = new Cursor(Cursor.MOVE_CURSOR);
    public static final Cursor TEXT = new Cursor(Cursor.TEXT_CURSOR);
    public static final Cursor WAIT = new Cursor(Cursor.WAIT_CURSOR);

    public void noCursor() {
        canvas.setCursor(BLANK_CURSOR);
    }

    public void cursor(Cursor cursor) {
        canvas.setCursor(cursor);
    }

    public static PImage loadImage(String filePath) {
        return new PImage(filePath);
    }

    public PImage createImage() {
        final ColorModel cm = this.renderer.getImage().getColorModel();
        final boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        final WritableRaster raster = this.renderer.getImage()
                .copyData(this.renderer.getImage().getRaster().createCompatibleWritableRaster());
        return new PImage(new BufferedImage(cm, raster, isAlphaPremultiplied, null));
    }

    public void saveFrame(String fileName) {
        final String[] split = fileName.split("\\.");
        if (split.length > 2) {
            error("Filename '" + fileName + "' is invalid");
        } else {
            final String name = split[0];
            final String extension = split.length == 2 ? split[1] : "png";
            saveImage(this.renderer.getImage(), name, extension);
        }
    }

    private static void saveImage(BufferedImage bufferedImage, String fileName, String extension) {
        if (extension.equals("png")) {
            final File outputFile = new File(fileName + '.' + extension);
            try {
                ImageIO.write(bufferedImage, extension, outputFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            error("File extension '" + extension + "' not supported.");
        }
    }

    public int get(int x, int y) {
        x = PCanvas.constrain(x, 0, width - 1);
        y = PCanvas.constrain(y, 0, height - 1);
        return renderer.getImage().getRGB(x, y);
    }

    // Graphics

    public static PGraphics createGraphics(int width, int height) {
        return new PGraphics(width, height, RendererType.P2D);
    }

    public static PGraphics createGraphics(int width, int height, RendererType rendererType) {
        return new PGraphics(width, height, rendererType);
    }

    // Math

    private static Random random;

    public static final float PI = (float) Math.PI;
    public static final float HALF_PI = PI / 2;
    public static final float TWO_PI = PI * 2;
    public static final float E = (float) Math.E;

    public static float random(float min, float max) {
        return random() * (max - min) + min;
    }

    public static float random() {
        return random(1f);
    }

    public static float random(float max) {
        if (random == null) {
            random = new Random();
        }
        return random.nextFloat() * max;
    }

    public static <T> T random(T[] array) {
        if (array.length == 0) {
            return null;
        }
        int index = (int) Math.floor(Math.random() * array.length);
        return array[index];
    }

    public static <T> T random(List<T> list) {
        if (list.size() == 0) {
            return null;
        }
        int index = (int) Math.floor(Math.random() * list.size());
        return list.get(index);
    }

    public static <T> void shuffle(T[] array) {
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            int randomIndexToSwap = rand.nextInt(array.length);
            T temp = array[randomIndexToSwap];
            array[randomIndexToSwap] = array[i];
            array[i] = temp;
        }
    }

    public static <T> void shuffle(int[] array) {
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            int randomIndexToSwap = rand.nextInt(array.length);
            int temp = array[randomIndexToSwap];
            array[randomIndexToSwap] = array[i];
            array[i] = temp;
        }
    }

    public static float sqrt(float value) {
        return (float) Math.sqrt(value);
    }

    public static int constrain(int value, int a, int b) {
        return value < a ? a : (Math.min(value, b));
    }

    public static float constrain(float value, float a, float b) {
        return value < a ? a : (Math.min(value, b));
    }

    public static int min(int... array) {
        int min = array[0];
        for (int value : array) {
            if (value < min)
                min = value;
        }
        return min;
    }

    public static float min(float... array) {
        float min = array[0];
        for (float v : array) {
            if (v < min)
                min = v;
        }
        return min;
    }

    public static int min(int a, int b) {
        return Math.min(a, b);
    }

    public static float min(float a, float b) {
        return Math.min(a, b);
    }

    public static int max(int... array) {
        int max = array[0];
        for (int value : array) {
            if (value > max)
                max = value;
        }
        return max;
    }

    public static float max(float... array) {
        float max = array[0];
        for (float v : array) {
            if (v > max)
                max = v;
        }
        return max;
    }

    public static int max(int a, int b) {
        return Math.max(a, b);
    }

    public static float max(float a, float b) {
        return Math.max(a, b);
    }

    public static int abs(int value) {
        return value > 0 ? value : -value;
    }

    public static float abs(float value) {
        return value > 0 ? value : -value;
    }

    public static int floor(float value) {
        return (int) Math.floor(value);
    }

    public static int ceil(float value) {
        return (int) Math.ceil(value);
    }

    public static int round(float value) {
        return Math.round(value);
    }

    public static float map(float value, float oldA, float olB, float newA, float newB) {
        return (value - oldA) / (olB - oldA) * (newB - newA) + newA;
    }

    public static float exp(float value) {
        return (float) Math.exp(value);
    }

    public static float pow(float value, float exponent) {
        return (float) Math.pow(value, exponent);
    }

    public static float lerp(float start, float stop, float amount) {
        amount = constrain(amount, 0f, 1f);
        return (stop - start) * amount + start;
    }

    public static float dist(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    public static float distSq(float x1, float y1, float x2, float y2) {
        return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
    }

    public static float dist(float x1, float y1, float z1, float x2, float y2, float z2) {
        return (float) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1) + (z2 - z1) * (z2 - z1));
    }

    public static float distSq(float x1, float y1, float z1, float x2, float y2, float z2) {
        return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1) + (z2 - z1) * (z2 - z1);
    }

    public static float noise(float x) {
        return ImprovedNoise.noise(x, 0, 0);
    }

    public static float noise(float x, float y) {
        return ImprovedNoise.noise(x, y, 0);
    }

    public static float noise(float x, float y, float z) {
        return ImprovedNoise.noise(x, y, z);
    }

    // Color functions

    public static int alpha(int color) {
        return (color >> 24) & 0xFF;
    }

    public static int red(int color) {
        return (color >> 16) & 0xFF;
    }

    public static int green(int color) {
        return (color >> 8) & 0xFF;
    }

    public static int blue(int color) {
        return (color) & 0x000000FF;
    }

    // System

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void exit(int status) {
        System.exit(status);
    }

    // Strings

    public static String[] loadStrings(String textFile) {
        File file = new File(textFile);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
            List<String> lines = new ArrayList<String>();
            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }
            sc.close();
            return lines.toArray(new String[0]);
        } catch (FileNotFoundException e) {
            error(e.getMessage());
            return new String[0];
        }
    }

    public static String join(Object[] array, String delimiter) {
        return Stream.of(array)
                .map(String::valueOf)
                .collect(Collectors.joining(delimiter));
    }

    // Print

    public static void println() {
        System.out.println();
    }

    public static void println(Object... objects) {
        if (objects.length > 10) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < objects.length; i++) {
                Object o = objects[i];
                String obj;
                if (o instanceof String) {
                    obj = '"' + String.valueOf(o) + '"';
                } else {
                    obj = String.valueOf(o);
                }
                sb.append("[" + i + "] " + obj + "\n");
            }
            System.out.print(sb);
        } else {
            System.out.println(Stream.of(objects)
                    .map(String::valueOf)
                    .collect(Collectors.joining(", ", "[", "]")));
        }
    }

    public static void println(int[] ints) {
        System.out.println(Arrays.toString(ints));
    }

    public static void println(float... floats) {
        System.out.print("(");
        for (int i = 0; i < floats.length; i++) {
            System.out.print(floats[i]);
            if (i != floats.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(")");
    }

    public static void println(Object o1, Object o2) {
        System.out.println("(" + o1 + ", " + o2 + ")");
    }

    public static void println(Object o) {
        System.out.println(o);
    }

    public static void println(List<?> list) {
        System.out.println("(");
        for (Object object : list) {
            System.out.println("   " + object);
        }
        System.out.println("]");
    }

    public static void print(Object... objects) {
        System.out.print(join(objects, " "));
    }

    public static void print(Object o) {
        System.out.print(o);
    }

    // Style related maths

    public float sin(float angle) {
        return (float) Math.sin(renderer.getStyle().getAngleMode().toRadians(angle));
    }

    public float cos(float angle) {
        return (float) Math.cos(renderer.getStyle().getAngleMode().toRadians(angle));
    }

    public float tan(float angle) {
        return (float) Math.tan(renderer.getStyle().getAngleMode().toRadians(angle));
    }

    public float atan(float angle) {
        return (float) Math.atan(renderer.getStyle().getAngleMode().toRadians(angle));
    }

    public float asin(float angle) {
        return (float) Math.asin(renderer.getStyle().getAngleMode().toRadians(angle));
    }

    public float acos(float angle) {
        return (float) Math.acos(renderer.getStyle().getAngleMode().toRadians(angle));
    }

    public float atan2(float x, float y) {
        return (float) Math.atan2(
                renderer.getStyle().getAngleMode().toRadians(x),
                renderer.getStyle().getAngleMode().toRadians(y));
    }

}