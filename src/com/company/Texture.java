package com.company;

import java.util.Queue;

public class Texture implements Comparable<Texture>{
    int x;
    int y;
    int right_corner_coord;

    double Vx;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void setVx(int x) {
        this.x = x;
    }


    public static Sprite image;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getVx() {return Vx;
    }


    public Texture(int x, int y, double Vx) {
        this.x = x;
        this.y = y;
        this.Vx = Vx;
        image = Game.getSprite("pictures/platform.png");
    }

    Texture(int right_corner_coord) {
        this.right_corner_coord = right_corner_coord;
    }

    public void calculateMoving(double delta)
    {
        this.x = (int) (this.x - this.Vx*delta);
    }

    public static int getImageWidth() {
        return image.getWidth();
    }


    @Override
    public int compareTo(Texture o) {
        if(o.getX() < this.getX()) return 1;
        else return -1;
    }
}
