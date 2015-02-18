package redstonedistortion.bases.tiles;

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

    //a simple ticker we will use.
    int ticker;

    public TileCell() { //simple constructor that should be always be called just add it here and don't worry about it.
        super();
        ticker = 0; //sets the ticker to 0 every time the world get's loaded or when the tile is placed donw
    }
    @Override

    public void updateEntity() {
        if (ticker % 20 == 0) //sync every second.
            sync();
    }

    public void sync() {
        PacketHandler.INSTANCE.sendToAll(new MessageTileCell(xCoord, yCoord, zCoord, energy)); // this sends the message to all the clients around that tile which will casue it to sync.
    }

    public TileCell(int capacity)
    {
        TileCell.capacity = capacity;
        energy = EnergyStorage.energy;
        maxReceive = EnergyStorage.maxReceive;
        maxExtract = EnergyStorage.maxExtract;

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