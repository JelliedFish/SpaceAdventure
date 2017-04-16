package com.company;

public class Animation {

    public void HeroImages() {
        if ((Game.Check_The_Animation >= Constants.FREQUENCY_FOR_ANIMATION) && (Game.CHECK_THE_JUMP == false))
            Game.hero.image = Game.getSprite("pictures/sprites_8.run1.png");

        if ((Game.Check_The_Animation >= Constants.FREQUENCY_FOR_ANIMATION*2) && (Game.CHECK_THE_JUMP == false))
            Game.hero.image = Game.getSprite("pictures/sprites_8.run2.png");

        if ((Game.Check_The_Animation >= Constants.FREQUENCY_FOR_ANIMATION*3) && (Game.CHECK_THE_JUMP == false))
            Game.hero.image = Game.getSprite("pictures/sprites_8.run3.png");

        if ((Game.Check_The_Animation >= Constants.FREQUENCY_FOR_ANIMATION*4) && (Game.CHECK_THE_JUMP == false))
            Game.hero.image = Game.getSprite("pictures/sprites_8.run4.png");

        if ((Game.Check_The_Animation >= Constants.FREQUENCY_FOR_ANIMATION*5) && (Game.CHECK_THE_JUMP == false))
            Game.hero.image = Game.getSprite("pictures/sprites_8.run5.png");

        if ((Game.Check_The_Animation >= Constants.FREQUENCY_FOR_ANIMATION*6)&& (Game.CHECK_THE_JUMP == false)) {
            Game.hero.image = Game.getSprite("pictures/sprites_8.run6.png");
            Game.Check_The_Animation = 0;
        }

    }
}
