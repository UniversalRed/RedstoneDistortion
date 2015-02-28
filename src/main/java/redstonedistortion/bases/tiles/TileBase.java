package redstonedistortion.bases.tiles;

import buildcraftAdditions.api.networking.ISyncronizedTile;
import buildcraftAdditions.api.networking.MessageByteBuff;
import cpw.mods.fml.common.network.NetworkRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import redstonedistortion.packets.MessageTileCell;
import redstonedistortion.packets.MessageTileMachine;
import redstonedistortion.packets.MessageTileSolar;
import redstonedistortion.packets.PacketHandler;

/**
 * Created by UniversalRed on 15-02-16.
 */
public class TileBase extends TileEntity implements ISyncronizedTile
{
    public int timer;
    public int x, y, z;

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
            PacketHandler.INSTANCE.sendToAllAround(new MessageTileMachine(), new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, getX(), getY(), getZ(), 20));
            PacketHandler.INSTANCE.sendToAllAround(new MessageTileCell(), new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, getX(), getY(), getZ(), 20));
            PacketHandler.INSTANCE.sendToAllAround(new MessageTileSolar(), new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, getX(), getY(), getZ(), 20));
            PacketHandler.INSTANCE.sendToAllAround(new MessageByteBuff(this), new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, getX(), getY(), getZ(), 20));
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
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
