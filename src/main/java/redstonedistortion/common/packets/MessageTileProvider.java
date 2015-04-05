package redstonedistortion.common.packets;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import redstonedistortion.bases.tiles.TileProvider;

/**
 * Created by UniversalRed on 15-02-16.
 */
public class MessageTileProvider implements IMessage, IMessageHandler<MessageTileProvider, IMessage> {

    int x;
    int y;
    int z;

    World world;
    int energy;

    public MessageTileProvider() {

    }

    public MessageTileProvider(int x, int y, int z, int energy) {
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
    public IMessage onMessage(MessageTileProvider message, MessageContext ctx) {
        if (FMLClientHandler.instance().getClient().theWorld != null) {
            TileEntity entity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
            if (entity instanceof TileProvider) {
                this.energy = message.energy;
            }
        }
        return null;
    }
}

