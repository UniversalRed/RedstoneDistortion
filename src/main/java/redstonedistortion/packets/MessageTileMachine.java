package redstonedistortion.packets;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import redstonedistortion.bases.tiles.TileMachine;

/**
 * Created by UniversalRed on 15-02-16.
 */
public class MessageTileMachine implements IMessage, IMessageHandler<MessageTileMachine, IMessage> {

    //Coords of the tile, so the client knows what tile to update.
    int x;
    int y;
    int z;

    int energy;

    public MessageTileMachine() {

    }

    //This constructor will be used for the syncing of stuff.
    public MessageTileMachine(int x, int y, int z, int energy) {
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

    //The method that handles the recieved packet, having an instance of the Message that was send and MessageServerContext which is only needed when you recieve on the server
    @Override
    public IMessage onMessage(MessageTileMachine message, MessageContext ctx) {
        if (FMLClientHandler.instance().getClient().theWorld != null) { //get the client world and check if not null
            TileEntity entity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z); //get the tile entity from the world, use the message.x values and not the ones from this class itself as those will not be the same as the recieved ones from the method params.
            if (entity instanceof TileMachine) //check if the tile you got is an instanceof the tile you wanted.
                this.energy = message.energy; // set the energy of the tile the recieved value at the client side.
        }
        return null; //just return null, this is not important (in this case).
    }
}
