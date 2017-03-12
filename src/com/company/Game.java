package com.company;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.net.URL;
import javax.swing.*;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;
    private int minY = 500;
    Hero hero = new Hero(0,0,0.03,0.1,0.1);
    Texture texture = new Texture();

    private boolean running;

    public static int WIDTH = 800;
    public static int HEIGHT = 600;
    public static String NAME = "First Stage";
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean upPressed = false;

    public void start() {
        running = true;
        new Thread(this).start();
    }

    public void run() {
        long delta;
        long lastTime = System.currentTimeMillis();
        init();

        while(running) {
            delta = System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();
            render();
            update(delta);
        }
    }

    public void init() {
        addKeyListener(new KeyInputHandler());
        hero.image = getSprite("pictures/man.png");
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(2);
            requestFocus();
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        hero.image.draw(g, hero.getX(), hero.getY());
        g.dispose();
        bs.show();
    }

    public void update(long delta) {


        if (leftPressed == true) {
            hero.setX((int) (hero.getX() - (hero.getAx() * delta * delta) / 2));
        }

        if (rightPressed == true) {
            hero.setX((int) (hero.getX() + (hero.getAx() * delta * delta) / 2));
        }

        if ((upPressed == true) && (hero.getY() > 200)){
            hero.setVy(0.2);
            hero.setY( (int) (hero.getY() - hero.getVy() * delta + (hero.getAy() * delta * delta) / 2));
            hero.setY(hero.getY()-1);
        }

        if (upPressed == false) {
            if (hero.getY() <= minY) {
            hero.setY((int) (hero.getY() - hero.getVy() * delta + (hero.getAy() * delta * delta) / 2));
        }
    }
        if (hero.getY() < minY -200) {
            hero.setVy(0);
        }

    }

    public Sprite getSprite(String path) {
        URL url = Game.class.getResource(path);
        Image sourceImage = new ImageIcon(url).getImage();


        Sprite sprite = new Sprite(sourceImage);

        return sprite;
    }


    private class KeyInputHandler extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
            if ((e.getKeyCode() == KeyEvent.VK_UP) && (hero.y >= minY -1)) {
                upPressed = true;
            }
        }

        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                leftPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                rightPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                upPressed = false;
            }
        }
    }
}


