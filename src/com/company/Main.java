package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Main {
    public static void main(String[] args) {
        JFrame frame1=new JFrame();
        frame1.setPreferredSize(new Dimension(Constants.WIDTH,Constants.HEIGHT));

        Button button1 = new Button(0,0,80,80,new ButtonImage("pictures/play.png","pictures/play.png","pictures/play.png"));
        button1.setBounds(Constants.WIDTH/2-15,Constants.HEIGHT/3+150,button1.width,button1.height);
        frame1.add(button1);

        //Button button2= new Button(0,0,60,71,new ButtonImage("assets/rightnarrow.png","assets/rightnarrow2.png","assets/rightnarrow2.png"));
        //button2.setBounds(WIDTH/3*2,HEIGHT/5*3+60,button2.width,button2.height);
        //frame1.add(button2);

        Button button3= new Button(0,0,Constants.WIDTH,Constants.HEIGHT,new ButtonImage("pictures/Menu.png","pictures/Menu.png","pictures/Menu.png"));
        button3.setBounds(0,0,button3.width,button3.height);
        frame1.add(button3);
        frame1.setLayout(new BorderLayout());
        frame1.pack();
        frame1.setVisible(true);


        /*button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button2.k =1;
                frame1.repaint();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                button2.k =-1;
                frame1.repaint();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button2.k =0;
                frame1.repaint();
            }
        });*/

        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1.k = 1;
                frame1.remove(button1);
                //frame1.remove(button2);
                frame1.remove(button3);

                Game game=new Game();
                game.init();
                game.setPreferredSize(new Dimension(Constants.WIDTH,Constants.HEIGHT));

                Button button = new Button(0,0,62,60,new ButtonImage("pictures/music.png","pictures/music.png","pictures/music.png"));
                button.setBounds(Constants.WIDTH/2-31,0,button.width,button.height);
                frame1.add(button);
                frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame1.setLayout(new BorderLayout());
                frame1.getContentPane().add(game,BorderLayout.CENTER);
                frame1.pack();
                frame1.setVisible(true);

                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button.k = 1;
                        frame1.repaint();
                    }
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        button.k = -1;
                        frame1.repaint();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        button.k = 0;
                        frame1.repaint();

                    }
                });
                game.start();

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                button1.k = -1;
                frame1.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button1.k = 0;
                frame1.repaint();

            }
        });
        while (Game.running) {
        }
    }
}

