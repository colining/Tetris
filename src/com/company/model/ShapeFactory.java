package com.company.model;

import com.company.controller.ShapeListener;
import util.util;

/**
 * Created by asus on 2017/4/17.
 */
public class ShapeFactory {
    /**
     * 形状工厂，用来创建不同的形状，
     * @return
     */
    public Shape getCurrentShape() {
        return currentShape;
    }

    Shape currentShape = null;
    public ShapeFactory() {
    }

    public Shape getShape(ShapeListener shapeListener)
    {
        Shape shape = new Shape();
        int shapekind = (int)(Math.random()*10000%7);
        //shapekind = 0;
        shape.setBody(util.shape[shapekind]);
        shape.setShapekind(shapekind);
        int state = (int)(Math.random()*1000%4);
        shape.setState(state);
        shape.addListenser(shapeListener);
        currentShape = shape;
        return shape;
    }
    /*
    暂停下降
     */
    public void  stopDown()
    {
        try {
            currentShape.stopThread();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*
    线程继续
     */
    public void continueThread()
    {
        currentShape.continueThread();
    }
}
