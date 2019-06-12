package online;

import cn.zhangchi.Dir;
import cn.zhangchi.Group;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.UUID;

public class TankJoinMsgDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes() < 8) return;

        in.markReaderIndex();

        int len = in.readInt();
        if(in.readableBytes() < len){
            in.resetReaderIndex();
            return;
        }

        TankJoinMsg msg = new TankJoinMsg();
        msg.x = in.readInt();
        msg.y = in.readInt();
        msg.dir = Dir.values()[in.readInt()];
        msg.group = Group.values()[in.readInt()];
        msg.moving = in.readBoolean();
        msg.id = new UUID(in.readLong(),in.readLong());

        out.add(msg);


    }
}
