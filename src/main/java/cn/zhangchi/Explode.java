package cn.zhangchi;

import java.awt.*;

public class Explode {
    private int x,y;
    private int step = 0;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics g){
        g.drawImage(ResourceManager.explodes[step++],x,y,null);

        if(step >= ResourceManager.explodes.length){
            TankFrame.INSTANCE.explodes.remove(this);
        }
    }
}
