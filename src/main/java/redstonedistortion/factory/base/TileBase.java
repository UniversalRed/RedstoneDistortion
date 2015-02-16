package redstonedistortion.factory.base;

import cpw.mods.fml.common.network.NetworkRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import redstonedistortion.network.ISynchronizedTile;
import redstonedistortion.network.NetWorkHandler;
import redstonedistortion.packets.RDMessageByteBuf;

/**
 * Created by UniversalRed on 15-02-16.
 */
public class TileBase extends TileEntity implements ISynchronizedTile
{
    public int timer;
    public int networkRange = 15;

    @Override
    public void updateEntity() {
        if (timer <= 0) {
            sync();
            timer = 20;
        } else
            timer--;
    }

    public void sync() {
        if (!worldObj.isRemote) {
            NetWorkHandler.INSTANCE.sendToAllAround(new RDMessageByteBuf(this), new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, getX(), getY(), getZ(), 20));
        }
    }

    @Override
    public ByteBuf writeToByteBuff(ByteBuf buf) {
        return null;
    }

    @Override
    public ByteBuf readFromByteBuff(ByteBuf buf) {
        return null;
    }

    @Override
    public int getX() {
        return xCoord;
    }

    @Override
    public int getY() {
        return yCoord;
    }

    @Override
    public int getZ() {
        return zCoord;
    }
}
