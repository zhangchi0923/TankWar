package cn.zhangchi;

import java.awt.*;

public class Tank {
    private int x,y;
    private boolean moving;
    private Group group;
    private Dir dir = Dir.DOWN;

    private static int WIDTH = 20;
    private static int HEIGHT = 20;
    private static int SPEED = 10;

    public Tank(int x, int y, boolean moving, Group group,Dir dir) {
        this.x = x;
        this.y = y;
        this.moving = moving;
        this.group = group;
        this.dir = dir;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x,y,WIDTH,HEIGHT);
        g.setColor(c);

        move();
    }

    private void move(){
        if(!isMoving()) return;

        switch(dir){
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
    }
}
