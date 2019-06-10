package cn.zhangchi;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class ResourceManagerTest {
    @Test
    public void test(){
        try{
            BufferedImage img1 = ImageIO.read(new File("src/images/BadTank1.png"));
            assertNotNull(img1);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        try{
            BufferedImage img2 = ImageIO.read(new File("/Users/zhangchi/myblog/public/IMG_3054.jpg"));
            assertNotNull(img2);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void imageRotateTest(){
        try{
            BufferedImage img = ImageIO.read(new File("src/images/tankU.gif"));
            img = ImageUtil.rotateImage(img,90);
            assertNotNull(img);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}