# TankWar
*Rebuild Tank-War from scratch*
## 第一次提交
1.创建窗口TankFrame，窗口长、宽等属性，做成单例模式  
2.构造器中addKeyListener并addWindowListener  
3.addKeyListener传入私有类MyKeyListener，其中对方向键的按下或松开响应，设置方向键布尔值，最后setMyTankDir()  
4.setMyTankDir()中根据bL,bR,bU,bD如果都为false则Tank的moving设为false；只要有一个为true，moving为true  
5.Tank中私有的move()方法，moving为false就返回；否则按照SPEED根据方向移动，用switch实现  
6.move()方法在paint()方法中实现，paint()方法还要负责将坦克画出来，现在暂且用Graphics.fillRect()方法画出  
7.TankFrame中while循环中每50毫秒repaint()  
## 第二次提交
1.创建ResourceManager管理好坏坦克、子弹和爆炸的图片  
2.getClassLoader()方法无法找到图片路径，所以改用了File()方法读取images  
3.ResourceManager做成**单例**之后在Tank的paint()方法中无法初始化ResourceManager，暂时先不用单例  
4.Tank的paint()方法按照方向画出不同方向的图片
