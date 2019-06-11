package cn.zhangchi;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        TankFrame tf = TankFrame.INSTANCE;
        int initialNum = 10;
        for(int i=0;i<initialNum;i++){
            tf.tanks.add(new Tank(100+i*85,700,true,Group.BAD,Dir.UP));
        }

        while(true){
            try{
                TimeUnit.MILLISECONDS.sleep(25);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            tf.repaint();
        }
    }
}
