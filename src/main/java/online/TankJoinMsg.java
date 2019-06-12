package online;

import cn.zhangchi.Dir;
import cn.zhangchi.Group;
import cn.zhangchi.Tank;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

public class TankJoinMsg {
    public int x,y;
    public Dir dir;
    public Group group;
    public boolean moving;
    public UUID id;

    public TankJoinMsg(){}

    public TankJoinMsg(Tank t){
        this.x = t.getX();
        this.y = t.getY();
        this.dir = t.getDir();
        this.group = t.getGroup();
        this.moving = t.isMoving();
        this.id = t.getId();
    }

    public TankJoinMsg(int x, int y, Dir dir, Group group, boolean moving, UUID id) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.moving = moving;
        this.id = id;
    }

    public byte[] toBytes(){
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        byte[] bytes = null;

        try{
            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);

            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(dir.ordinal());
            dos.writeInt(group.ordinal());
            dos.writeBoolean(moving);
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());

            dos.flush();
            bytes = baos.toByteArray();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(baos != null){
                    baos.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }

            try{
                if(dos != null){
                    dos.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return bytes;
    }
}
