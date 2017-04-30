package com.company.view;

import com.company.model.*;
import com.company.model.Shape;

import javax.swing.*;
import java.awt.*;

import static util.util.SIDEWEITH;

/**
 * Created by asus on 2017/4/22.
 */
public class Testpanel extends JPanel{
    //游戏的画面
    static int height,width,top;
    Shape shape;
    Ground ground;


    public Testpanel() {
        int width = 600;
        int height = 1000;
        this.setSize(width,height);
        this.setVisible(true);
        getvar();
    }

    void getvar(){
        /**
         * 初始化一些数据
         */
        Insets insets = this.getInsets();
        height = this.getHeight();
        width = this.getWidth();
        top = 32;
        System.out.println(insets.toString());
    }

    public void display(Ground ground , Shape shape)
    {
        /**
         * 重画
         */
        this.shape  = shape;
        this.ground = ground;
        this.repaint();

    }
    public void update(Graphics g){
        /*
        防止画面闪烁什么的
         */
            paint(g);
    }
    public void paint(Graphics g) {
        /*
        绘制游戏界面
         */
        super.paint(g);
        int sidelengh = SIDEWEITH;                                         //边长
        int x1 = sidelengh, y1 =0;
        for (int i = 0; i < 20; i++) {
             //g.drawLine(x1, y1, width - x1, y1);
            y1 += sidelengh;
        }
        y1 =0;
        for (int i = 0; i < 10; i++)
        {
            //g.drawLine(x1,y1,x1,height);
            x1+=sidelengh;
        }

        g.setColor(new Color(38,50,56));
        g.fill3DRect(x1,0,sidelengh,height,true);
        g.fill3DRect(0,0,sidelengh,height,true);
        if (shape!=null&&ground!=null) {
            shape.draw(g);
            ground.draw(g);
        }

    }


}
