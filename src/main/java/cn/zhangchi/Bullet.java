package cn.zhangchi;

import java.awt.*;

public class Bullet {
    private int x,y;
    private Group group = Group.BAD;
    private boolean living = true;
    private Dir dir;
    private static final int SPEED = 20;
    static final int Width = ResourceManager.bulletU.getWidth();
    static final int Height = ResourceManager.bulletU.getHeight();

    private Rectangle rect = new Rectangle();

    public Bullet(int x, int y, Group group, Dir dir) {
        this.x = x;
        this.y = y;
        this.group = group;
        this.dir = dir;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = Width;
        rect.height = Height;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics g){
        if(!living) TankFrame.INSTANCE.bullets.remove(this);

        switch(dir){
            case LEFT:
                g.drawImage(ResourceManager.bulletL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceManager.bulletR,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceManager.bulletU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceManager.bulletD,x,y,null);
                break;
        }
        move();
    }

    private void move(){
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
        rect.x = this.x;
        rect.y = this.y;

        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT)
            die();
    }

    private void die(){
        setLiving(false);
    }

    public boolean collideWith(Tank t){
        if(this.group == t.getGroup()) return false;

        if(this.rect.intersects(t.getRect())){
            this.die();
            t.die();
            TankFrame.INSTANCE.explodes.add(new Explode(t.getX(),t.getY()));
            return true;
        }
        return false;
    }
}
