package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Main {
    public static void main(String[] args) {
        JFrame frame1 = new JFrame();
        frame1.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));

        Button button1 = new Button(0, 0, 80, 80, new ButtonImage("pictures/play.png", "pictures/play.png", "pictures/play.png"));
        button1.setBounds(Constants.WIDTH / 2 - 15, Constants.HEIGHT / 3 + 150, button1.width, button1.height);
        frame1.add(button1);

        Button button3 = new Button(0, 0, Constants.WIDTH, Constants.HEIGHT, new ButtonImage("pictures/Menu.png", "pictures/Menu.png", "pictures/Menu.png"));
        button3.setBounds(0, 0, button3.width, button3.height);
        frame1.add(button3);
        frame1.setLayout(new BorderLayout());
        frame1.pack();
        frame1.setVisible(true);


        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1.k = 1;
                frame1.remove(button1);
                //frame1.remove(button2);
                frame1.remove(button3);

                Game game = new Game();
                game.init();
                game.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));

                frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame1.setLayout(new BorderLayout());
                frame1.getContentPane().add(game, BorderLayout.CENTER);
                frame1.pack();
                frame1.setVisible(true);


                game.start();

            }
        });
    }
}

