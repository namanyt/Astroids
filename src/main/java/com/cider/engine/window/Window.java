package com.cider.engine.window;

import com.cider.engine.manager.Engine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Window {

    private JFrame jFrame;
    private BufferedImage bufferedImage;
    private Canvas canvas;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;

    public Window(Engine engine) {
        bufferedImage = new BufferedImage(engine.getScreenWidth(), engine.getScreenHeight(), BufferedImage.TYPE_INT_RGB);
        Dimension ScreenDimension = new Dimension((int) (engine.getScreenWidth() * engine.getScreenScale()), (int) (engine.getScreenHeight() * engine.getScreenScale()));

        // Canvas things
        canvas = new Canvas();
        canvas.setPreferredSize(ScreenDimension);
        canvas.setMaximumSize(ScreenDimension);
        canvas.setMinimumSize(ScreenDimension);
        canvas.requestFocus();

        // JFrame stuff
        jFrame = new JFrame(engine.getWindowTitle());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(new BorderLayout());
        jFrame.add(canvas, BorderLayout.CENTER);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setVisible(true);

        // Graphics things
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        graphics = bufferStrategy.getDrawGraphics();
    }

    public void update() {
        graphics.drawImage(bufferedImage, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
        bufferStrategy.show();
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public JFrame getjFrame() {
        return jFrame;
    }
}
