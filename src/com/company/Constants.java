package com.company;

import java.awt.*;

public class Constants {
    public static  final int  WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static  final  int HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    public static final int minY = HEIGHT - 200;
    public static final int SPAWN_FOR_HERO = 100;
    public static final int SPAWNx_FOR_FLOOR = 300;
    public static final int SPAWNy_FOR_FLOOR = Constants.minY+90;
    public static final int deltaConst = 100;
    public static final double ACC = 20.4;
    public static final double Vy_FOR_HERO = 0.03;
    public static final double MAXVy_FOR_HERO = -120;
    public static final double Ay_FOR_HERO = 0.1;
    public static final double FREQUENCY_FOR_BLOCK = 500;
    public static final double FREQUENCY_FOR_FLOOR = 10;
    public static final double FREQUENCY_FOR_ANIMATION = 15;
    public static final double SPAWN_FOR_BLOCK = Constants.WIDTH+10;
    public static final double Vx_FOR_TEXTURE = 65;

}

