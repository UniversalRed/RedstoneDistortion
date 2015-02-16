package redstonedistortion.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.ChatComponentText;

import java.util.Random;

/**
 * Created by UniversalRed on 15-02-16.
 */
public class RDMessage implements IMessage
{
    public static int value;

    public RDMessage() {
        value = new Random().nextInt();
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        value = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(value);
    }

    public static class Handler implements IMessageHandler<RDMessage, IMessage>
    {

        public IMessage onMessage(RDMessage message, MessageContext ctx)
        {
            //get the player and add a chat message
            ctx.getServerHandler().playerEntity.addChatComponentMessage(new ChatComponentText("The number that was generated was: " + RDMessage.value));
            return null;
        }
    }
}
