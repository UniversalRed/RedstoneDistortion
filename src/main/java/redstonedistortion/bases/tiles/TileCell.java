package redstonedistortion.bases.tiles;

import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.nbt.NBTTagCompound;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import net.minecraftforge.common.util.ForgeDirection;
import redstonedistortion.packets.MessageTileCell;
import redstonedistortion.packets.PacketHandler;

public class TileCell extends TileBase implements IEnergyHandler
{
    public static int energy;
    public static int capacity;
    public static int maxReceive;
    public static int maxExtract;

    protected EnergyStorage storage = new EnergyStorage(capacity);

    int ticker;

    public TileCell()
    {
        super();
        ticker = 0;
    }

    public TileCell(int capacity)
    {
        TileCell.capacity = capacity;
        energy = EnergyStorage.energy;
        maxReceive = EnergyStorage.maxReceive;
        maxExtract = EnergyStorage.maxExtract;

    }

    @Override
    public void updateEntity() {
        if (ticker % 20 == 0)
            sync();
    }

    public void sync() {
        PacketHandler.INSTANCE.sendToAllAround(new MessageTileCell(xCoord, yCoord, zCoord, energy), new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, getX(), getY(), getZ(), 20));
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        storage.readFromNBT(nbt);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        storage.writeToNBT(nbt);
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        return storage.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        return storage.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored(ForgeDirection from) {
        return storage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        return storage.getMaxEnergyStored();
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return true;
    }
}