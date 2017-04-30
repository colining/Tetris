package com.company.view;

import com.company.controller.Controllerback;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static util.util.FILEPATH;

/**
 * Created by asus on 2017/4/24.
 */
public class Menu implements ActionListener{

    Controllerback controllerback = null;
    private JPanel panel;
    private JButton newgameButton;
    private JButton returnButton;
    private JLabel soccer;
    private JLabel num;
    private JButton continueButton;
    private JButton savebutton;
    private JButton stopButton;

    public Menu(Controllerback controllerback) {
        /*
        所有按钮都要加上主控制器，否则焦点移到按钮上，键盘就失灵了，
        暂停键不需要；
         */
        this.controllerback = controllerback;
        panel.addKeyListener(controllerback);
        newgameButton.addKeyListener(controllerback);
        returnButton.addKeyListener(controllerback);
        newgameButton.addActionListener(this);
        continueButton.addKeyListener(controllerback);
        continueButton.addActionListener(this);
        savebutton.addActionListener(this);
        savebutton.addKeyListener(controllerback);
        //stopButton.addKeyListener(controllerback);
        stopButton.addActionListener(this);
    }

    public JPanel getPanel() {
        return panel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        /*
        点击事件的处理
         */

        if (e.getSource() ==newgameButton)          //新的游戏
        {
            controllerback.newGame();
        }

        if (e.getSource() == savebutton)            //保存游戏，也就是序列化
        {
            System.out.println("LLLLLLLLLLLLLLLLLLLLL");
            try {
                controllerback.getGround().toString();
                controllerback.serialize(controllerback.getGround(),FILEPATH);

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (e.getSource() == continueButton)        //继续游戏，线程重新启动
        {
            if (controllerback.getShapeFactory().getCurrentShape()==null)
            controllerback.newShape();
            else
            {
                controllerback.getShapeFactory().continueThread();
            }
        }
        if (e.getSource() == stopButton)                //暂停游戏，线程挂起
        {
            controllerback.getShapeFactory().stopDown();
        }
    }
}
