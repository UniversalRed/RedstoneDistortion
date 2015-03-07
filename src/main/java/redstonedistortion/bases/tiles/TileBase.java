package redstonedistortion.bases.tiles;

import buildcraftAdditions.api.networking.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import redstonedistortion.packets.*;

/**
 * Created by UniversalRed on 15-02-16.
 */
public abstract class TileBase extends TileEntity implements ISyncronizedTile
{
    public World world;
    public int timer;
    public int x, y, z;

    public TileBase() {

    }

    @Override
    public void updateEntity() {
        if (timer <= 0) {
            sync();
            timer = 20;
        } else {
            timer--;
        }

        if(!(!worldObj.canBlockSeeTheSky(getX(), getY() + 1, getZ()) && worldObj.isRaining())) {
            getDefunctionTicker();
        }
        machineProcessing();
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

    protected void getDefunctionTicker() {
        if (timer <= 0)
        {
            energyLoss();
            timer = 5;
        } else
        {
            timer--;
        }
    }

    public abstract int energyLoss();

    public void machineProcessing(){

    }
}