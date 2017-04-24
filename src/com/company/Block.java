package com.company;

public class Block implements Comparable<Block> {
    Sprite [] Sarr = new Sprite[] {Game.getSprite("pictures/Block1.png"),
            Game.getSprite("pictures/Block3.png")

    };
    int x;
    int y;

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


     Sprite image;

    public int getX() {
        return x;
    }

    public int getY() {

        return y;
    }

    public double getVx() {
        return Vx;
    }


    public Block(int x, int y, double Vx, int height) {
        this.x = x;
        this.y = y;
        this.Vx = Vx;
        this.image = Sarr[height];
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
        b =(this.x < hero.getX() + hero.image.getWidth()-15) && (this.x + this.image.getWidth()-15)  > hero.getX() &&
                this.y < (hero.getY() + hero.image.getHeight()-10) && (this.y + this.image.getHeight() > hero.getY()-10);
        else
            b =  (this.x < hero.getX() + hero.image.getWidth()-15) && (this.x + this.image.getWidth()-15)  > hero.getX() &&
                    this.y < (hero.getY() + hero.image.getHeight()+10) && (this.y + this.image.getHeight() > hero.getY()+10);
         return b;

    }//процесс соударения
}
