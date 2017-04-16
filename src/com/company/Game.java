package com.company;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.net.URL;
import javax.swing.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    static Hero hero = new Hero(Constants.SPAWN_FOR_HERO, Constants.minY, Constants.Vy_FOR_HERO,Constants.Vx_FOR_TEXTURE, Constants.Ay_FOR_HERO);
    Animation img = new Animation();
    Floor floor = new Floor(Constants.SPAWNx_FOR_FLOOR,Constants.SPAWNy_FOR_FLOOR);
    public Queue <Block> blockQueue = new PriorityQueue<Block>();
    Random RG = new Random();
    private boolean running;
    private boolean gameOver;
    private Sprite backgroundImg  = getSprite("pictures/Background.png");
    static String NAME = "First Stage";
    private static int cnt ;
    private boolean upPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    static boolean MaxSpeed = false;
    private boolean CHECK_THE_RESTART = false;
    static boolean CHECK_THE_JUMP = false;
    static int Check_The_Animation = 0;
    private static int Check_The_Speed = 0;
    double Block_Speed = Constants.Vx_FOR_TEXTURE;

    public void start() {
        running = true;
        new Thread(this).start();
    }

    public void run(){
        long delta;
        long lastTime = System.currentTimeMillis();
        init();

        while (running) {
            delta = (System.currentTimeMillis() - lastTime);
            lastTime = System.currentTimeMillis();
            render();
            update((double) delta / Constants.deltaConst);
            cnt++;
            Check_The_Animation++;
            Check_The_Speed++;

            if (cnt >= Constants.FREQUENCY_FOR_BLOCK) {
                cnt = 0;
                blockQueue.add(new Block((int)Constants.SPAWN_FOR_BLOCK, Constants.SPAWNy_FOR_BLOCK, Block_Speed,RG.nextInt(3)));
            }

            if ((Check_The_Speed >= 10) && (!MaxSpeed)) {
                Block_Speed+=Constants.Ax_FOR_BLOCK;
                System.out.print(Block_Speed + " ");
                if (Block_Speed >= Constants.MAX_SPEED_FOR_BLOCK) {
                    MaxSpeed = true;
                }
                Check_The_Speed =0;
            }

            if(gameOver) {
                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch(Exception e){
                    System.out.print(e);
                }
                CHECK_THE_RESTART = true;
            }
        }
    }

    private void restart(){
        hero = new Hero(Constants.SPAWN_FOR_HERO, Constants.minY, Constants.Vy_FOR_HERO,Constants.Vx_FOR_TEXTURE,Constants.Ay_FOR_HERO);
        Floor floor = new Floor(Constants.SPAWNx_FOR_FLOOR,Constants.SPAWNy_FOR_FLOOR);
        Queue <Block> blockQueue = new PriorityQueue<Block>();
        gameOver = false;
        running = true;
        Block_Speed = Constants.Vx_FOR_TEXTURE;
    }

    public void init() {
        cnt = 0;
        addKeyListener(new KeyInputHandler());

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
        g.setColor(Color.black);
        g.fillRect(0,0, getWidth(), getHeight());
        backgroundImg.draw(g,0,0);

        floor.image.draw(g,floor.getX(),floor.getY());

        if(hero != null){
                img.HeroImages();
                hero.image.draw(g, hero.getX(), hero.getY());
        }

        for(Block block : blockQueue)
            block.image.draw(g, block.getX(), block.getY());
        g.dispose();
        bs.show();

    }

    public void update(double delta) {

        hero.jump(delta);

        if (leftPressed) {
            Hero.runningLeft(delta);
        }

        if (rightPressed) {
            Hero.runningRight(delta);
        }

        if(blockQueue != null && !blockQueue.isEmpty() && blockQueue.peek().getX() < -500 )
            blockQueue.poll();
        if(blockQueue != null && !blockQueue.isEmpty()) {
            for(Block block : blockQueue){
                if(block.overlaps(hero)) gameOver = true;
            }
        }
        for(Block block : blockQueue) block.BlockMoving(delta);

    }

    public static Sprite getSprite(String path) {
        URL url = Game.class.getResource(path);
        Image sourceImage = new ImageIcon(url).getImage();
        Sprite sprite = new Sprite(sourceImage);
        return sprite;
    }


    private class KeyInputHandler extends KeyAdapter {

        public void keyPressed(KeyEvent e) {

            if ((e.getKeyCode() == KeyEvent.VK_UP) && (hero.getY() >= Constants.minY - 1)) {
                upPressed = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }

            if (((e.getKeyCode() == KeyEvent.VK_SPACE)) && (CHECK_THE_RESTART)) {
                restart();
                CHECK_THE_RESTART = false;

            }
        }

        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (upPressed)
                    Hero.processUpPressed();
                upPressed = false;
            }

            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                leftPressed = false;
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                rightPressed = false;
            }
        }
    }
}