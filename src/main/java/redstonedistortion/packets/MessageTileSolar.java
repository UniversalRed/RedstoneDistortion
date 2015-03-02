package redstonedistortion.packets;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by UniversalRed on 15-02-16.
 */
public class MessageTileSolar implements IMessage, IMessageHandler<MessageTileSolar, IMessage> {

    int x;
    int y;
    int z;

    int energy;

    public MessageTileSolar() {

    }

    public MessageTileSolar(int x, int y, int z, int energy) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.energy = energy;
    }

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

    @Override
    public IMessage onMessage(MessageTileSolar message, MessageContext ctx) {
        if (FMLClientHandler.instance().getClient().theWorld != null) {
            TileEntity entity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
            //if (entity instanceof TileSolarPanel)
            //    this.energy = message.energy;
        }
        return null;
    }
}

