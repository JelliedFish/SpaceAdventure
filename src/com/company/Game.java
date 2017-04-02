package com.company;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    public static Hero hero = new Hero(Constants.SPAWN_FOR_HERO, Constants.minY, Constants.Vy_FOR_HERO, Constants.Ay_FOR_HERO);
    Animation img = new Animation();
    Floor floor = new Floor(Constants.SPAWNx_FOR_FLOOR,Constants.SPAWNy_FOR_FLOOR,Constants.Vx_FOR_TEXTURE);
    public Queue <Block> blockQueue = new PriorityQueue<Block>();
    Random RG = new Random();
    private boolean running;
    private boolean gameOver;
    private Sprite backgroundImg  = getSprite("pictures/Background.png");
    public static String NAME = "First Stage";
    private static int cnt ;
    boolean upPressed = false;
    boolean CHECK_THE_RESTART = false;
    public static boolean CHECK_THE_JUMP = false;
    public  static int cnt1 = 0;


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

            if (cnt >= Constants.FREQUENCY_FOR_BLOCK) {
                cnt = 0;
                blockQueue.add(new Block((int)Constants.SPAWN_FOR_BLOCK, Constants.SPAWNy_FOR_BLOCK, Constants.Vx_FOR_TEXTURE,RG.nextInt(3)));
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
        hero = new Hero(Constants.SPAWN_FOR_HERO, Constants.minY, Constants.Vy_FOR_HERO, Constants.Ay_FOR_HERO);
        Floor floor = new Floor(Constants.SPAWNx_FOR_FLOOR,Constants.SPAWNy_FOR_FLOOR,Constants.Vx_FOR_TEXTURE);
        Queue <Block> blockQueue = new PriorityQueue<Block>();
        gameOver = false;
        running = true;
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

        if (floor.getX() <= Constants.FREQUENCY_FOR_FLOOR) {
            floor = new Floor(Constants.SPAWNx_FOR_FLOOR, Constants.SPAWNy_FOR_FLOOR,Constants.Vx_FOR_TEXTURE);
        }

        floor.image.draw(g,floor.getX(),floor.getY());

        cnt1++;
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
        floor.FloorMoving(delta);

        if(blockQueue != null && !blockQueue.isEmpty() && blockQueue.peek().getX() < -200 )
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

            if (((e.getKeyCode() == KeyEvent.VK_SPACE)) && (CHECK_THE_RESTART == true)) {
                restart();
                CHECK_THE_RESTART = false;

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

    private void processUpPressed()
    {
        hero.setVy(Constants.MAXVy_FOR_HERO);
    }
}