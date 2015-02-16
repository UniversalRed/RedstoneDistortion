package redstonedistortion.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import io.netty.buffer.ByteBuf;

import net.minecraft.tileentity.TileEntity;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import redstonedistortion.network.ISynchronizedTile;

/**
 * Created by UniversalRed on 15-02-16.
 */
public class RDMessageByteBuf implements IMessage
{

    public ISynchronizedTile tile;
    public int x, y, z;

    public RDMessageByteBuf()
    {

    }

    public RDMessageByteBuf(ISynchronizedTile tile) {
        this.tile = tile;
        x = tile.getX();
        y = tile.getY();
        z = tile.getZ();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        if (FMLClientHandler.instance().getClient().theWorld != null) {
            TileEntity entity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(x, y, z);
            if (entity instanceof ISynchronizedTile) {
                tile = (ISynchronizedTile) entity;
                tile.readFromByteBuff(buf);
            }
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        tile.writeToByteBuff(buf);
    }

    public static class Handler implements IMessageHandler<RDMessageByteBuf, IMessage>
    {
        public IMessage onMessage(RDMessageByteBuf message, MessageContext ctx)
        {
            return null;
        }
    }
}

