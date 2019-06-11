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
## 第三次提交
1.在move()的最后添加boundsCheck()，防止坦克运动越界  
2.创建Bullet类，属性：位置、group、dir、SPEED、width、height、living  
3.move()方法类似于Tank，注意方法最后更新rect的x、y并在子弹触碰边界时调用die()方法  
4.paint()方法先判断living，如果false就在TankFrame的bullets中remove当前bullet对象；否则根据方向从ResourceMananger中读取图片，最后调用move  
5.回到TankFrame中修改KeyListener，当空格键松开调用fire()  
6.TankFrame 的paint注意要调用bullets中的所有bullet的paint()方法，这里用的是经典的循环遍历，forEach会抛出ConcurrentModificationException
## 第四次提交
1.新建Explode类，只需要x、y属性；paint()方法让16个图片全部画出来，当step大于等于16后remove(this)
2.Bullet类中collideWith()方法，当相撞时子弹和坦克都死，并new一个Explode，并在TankFrame中的explodes中加入进去  
3.TankFrame的paint()时，让每颗子弹都和坦克进行碰撞检测
