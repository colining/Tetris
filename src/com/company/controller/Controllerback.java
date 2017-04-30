package com.company.controller;


import com.company.model.Ground;
import com.company.model.Shape;
import com.company.model.ShapeFactory;
import com.company.myenum.ShapeAction;

import com.company.view.Testpanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by asus on 2017/4/24.
 */
public class Controllerback extends KeyAdapter implements ShapeListener{
    /*
    游戏控制器，需要游戏相关的实体，还需要游戏UI
     */
    Ground ground;
    ShapeFactory shapeFactory;
    Shape shape ;
    Testpanel test;

    public Ground getGround() {
        return ground;
    }
    public ShapeFactory getShapeFactory() {
        return shapeFactory;
    }


    public Controllerback(Ground ground, ShapeFactory shapeFactory, Testpanel test) {
        this.ground = ground;
        this.shapeFactory = shapeFactory;
        this.test = test;
    }


    public void newShape()
    {
        /**
         * 新的形状
         */
        shape = shapeFactory.getShape(this);
    }
    public void newGame()
    {
        /**
         * 新的游戏，清除所有障碍物
         */
        ground.clearFround();
        newShape();
    }


    public void serialize(Object obj, String path) throws IOException {
        /*
        序列化障碍物
         */
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(obj);
        } finally {
            if (null != oos)
                oos.close();
        }
    }

    @Override
    public void shapeMoveDown(Shape shape) {
        /*
        形状下移
         */
        test.display(this.ground,shape);
    }

    @Override
    public synchronized boolean shapeIsMoveDownable(Shape shape) {
        /*
        判断是否可行
         */
        if (this.shape!=shape)
        {
            return false;
        }
        if (ground.isMoveabel(shape, ShapeAction.DOWN)) {
            return true;
        }
        ground.add(this.shape);
        if (!ground.isFull())
            this.shape = shapeFactory.getShape(this);
        return false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        /*
        键盘监听
         */
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                if (ground.isMoveabel(shape, ShapeAction.ROTATE))
                    shape.rotate();
                break;
            case KeyEvent.VK_DOWN:
                if (shapeIsMoveDownable(shape))
                    shape.down();
                break;
            case KeyEvent.VK_LEFT:
                if (ground.isMoveabel(shape,ShapeAction.LEFT))
                    shape.moveleft();
                break;
            case KeyEvent.VK_RIGHT:
                if (ground.isMoveabel(shape,ShapeAction.RIGHT))
                    shape.moveright();
                break;
        }
        test.display(ground,shape);
    }

}
