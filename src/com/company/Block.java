package com.company;

public class Block implements Comparable<Block> {

    int x;
    int y;
    Sprite image;


    double Vx;

    public void setX(int x) {
        this.x = x;
    }
 
    public void setY(int y)
    {
        this.y = y;
    }

    public void setVx(int Vx) {

        this.Vx = Vx;
    }

    public int getX() {
        return x;
    }

    public int getY() {

        return y;
    }

    public double getVx() {
        return Vx;
    }


    public Block(int x, int y, double Vx) {
        this.x = x;
        this.y = y;
        this.Vx = Vx;
        this.image = Game.getSprite("pictures/The Block1.png");
        }


    public void BlockMoving(double delta)
    {
        this.x = (int) (this.x - this.Vx*delta);
    }

    @Override
    public int compareTo(Block o) {
        if(o.getX() < this.getX()) return 1;
        else return -1;// здесь все понятно
    }

    public boolean overlaps(Hero hero){
        boolean b;
         if (this.x < hero.getX())
        b =(this.x+50 < hero.getX() + hero.image.getWidth()) && (this.x + this.image.getWidth())  > hero.getX() &&
                this.y +35 < (hero.getY() + hero.image.getHeight()) && (this.y + this.image.getHeight()-20 > hero.getY()+hero.image.getHeight());
        else
            b =  (this.x+27 < hero.getX() + hero.image.getWidth()) && (this.x + this.image.getWidth())  > hero.getX() &&
                    this.y+35 < (hero.getY() + hero.image.getHeight()) && (this.y + this.image.getHeight()-20 > hero.getY());
         return b;

    }//процесс соударения
}
