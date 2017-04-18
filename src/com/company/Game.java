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
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class Game extends Canvas implements Runnable { //Класс, в котором происходят все игровые манипуляции:

    private static final long serialVersionUID = 1L;
    static Hero hero = new Hero(Constants.SPAWN_FOR_HERO, Constants.minY, Constants.Vy_FOR_HERO,Constants.Vx_FOR_TEXTURE, Constants.Ay_FOR_HERO);
    static  double Block_Speed = Constants.Vx_FOR_TEXTURE;
    Floor floor = new Floor(Constants.SPAWNx_FOR_FLOOR,Constants.SPAWNy_FOR_FLOOR,Block_Speed);
    public Queue <Block> blockQueue = new PriorityQueue<Block>();// Создаем очередь для блоков (платформы, которые перемещаются)
    Random RG = new Random();
    private boolean running; //переменная для главного игрового цикла (всегда true)
    private boolean gameOver; // флаг для остановк игры после соприкосновения с блоком
    private Sprite backgroundImg  = getSprite("pictures/Background.png");// background
    static String NAME = "First Stage";
    int pos;//счетчик для регулировки индексов картинки sprites_8.run + pos + ...
    private boolean upPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    static boolean MaxSpeed = false;
    private boolean CHECK_THE_RESTART = false; //переменная для проверки на использование респауна в игровое время
    static boolean CHECK_THE_JUMP = false;//проверка на прыжок
    static double FREQUENCY_FOR_BLOCK = Constants.FREQUENCY_FOR_BLOCK;
    int Speed_cnt;

    public void start() { // начало игры
        running = true;
        new Thread(this).start();
    }

    public void run(){
        long delta;
        long lastTime = System.currentTimeMillis();
        init();//инициализируем

        while (running) {
            Speed_cnt++;
            delta = (System.currentTimeMillis() - lastTime);
            lastTime = System.currentTimeMillis(); // реализация игрового времени
            render();//рисуем
            update((double) delta / Constants.deltaConst);//всякие физические процессы

            if (Speed_cnt >= FREQUENCY_FOR_BLOCK) {
                blockQueue.add(new Block((int)Constants.SPAWN_FOR_BLOCK, Constants.SPAWNy_FOR_BLOCK, Game.Block_Speed,RG.nextInt(2)));
                Speed_cnt = 0;
            }
            if (!MaxSpeed)
            FREQUENCY_FOR_BLOCK-=0.05;
            if( FREQUENCY_FOR_BLOCK <= Constants.MAX_FREQUENCY_FOR_BLOCK)
                MaxSpeed = true;
            if(gameOver) { // что будет, если попал в блок
                try{
                    TimeUnit.SECONDS.sleep(2);//сон
                }catch(Exception e){
                    System.out.print(e);
                }
                blockQueue.clear();//подчищаем за оставшимися
                CHECK_THE_RESTART = true;

            }
        }
    }

    private void restart(){ //что будет, если попал в блок, но обращаемся сюда после нажатия пробела
        Block_Speed = Constants.Vx_FOR_TEXTURE;
        hero = new Hero(Constants.SPAWN_FOR_HERO, Constants.minY, Constants.Vy_FOR_HERO,Constants.Vx_FOR_TEXTURE,Constants.Ay_FOR_HERO);
        Floor floor = new Floor(Constants.SPAWNx_FOR_FLOOR,Constants.SPAWNy_FOR_FLOOR,Game.Block_Speed);
        Queue <Block> blockQueue = new PriorityQueue<Block>();
        MaxSpeed = false;
    }

    public void init() {

        addKeyListener(new KeyInputHandler());
        java.util.Timer timer_for_hero = new java.util.Timer();
        java.util.Timer timer_for_speed = new java.util.Timer();
        java.util.Timer timer_for_floor = new java.util.Timer();

        timer_for_hero.schedule(new TimerTask() {
            @Override
            public void run() {
                if (pos++ > 5) {
                    pos = 1;
                }
                if (!Game.CHECK_THE_JUMP) {
                    Game.hero.image = Game.getSprite("pictures/sprites_8.run" + pos + ".png");
                }
            }
        }, Constants.DIPLAY, Constants.PERIOD);

        timer_for_speed.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!MaxSpeed) {
                    Game.Block_Speed += Constants.Ax_FOR_BLOCK;
                    if (Game.Block_Speed >= Constants.MAX_SPEED_FOR_BLOCK) {
                        MaxSpeed = true;
                    }
                }
            }
        }, Constants.DIPLAY, Constants.FREQUENCY_FOR_SPEED);

        timer_for_floor.schedule(new TimerTask() {
            @Override
            public void run() {
                    floor.setX(Constants.SPAWNx_FOR_FLOOR);
                }
        }, Constants.DIPLAY, Constants.PERIOD_FOR_FLOOR);

    }
// здесь, вроде, все понятно
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
                hero.image.draw(g, hero.getX(), hero.getY());
        }

        for(Block block : blockQueue)
            block.image.draw(g, block.getX(), block.getY());
        g.dispose();
        bs.show();
//рисуем
    }

    public void update(double delta) {

        hero.jump(delta);
        floor.Floor_Moving(delta);
        System.out.print(floor.getVx()+" ");

        if (leftPressed) {
            Hero.runningLeft(delta); //бег для героя, смотри в классе hero
        }

        if (rightPressed) {
            Hero.runningRight(delta);
        }

        if(blockQueue != null && !blockQueue.isEmpty() && blockQueue.peek().getX() < -500 )
                blockQueue.poll();
                if(blockQueue != null && !blockQueue.isEmpty()) {
                    for(Block block : blockQueue){
                if(block.overlaps(hero)) gameOver = true; //чистим очередь
            }
        }
        for(Block block : blockQueue) block.BlockMoving(delta); //движение блоков, смотри в классе block

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
                gameOver = false;
                CHECK_THE_RESTART = false;
                restart();
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