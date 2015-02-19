package redstonedistortion.bases.tiles;

import cofh.api.energy.*;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import redstonedistortion.utils.enums.EnumSideStatus;
import redstonedistortion.utils.helpers.SideConfiguration;

public class TileCell extends TileBase implements IEnergyHandler, IEnergyStorage
{
    public SideConfiguration configuration = new SideConfiguration();

    public int energy;
    public int capacity;
    public int maxReceive;
    public int maxExtract;

    public TileCell()
    {
        super();
    }

    public TileCell(int capacity, int maxReceive, int maxExtract) {

        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
    }


    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        this.maxReceive = maxReceive;

        int energyReceived = Math.min(capacity - energy, Math.min(EnergyStorage.maxReceive, maxReceive));

        if (!simulate) {
            energy += energyReceived;
        }
        return energyReceived;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        this.maxExtract = maxExtract;

        int energyExtracted = Math.min(energy, Math.min(EnergyStorage.maxExtract, maxExtract));

        if (!simulate) {
            energy -= energyExtracted;
        }
        return energyExtracted;
    }

    @Override
    public int getEnergyStored() {

        return energy;
    }

    @Override
    public int getMaxEnergyStored() {

        return capacity;
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        this.maxReceive = maxReceive;

        if (!configuration.canReceive(from))
            return 0;
        int recieved = maxReceive;
        if (recieved > capacity - energy)
            recieved = capacity - energy;
        if (recieved > maxReceive)
            recieved = maxReceive;
        if (!simulate) {
            energy += recieved;
        }
        return recieved;
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        this.maxExtract = maxExtract;

        if (!configuration.canSend(from))
            return 0;
        int extracted = maxExtract;
        if (extracted > energy)
            extracted = energy;
        if (extracted > maxExtract)
            extracted = maxExtract;
        if (!simulate)
            energy -= extracted;
        return extracted;
    }

    @Override
    public int getEnergyStored(ForgeDirection from) {
        return energy;
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        return capacity;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        energy = tag.getInteger("energy");
        capacity = tag.getInteger("capacity");
        maxReceive = tag.getInteger("maxReceive");
        maxExtract = tag.getInteger("maxExtract");
        configuration.readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("energy", energy);
        tag.setInteger("capacity", capacity);
        tag.setInteger("maxReceive", maxReceive);
        tag.setInteger("maxExtract", maxExtract);
        configuration.writeToNBT(tag);
    }


    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return true;
    }


    @Override
    public ByteBuf writeToByteBuff(ByteBuf buf)
    {
        buf.writeInt(energy);
        return buf;
    }

    @Override
    public ByteBuf readFromByteBuff(ByteBuf buf)
    {
        energy = buf.readInt();
        return buf;
    }

    public EnumSideStatus getStatus(ForgeDirection side) {
        return configuration.getStatus(side);
    }
}