package com.company.model;



import java.awt.*;
import java.awt.event.KeyAdapter;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.util.*;
import com.company.controller.*;
/**
 * Created by asus on 2017/4/17.
 */
public class Shape {
    /*
    形状
     */
        int top = 0;                                            //距离顶部多长
        int left = CELLWEITH/2-2;                               //距离左端多长，新形状在中间掉落
        int [][]body;                                           //形状的数组
        int shapekind;                                          //形状类型
        int state =0;                                           //形状在哪个状态
        ShapeListener shapeListener;                            //监听器
        Thread thread = null;                                   //线程
        boolean suspended;                                      //线程是否要挂起
        ShapeDriver shapeDriver =new ShapeDriver();             //线程实际的对象

    /*
    get/set
     */
    public int getShapekind() {
        return shapekind;
    }

    public void setShapekind(int shapekind) {
        this.shapekind = shapekind;
    }

    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }

    //当前的形状是哪个状态；
    public void setBody(int[][] body) {
        this.body = body;

    }
    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    /*
    增加监听器，启动线程
     */
    public void addListenser(ShapeListener shapeListener1)
    {
        if (shapeListener1!=null)
            this.shapeListener = shapeListener1;
        newThreed();
    }

    public void  newThreed()
    {
        /*
        新建一个线程
         */
        thread = new Thread(shapeDriver);
        thread.start();
    }
    public void stopThread() throws InterruptedException {

        suspended = true;
    }
    public synchronized void continueThread()
    {
        /*
        唤醒线程
         */
        synchronized (shapeDriver)          //必须锁住线程对象，否则将报错
        {
            suspended = false;
            System.out.println("akjhjkahfskfhlasfk");
            shapeDriver.notify();
        }

    }
    /*
    形状移动的相关函数
     */
    public void down()
    {
        top++;
    }
    public boolean candown()
    {
        if (top>=18)
            return false;
        return true;
    }
    public void moveleft()
    {
        left--;
    }
    public void moveright()
    {
        left++;
    }
    public void rotate()
    {
        state = (state+1)%body.length;
    }

    public Color getcolor()
    {
        Color color = null;
        switch (shapekind)
        {
            case 0: color = new Color(171,71,188);
            break;
            case 1: color = new Color(33,150,243);
            break;
            case 2: color = new Color(76,175,80);
            break;
            case 3: color = new Color(121,85,72);
            break;
            case 4: color = new Color(96,125,139);
            break;
            case 5: color = new Color(255,87,34);
            break;
            case 6: color = new Color(244,143,177);
            break;
        }
        return color;
    }
    /*
    绘图形状自身；
     */
    public  void  draw (Graphics g)
    {
        g.setColor(getcolor());
        for (int x = 0 ; x<4; x++)
            for (int y= 0 ; y<4 ;y++)
            {
                if (getFlagByPos(x,y))
                g.fill3DRect((left+x)*50+50,(top+y)*50,50,50,true);
            }
    }
    /*
    判定当前位置是否被形状覆盖
     */
    private boolean getFlagByPos(int x, int y) {
        return body[state][y*4+x] == 1;
    }

    public boolean isMember(int x , int y ,boolean rotate)
    {
        /*
        对整个边长4的矩形，探测是否有小矩形，如果当前是旋转，便探测旋转后的
        其实不用这么写，直接写到要调用的位置也没问题
        */
        int temp = state;
        if (rotate)
            temp = (state+1)%body.length;
        return body[temp][y*4+x] ==1;
    }




    public class ShapeDriver implements  Runnable
    {

        @Override
        public void run() {
            while (shapeListener.shapeIsMoveDownable(Shape.this)) {
                    down();
                    shapeListener.shapeMoveDown(Shape.this);
                    try {
                        System.out.println("sleep");
                        Thread.sleep(1000);
                        //test();
                        synchronized (shapeDriver) {            //锁住对象
                            System.out.println(this.toString());
                            while (suspended)
                                shapeDriver.wait();             //对象等待
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

        }




    }

}
