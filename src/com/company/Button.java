package com.company;

import com.company.pictures.ButtonImage;

import javax.swing.*;
import java.awt.*;

    /**
     * Created by nurgalievar.18 on 17.04.2017.
     */
    public class Button extends JPanel{
        int x;
        int y;
        int k=0;//если при нажатии на кнопку нужно увеличивать какой-то счетчик
        int width;
        int height;
        ButtonImage buttonImage;
        Rectangle rect;

        public Button(int x,int y,int width,int height,ButtonImage buttonImage){
            this.x=x;
            this.y=y;
            this.width=width;
            this.height=height;
            this.rect = new Rectangle(x,y,width,height);
            this.buttonImage=buttonImage;
        }

        public void paint(Graphics g){
            if (k==0){g.drawImage(buttonImage.getImage(),x,y,width,height,null);}
            if (k==1){g.drawImage(buttonImage.getImageClicked(),x,y,width,height,null);}
            if (k==-1){g.drawImage(buttonImage.getImageEntered(),x,y,width,height,null);}
        }


    }

