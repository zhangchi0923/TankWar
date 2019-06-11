package cn.zhangchi;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x,y;
    private boolean moving;
    private Group group;
    private Dir dir = Dir.DOWN;
    private Random r = new Random();
    private boolean living = true;

    private int prevX,prevY;

    private static int SPEED = 7;
    private Rectangle rect = new Rectangle();

    static int Width = ResourceManager.badTankU.getWidth();
    static int Height = ResourceManager.badTankU.getHeight();


    public Tank(int x, int y, boolean moving, Group group,Dir dir) {
        this.x = x;
        this.y = y;
        this.moving = moving;
        this.group = group;
        this.dir = dir;

        rect.x = x;
        rect.y = y;
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

    public Rectangle getRect() {
        return rect;
    }

    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }

    public void paint(Graphics g){
        /*
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x,y,WIDTH,HEIGHT);
        g.setColor(c);
        */
        if(!isLiving()){
            TankFrame.INSTANCE.tanks.remove(this);
        }

        switch(dir){
            case LEFT:
                g.drawImage(this.group == Group.GOOD? ResourceManager.goodTankL:ResourceManager.badTankL,x,y,null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD? ResourceManager.goodTankU:ResourceManager.badTankU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD? ResourceManager.goodTankR:ResourceManager.badTankR,x,y,null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD? ResourceManager.goodTankD:ResourceManager.badTankD,x,y,null);
                break;
            default:
                break;
        }
        move();
    }

    private void move(){
        if(!isMoving()) return;

        prevX = x;
        prevY = y;

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

        if(this.group == Group.BAD && r.nextInt(100)>95){
            setDir(Dir.values()[r.nextInt(4)]);
        }
        if(this.group == Group.BAD && r.nextInt(100)>95){
            this.fire();
        }
        boundsCheck();
        // update rect;
        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheck(){
        if(x < 1) x = 1;
        if(y < 28) y = 28;
        if(x > TankFrame.GAME_WIDTH - Tank.Width -1) x = TankFrame.GAME_WIDTH - Tank.Width -1;
        if(y > TankFrame.GAME_HEIGHT - Tank.Height -1) y = TankFrame.GAME_HEIGHT - Tank.Height -1;
    }

    public void crushCheck(Tank t){
        if(this.rect.intersects(t.getRect())){
            this.back();
            t.back();
        }
    }

    private void back(){
        x = prevX;
        y = prevY;
    }

    public void fire(){
        int bX = this.x + Tank.Width/2 - Bullet.Width/2;
        int bY = this.y + Tank.Height/2 - Bullet.Height/2;

        TankFrame.INSTANCE.bullets.add(new Bullet(bX,bY,this.group,this.dir));
    }

    public void die(){
        setLiving(false);
    }
}
