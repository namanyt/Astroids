package com.cider.engine.manager;

import com.cider.engine.entity.Player;
import com.cider.engine.graphics.Renderer;
import com.cider.engine.input.Input;
import com.cider.engine.window.Window;

import java.awt.event.KeyEvent;

public class Engine implements Runnable{

    private Thread thread;
    private Window window;
    private Renderer renderer;
    private Input input;
    private Player player;

    // Game private stuff
    private boolean isRunning = false;
    private final double UPDATE_CAP = 1.0 / 120.0;

    // Window stuff
    private int screenWidth = 320, ScreenHeight = 240;
    private float screenScale = 3f;
    private String WindowTitle = "Asteroids v1.0";

    public Engine() {

    }

    public void start() {
        window = new Window(this);
        renderer = new Renderer(this);
        input = new Input(this);

        player = new Player(this);

        thread = new Thread(this);
        thread.run();
    }

    public void stop() {
        System.exit(0);
    }

    public void run() {
        isRunning = true;

        boolean render;

        // Time the loop
        double firstTime = 0;
        double lastTime = System.nanoTime() / 1000000000.0;
        double passedTime = 0;
        double unprocessedTime = 0;

        // FPS things
        double frameTime = 0;
        double frames = 0;
        double FPS = 0;

        while(isRunning) {
            // not allow the game to render at start
            render = false;

            // More Timing loop -_-
            firstTime = System.nanoTime() / 1000000000.0;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;
            unprocessedTime += passedTime;

            frameTime += passedTime;

            while (unprocessedTime >= UPDATE_CAP) {
                unprocessedTime -= UPDATE_CAP;

                // TODO Update Game >:(
                player.move();

                // To close the game
                if (input.isKeyDown(KeyEvent.VK_ESCAPE)) {
                    stop();
                }

                input.update();

                if (frameTime >= 1) {
                    frameTime = 0;
                    FPS = frames;
                    frames = 0;
                }

                // to allow the game to render
                render = true;
            }

            if (render) {
                renderer.clear();
                renderer.drawText("FPS: " + FPS, 0, 0, 0xffffff00);
                window.update();
                frames++;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        dispose();
    }

    private void dispose() {

    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return ScreenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        ScreenHeight = screenHeight;
    }

    public float getScreenScale() {
        return screenScale;
    }

    public void setScreenScale(float screenScale) {
        this.screenScale = screenScale;
    }

    public String getWindowTitle() {
        return WindowTitle;
    }

    public void setWindowTitle(String windowTitle) {
        WindowTitle = windowTitle;
    }

    public Window getWindow() {
        return window;
    }

    public Input getInput() {
        return input;
    }
}
