package com.company;

import java.awt.*;

public abstract class Constants {
     static  final int  WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
     static  final  int HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
     static final int minY = HEIGHT - 200;
     static final int SPAWN_FOR_HERO = 250;
     static final int SPAWNx_FOR_FLOOR = 0;
     static final int SPAWNy_FOR_FLOOR = Constants.minY+93;
     static final int SPAWNy_FOR_BLOCK = HEIGHT - 210;
     static final int deltaConst = 100;
     static final double ACC = -13;
     static final double ACC1 = 7;
     static final double Vy_FOR_HERO = 100;
     static final double MAXVy_FOR_HERO = -120;
     static final double Ay_FOR_HERO = 0.1;
     static final double FREQUENCY_FOR_BLOCK = 300;
     static final double MAX_FREQUENCY_FOR_BLOCK = 100;
     static final int FREQUENCY_FOR_SPEED = 1000;
     static final double FREQUENCY_FOR_FLOOR = 0;
     static final double SPAWN_FOR_BLOCK = Constants.WIDTH+10;
     static final double Vx_FOR_TEXTURE = 65;
     static final double Ax_FOR_BLOCK = 1;
     static final double MAX_SPEED_FOR_BLOCK = 250;
     static final int PERIOD=180;
     static final int PERIOD_FOR_BLOCK= 180;
     static final int PERIOD_FOR_FLOOR1= 470;
     static final int PERIOD_FOR_FLOOR2= 570;
     static final int PERIOD_FOR_BOOM= 220;
     static final int DIPLAY= 0;
     static final int IMIN = 10;
     static final int IMAX = Constants.minY;
     static final int w = 10;
     static final int FONT_SIZE = 19;
     // класс констант
}

