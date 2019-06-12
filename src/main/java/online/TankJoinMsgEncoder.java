package online;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class TankJoinMsgEncoder extends MessageToByteEncoder<TankJoinMsg> {
    protected void encode(ChannelHandlerContext channelHandlerContext, TankJoinMsg msg, ByteBuf buf) throws Exception {

        byte[] bytes = msg.toBytes();
        buf.writeInt(bytes.length);
        buf.writeBytes(msg.toBytes());
    }
}
