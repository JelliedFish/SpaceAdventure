package com.company;

        import java.awt.Graphics;
        import java.awt.Image;
        import java.awt.image.BufferedImage;

public class Sprite {
    private Image image;

    public Sprite(Image image) {
        this.image = image;
    }

    void draw_with_size (Graphics g,int x,int y, int x1, int y1){
        g.drawImage(image,x,y,x1,y1,null);
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