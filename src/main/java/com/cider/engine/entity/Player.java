package com.cider.engine.entity;

import com.cider.engine.manager.Engine;

import java.awt.event.KeyEvent;

public class Player {
    private Engine engine;
    private int playerX, playerY;

    public Player(Engine engine) {
        this.engine = engine;
    }

    public void move() {
        if (engine.getInput().isKeyDown(KeyEvent.VK_SPACE)) {
            System.out.println("SPACE");
        }
    }
}
