package com.company;

        import java.awt.Graphics;
        import java.awt.Image;
        import java.awt.image.BufferedImage;

public class Sprite {
    private Image image;

    public Sprite(Image image) {

        this.image = image;
    }

    public int getWidth() {

        return image.getWidth(null);
    }

    public int getHeight()
    {
        return image.getHeight(null);
    }

    public void draw(Graphics g, int x, int y)
    {
        g.drawImage(image,x,y,null);
    }
    //спрайт и кола...
}