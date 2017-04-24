package com.company;

import com.company.pictures.ButtonImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Main {
    public static void main(String[] args) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int WIDTH = dim.width;
        int HEIGHT = dim.height;
        JFrame frame1=new JFrame();
        frame1.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        Button button1 = new Button(0,0,457,210,new ButtonImage("assets/play1.png","assets/play2.png","assets/play2.png"));
        button1.setBounds(WIDTH/2-229,HEIGHT/3-50,button1.width,button1.height);
        frame1.add(button1);
        //Button button2= new Button(0,0,60,71,new ButtonImage("assets/rightnarrow.png","assets/rightnarrow2.png","assets/rightnarrow2.png"));
        //button2.setBounds(WIDTH/3*2,HEIGHT/5*3+60,button2.width,button2.height);
        //frame1.add(button2);
        Button button3= new Button(0,0,WIDTH,HEIGHT,new ButtonImage("assets/startframe.png","assets/startframe.png","assets/startframe.png"));
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
                game.setPreferredSize(new Dimension(WIDTH,HEIGHT));

                Button button = new Button(0,0,62,60,new ButtonImage("assets/restart1.png","assets/restart2.png","assets/restart2.png"));
                button.setBounds(WIDTH/2-31,0,button.width,button.height);
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
                        if(game.isPlayer1win()||game.isPlayer2win()){game.restart();}

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

    }
}

