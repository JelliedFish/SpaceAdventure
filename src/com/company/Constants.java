package com.company;

import java.awt.*;

public abstract class Constants {
     static  final int  WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
     static  final  int HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
     static final int minY = HEIGHT - 200;
     static final int SPAWN_FOR_HERO = 250;
     static final int SPAWNx_FOR_FLOOR = 0;
     static final int SPAWNy_FOR_FLOOR = Constants.minY+60;
     static final int SPAWNy_FOR_BLOCK = HEIGHT - 210;
     static final int deltaConst = 100;
     static final double ACC = 20.4;
     static final double Vy_FOR_HERO = 0.03;
     static final double MAXVy_FOR_HERO = -120;
     static final double Ay_FOR_HERO = 0.1;
     static final double FREQUENCY_FOR_BLOCK = 250;
     static final double FREQUENCY_FOR_FLOOR = 0;
     static final double FREQUENCY_FOR_ANIMATION = 15;
     static final double SPAWN_FOR_BLOCK = Constants.WIDTH+10;
     static final double Vx_FOR_TEXTURE = 65;
     static final double Ax_FOR_BLOCK = 0.03;
     static final double MAX_SPEED_FOR_BLOCK = 120;
     static final int PERIOD= 120;
     static final int DIPLAY= 0;


}

