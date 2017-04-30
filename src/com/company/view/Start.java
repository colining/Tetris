package com.company.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by asus on 2017/4/22.
 */
public class Start extends JFrame{
    /**
     * 主要Jframe
     */

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1032;

    public Start() {
        this.setSize(WIDTH, HEIGHT);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();  //获取计算机屏幕大小
        this.setLocation((dimension.width-WIDTH)/2,(dimension.height-HEIGHT)/2);    //居中
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //this.setVisible(true);
    }

    public static void main(String[] args) {
        Start start = new Start();
    }
}
