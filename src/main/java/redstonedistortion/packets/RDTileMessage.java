package redstonedistortion.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import redstonedistortion.factory.base.TileMachine;

import java.util.Random;

/**
 * Created by UniversalRed on 15-02-16.
 */
public class RDTileMessage implements IMessage
{
    public static int value;
    public int id;
    public int x;
    public int y;
    public int z;


    public RDTileMessage() {
        this.value = new Random().nextInt();
    }

    public RDTileMessage(TileEntity tile, int id, int value)
    {
        this.x = tile.xCoord;
        this.y = tile.yCoord;
        this.z = tile.zCoord;
        this.id = id;
        this.value = value;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.value = buf.readInt();
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.id = buf.readInt();
        this.value = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(this.value);
        buf.writeInt(this.id);
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
    }

    public static class Handler implements IMessageHandler<RDTileMessage, IMessage>
    {
        public IMessage onMessage(RDTileMessage message, MessageContext ctx)
        {
            TileEntity tile = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z);

            if(tile instanceof TileMachine)
            {
                ((TileMachine) tile).updateEntity();
            }
            return null;
        }
    }
}
