package cn.zhangchi;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        TankFrame tf = TankFrame.INSTANCE;

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
