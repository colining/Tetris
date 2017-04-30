package com.company.controller;

import com.company.model.*;
/**
 * Created by asus on 2017/4/20.
 */
public interface ShapeListener {
    public  abstract void shapeMoveDown(Shape shape);

    public  abstract  boolean shapeIsMoveDownable(Shape shape);
}
