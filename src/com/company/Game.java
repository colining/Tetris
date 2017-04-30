package com.company;

import com.company.controller.*;
import com.company.model.*;

import com.company.view.*;
import com.company.view.Menu;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;

import static util.util.FILEPATH;

/**
 * Created by asus on 2017/4/24.
 */
public class Game {
    /*
    游戏的main方法，游戏从这里开始
     */
    public static void main(String[] args) {
        Ground ground = new Ground();
        ShapeFactory shapeFactory = new ShapeFactory();
        Testpanel testpanel = new Testpanel();
        Ground ground1 = new Ground();
        /*
        看看能不能找到保存的游戏
         */
        try {
             ground1 = (Ground) deSerialize(FILEPATH);
        } catch (IOException e) {
            ground1 = new Ground();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            ground1 = new Ground();
            e.printStackTrace();
        }
        ground = ground1;
        Controllerback controllerback = new Controllerback(ground,shapeFactory,testpanel);      //主控制器初始化
        /*
        UI部分
         */
        Start start = new Start();
        Menu menu = new Menu(controllerback);
        JPanel panel = menu.getPanel();
        start.setLayout(new BorderLayout());
        start.add(new Label("          "),BorderLayout.WEST);
        start.add(testpanel,BorderLayout.CENTER);
        start.add(panel,BorderLayout.EAST);
        start.addKeyListener(controllerback);
        //controllerback.newShape();
        start.setVisible(true);
    }

    /*
    反序列化的函数
     */
    public static Object deSerialize(String path) throws IOException,
            ClassNotFoundException {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(path));
            return ois.readObject();
        } finally {
            if (null != ois)
                ois.close();
        }
    }

}
