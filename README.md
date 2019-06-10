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
