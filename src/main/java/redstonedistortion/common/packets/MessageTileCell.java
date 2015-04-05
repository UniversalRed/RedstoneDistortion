package redstonedistortion.common.packets;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import redstonedistortion.bases.tiles.TileCell;

/**
 * Created by UniversalRed on 15-02-16.
 */
public class MessageTileCell implements IMessage, IMessageHandler<MessageTileCell, IMessage> {

    int x;
    int y;
    int z;

    int energy;

    public MessageTileCell() {

    }

    //This constructor will be used for the syncing of stuff.
    public MessageTileCell(int x, int y, int z, int energy) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.energy = energy;
    }


    //Note that the order in these methods are important.
    @Override
    public void fromBytes(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        energy = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(energy);
    }

	/* IMESSAGEHANDLER METHODS */

    @Override
    public IMessage onMessage(MessageTileCell message, MessageContext ctx) {
        if (FMLClientHandler.instance().getClient().theWorld != null) {
            TileEntity entity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
            if (entity instanceof TileCell)
                this.energy = message.energy;
        }
        return null;
    }
}