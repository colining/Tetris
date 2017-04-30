package com.company.model;

import com.company.myenum.ShapeAction;

import java.awt.*;
import java.io.Serializable;

import static util.util.CELLHEIGTH;
import static util.util.CELLWEITH;

/**
 * Created by asus on CELLHEIGTH17/4/17.
 */
public class Ground implements Serializable{
    private int [][] ground = new int[CELLWEITH][CELLHEIGTH];

    public void setGround(int[][] ground) {

        this.ground = ground;
    }
    public void clearFround()
    {
        ground = new int[CELLWEITH][CELLHEIGTH];
    }
    /*
    画障碍物
     */
    public void draw(Graphics g)
    {
        for (int i = 0 ; i <CELLWEITH;i++)
            for (int j = 0 ; j< CELLHEIGTH ; j++)
            {
                if (ground[i][j]==1)
                {

                    g.setColor(new Color(0,150,136));
                    g.fill3DRect(i*50+50,j*50+50,50,50,true);

                }
            }
    }
    /*
    下落方块加入障碍物中
     */
    public void add(Shape shape)
    {
        for (int x = 0 ; x< 4 ; x++)
            for (int y = 0 ; y <4 ; y++)
                if (shape.isMember(x,y,false))
                {
                    ground[x+shape.getLeft()][y+shape.getTop()] = 1;
                }
        deleteFulline();
    }
    /*
    判断是否满行
     */
    private void deleteFulline()
    {
        for (int y = CELLHEIGTH-1; y>=0; y--)
        {
            boolean isFullFlag = true;
            for (int x = 0; x<CELLWEITH;x++)
            {
                if (ground[x][y]!=1)
                    isFullFlag=false;
            }
            if (isFullFlag)
                deleteLines(y);
        }
    }
    /*
    消行
     */
    private void deleteLines(int LineNum)
    {
        for (int y = LineNum; y>0; y--)
            for (int x = 0 ; x<CELLWEITH ; x++)
            {
                ground[x][y] = ground[x][y-1];
            }
            for (int x = 0;x<CELLWEITH ;x++)
            {
                ground[x][0] = 0;
            }
    }
    /*
    是否可以移动吗？
     */
    public boolean isMoveabel(Shape shape, ShapeAction action)
    {
        int left = shape.getLeft();
        int top = shape.getTop();
        switch (action)
        {
            case LEFT:
                left--;
                break;
            case DOWN:
                top++;
                break;
            case RIGHT:
                left++;
                break;
            default:
                break;
        }
        if (action!=ShapeAction.ROTATE) {
            System.out.println("jinru");
            return canRotate(shape,action,left,top)==0;
        }
        else if (action==ShapeAction.ROTATE) {
            int leftback = left;
            while (leftback > -3 && leftback < 3) {
                int flag = 0;
                System.out.println("假定坐标当前是" + leftback);
                flag = canRotate(shape,action,leftback,top);
                if (flag == 0) {
                    System.out.println("flag为真");
                    shape.setLeft(leftback);
                    return true;
                }
                    leftback++;
            }
            while (leftback > 4 && leftback < 10) {
                int flag = 0;
                System.out.println("假定坐标当前是" + leftback);
                flag = canRotate(shape,action,leftback,top);
                if (flag == 0) {
                    System.out.println("flag为真");
                    shape.setLeft(leftback);
                    return true;
                }
                    leftback--;
            }
            return canRotate(shape,action,leftback,top)==0;

        }
        return true;
    }
    /*
    是否可以旋转呢
     */
    public int canRotate(Shape shape,ShapeAction action,int left,int top)
    {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (shape.isMember(x, y, action == ShapeAction.ROTATE)) {

                    if ((top + y >= CELLHEIGTH) || (left + x < 0) || ((left + x) > CELLWEITH - 1)
                           ||ground[left+x][top+y] ==1 ) {
                        return 1;
                    }
                }
            }
        }
        return 0;

    }
    /*
    是不是满了
     */
    public boolean isFull()
    {
        for (int x= 0 ; x<CELLWEITH;x++)
        {
            if (ground[x][0] == 1)
                return false;
        }
        return false;
    }
}
