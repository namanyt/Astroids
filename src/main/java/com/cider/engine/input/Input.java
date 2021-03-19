package com.cider.engine.input;

import com.cider.engine.manager.Engine;

import java.awt.event.*;

public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

    private final Engine engine;

    private final int NUM_KEYS = 256;
    private final boolean[] keys = new boolean[NUM_KEYS];
    private final boolean[] keysLast = new boolean[NUM_KEYS];

    private final int NUM_BUTTONS = 5;
    private final boolean[] buttons = new boolean[NUM_BUTTONS];
    private final boolean[] buttonsLast = new boolean[NUM_BUTTONS];

    private int mouseX, mouseY;
    private int scroll;

    public Input(Engine engine) {
        this.engine = engine;

        mouseX = 0;
        mouseY = 0;
        scroll = 0;

        engine.getWindow().getCanvas().addKeyListener(this);
        engine.getWindow().getCanvas().addMouseListener(this);
        engine.getWindow().getCanvas().addMouseMotionListener(this);
        engine.getWindow().getCanvas().addMouseWheelListener(this);
    }

    public void update() {
        for (int i = 0; i < NUM_KEYS; i++) {
            keysLast[i] = keys[i];
        }

        for (int i = 0; i < NUM_BUTTONS; i++) {
            buttonsLast[i] = buttons[i];
        }
    }

    public boolean isKey(int keyCode) {
        return keys[keyCode];
    }

    public boolean isKeyUp(int keyCode) {
        return !keys[keyCode] && keysLast[keyCode];
    }

    public boolean isKeyDown(int keyCode) {
        return keys[keyCode] && !keysLast[keyCode];
    }

    public boolean isButton(int buttonCode) {
        return buttons[buttonCode];
    }

    public boolean isButtonUp(int buttonCode) {
        return !buttons[buttonCode] && buttonsLast[buttonCode];
    }

    public boolean isButtonDown(int keyCode) {
        return buttons[keyCode] && !buttonsLast[keyCode];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        buttons[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        buttons[e.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = (int)(e.getX() / engine.getScreenScale());
        mouseY = (int)(e.getY() / engine.getScreenScale());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = (int)(e.getX() / engine.getScreenScale());
        mouseY = (int)(e.getY() / engine.getScreenScale());
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        scroll = e.getWheelRotation();
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public int getScroll() {
        return scroll;
    }
}
