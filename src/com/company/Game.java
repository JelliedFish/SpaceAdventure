package com.company;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.net.URL;
import javax.swing.*;
import java.util.PriorityQueue;
import java.util.Queue;


public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;
    static int minY = 500;
    Hero hero = new Hero(500, 200, 0.03, 0.1);
    public Queue <Texture> textureQueue = new PriorityQueue<Texture>();
    private boolean running;

    private static final int deltaConst = 100;
    public static int WIDTH = 1000;
    public static int HEIGHT = 800;
    public static String NAME = "First Stage";
    boolean upPressed = false;

    public void start() {
        running = true;
        new Thread(this).start();
    }

    public void run() {
        long delta;
        long deltaTexture;
        long lastTime = System.currentTimeMillis();
        long cnt = 0;
        init();

        while (running) {
            delta = (System.currentTimeMillis() - lastTime);
            lastTime = System.currentTimeMillis();
            render();
            update((double) delta / deltaConst);
            cnt++;
            System.out.println(cnt);
            if (cnt >= 2500) {
                cnt = 0;
                textureQueue.add(new Texture(1000, 200, 0.1));
            }
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
        for(Texture texture : textureQueue)
           texture.image.draw(g,texture.getX(), texture.getY());
        g.dispose();
        bs.show();
    }
    //
    public void update(double delta) {
        hero.calculatePhysics(delta);
        /*if(textureQueue != null && !textureQueue.isEmpty() && textureQueue.peek().getX() < -200 )
            textureQueue.poll();*/
        for(Texture texture : textureQueue) texture.calculateMoving(delta);
    }

    public static Sprite getSprite(String path) {
        URL url = Game.class.getResource(path);
        Image sourceImage = new ImageIcon(url).getImage();


        Sprite sprite = new Sprite(sourceImage);

        return sprite;
    }


    private class KeyInputHandler extends KeyAdapter {
        public void keyPressed(KeyEvent e) {

            if ((e.getKeyCode() == KeyEvent.VK_UP) && (hero.y >= minY - 1)) {
                upPressed = true;
            }
        }

        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (upPressed)
                    processUpPressed();
                upPressed = false;
            }
        }
    }

    private void processUpPressed() {
        hero.setVy(-100);
    }
}