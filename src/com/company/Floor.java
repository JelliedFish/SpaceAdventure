package com.company;

public class Floor {

        int x;
        int y;

        double Vx;

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public void setVx(int x) {
            this.x = x;
        }


        public Sprite image;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public double getVx() {
            return Vx;
        }


        public Floor(int x, int y, double Vx) {
            this.x = x;
            this.y = y;
            this.Vx = Vx;
            image = Game.getSprite("pictures/floor.png");
        }


        public void FloorMoving(double delta)
        {
            this.x = (int) (this.x - this.Vx * delta);
        }
    }
