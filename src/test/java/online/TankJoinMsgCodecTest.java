package online;

import cn.zhangchi.Dir;
import cn.zhangchi.Group;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class TankJoinMsgCodecTest {

    @Test
    public void testEncoder() {
        EmbeddedChannel ch = new EmbeddedChannel();
        ch.pipeline().addLast(new TankJoinMsgEncoder());

        UUID id = UUID.randomUUID();
        TankJoinMsg msg = new TankJoinMsg(5,8, Dir.UP, Group.GOOD,true,id);

        ch.writeOutbound(msg);

        ByteBuf buf = (ByteBuf) ch.readOutbound();
        int len = buf.readInt();
        assertEquals(33,len);

        int x = buf.readInt();
        int y = buf.readInt();
        int dirOrd = buf.readInt();
        Dir dir = Dir.values()[dirOrd];
        int groupOrd = buf.readInt();
        Group group = Group.values()[groupOrd];
        boolean moving = buf.readBoolean();
        UUID uuid = new UUID(buf.readLong(),buf.readLong());

        assertEquals(5,x);
        assertEquals(8,y);
        assertEquals(Dir.UP,dir);
        assertEquals(Group.GOOD,group);
        assertEquals(true,moving);
        assertEquals(id,uuid);
    }

    @Test
    public void testDecoder(){
        EmbeddedChannel ch = new EmbeddedChannel();
        ch.pipeline().addLast(new TankJoinMsgDecoder());

        UUID id = UUID.randomUUID();
        TankJoinMsg msg = new TankJoinMsg(5,8,Dir.UP,Group.GOOD,true,id);

        ByteBuf buf = Unpooled.buffer();
        byte[] bytes = msg.toBytes();
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);

        ch.writeInbound(buf.duplicate());
        TankJoinMsg msgR = (TankJoinMsg)ch.readInbound();

        assertEquals(5,msgR.x);
        assertEquals(8,msgR.y);
        assertEquals(Dir.UP,msgR.dir);
        assertEquals(Group.GOOD,msgR.group);
        assertEquals(true,msgR.moving);
        assertEquals(id,msgR.id);
    }
}