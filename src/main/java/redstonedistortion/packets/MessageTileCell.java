package redstonedistortion.packets;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import redstonedistortion.factory.base.TileCell;

/**
 * Created by UniversalRed on 15-02-16.
 */
//The Message class, here is were everything will be read and written to the object that will be send over the network (aka a ByteBuff).
//Note that both IMessage and IMessageHander are implemented, these are just for easy use.
public class MessageTileCell implements IMessage, IMessageHandler<MessageTileCell, IMessage> {

    //Coords of the tile, so the client knows what tile to update.
    int x;
    int y;
    int z;

    //The value that should be synced
    int energy;

    //Empty Constructor, this is needed for some FML internals, I don't know why but it should be there.
    public MessageTileCell() {

    }

    //This constructor will be used for the syncing of stuff.
    public MessageTileCell(int x, int y, int z, int energy) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.energy = energy;
    }

	/* IMESSAGE METHODS */

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

    //The method that handles the recieved packet, having an instance of the Message that was send and MessageServerContext which is only needed when you recieve on the server
    @Override
    public IMessage onMessage(MessageTileCell message, MessageContext ctx) {
        if (FMLClientHandler.instance().getClient().theWorld != null) { //get the client world and check if not null
            TileEntity entity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z); //get the tile entity from the world, use the message.x values and not the ones from this class itself as those will not be the same as the recieved ones from the method params.
            if (entity instanceof TileCell) //check if the tile you got is an instanceof the tile you wanted.
                ((TileCell) entity).energy = message.energy; // set the energy of the tile the recieved value at the client side.
        }
        return null; //just return null, this is not important (in this case).
    }
}

