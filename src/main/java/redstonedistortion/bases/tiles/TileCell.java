package redstonedistortion.bases.tiles;

import buildcraftAdditions.api.configurableOutput.EnumPriority;
import buildcraftAdditions.api.configurableOutput.EnumSideStatus;
import buildcraftAdditions.api.configurableOutput.IConfigurableOutput;
import buildcraftAdditions.api.networking.ISyncronizedTile;
import cofh.api.energy.*;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import redstonedistortion.utils.helpers.Location;
import redstonedistortion.utils.helpers.SideConfiguration;

public class TileCell extends TileBase implements IEnergyReceiver, IEnergyProvider, IConfigurableOutput, ISyncronizedTile, IEnergyStorage {

    public int energy, capacity, maxRecieve, maxExtract;
    protected boolean[] blocked = new boolean[6];
    protected final SideConfiguration configuration = new SideConfiguration();

    public TileCell cell;

    public TileCell()
    {

    }

    public TileCell(int capacity, int maxRecieve, int maxExtract) {
        super();
        this.capacity = capacity;
        this.maxRecieve = maxRecieve;
        this.maxExtract = maxExtract;
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        this.maxRecieve = maxReceive;

        if (!configuration.canReceive(from))
            return 0;
        int recieved = maxReceive;
        if (recieved > capacity - energy)
            recieved = capacity - energy;
        if (recieved > maxReceive)
            recieved = maxReceive;
        if (!simulate) {
            energy += recieved;
            blocked[from.ordinal()] = true;
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
        return this.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        return this.getMaxEnergyStored();
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        energy = tag.getInteger("energy");
        capacity = tag.getInteger("capacity");
        maxRecieve = tag.getInteger("maxRecieve");
        maxExtract = tag.getInteger("maxExtract");
        configuration.readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("energy", energy);
        tag.setInteger("capacity", capacity);
        tag.setInteger("maxRecieve", maxRecieve);
        tag.setInteger("maxExtract", maxExtract);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if(worldObj.isRemote) {
            return;
        }
        if (energy < 0)
            energy = 0;

        if(energy > maxExtract)
        {
            energy = maxExtract;
        } else {
            return;
        }
        sendEnergy();
    }

    protected void sendEnergy()
    {

        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
        {
            TileEntity tile = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
            if (tile instanceof IEnergyHandler)
            {
                IEnergyHandler reciever = (IEnergyHandler) tile;
                cell.extractEnergy(reciever.receiveEnergy(dir.getOpposite(), cell.extractEnergy(dir.getOpposite(), maxExtract, false), false), false);
            }
        }

    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return configuration.canReceive(from) || configuration.canSend(from);
    }

    @Override
    public EnumSideStatus getStatus(ForgeDirection side) {
        return configuration.getStatus(side);
    }

    @Override
    public void changeStatus(ForgeDirection side) {
        configuration.changeStatus(side);
    }

    @Override
    public void setSideConfiguration(SideConfiguration configuration) {
        this.configuration.load(configuration);
    }

    @Override
    public EnumPriority getPriority(ForgeDirection side) {
        return configuration.getPriority(side);
    }

    @Override
    public void changePriority(ForgeDirection side) {
        configuration.changePriority(side);
    }

    @Override
    public SideConfiguration getSideConfiguration() {
        return configuration;
    }


    @Override
    public ByteBuf writeToByteBuff(ByteBuf buf) {
        buf.writeInt(energy);
        configuration.writeToByteBuff(buf);
        return buf;
    }

    @Override
    public ByteBuf readFromByteBuff(ByteBuf buf) {
        energy = buf.readInt();
        configuration.readFromByteBuff(buf);
        return buf;
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

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {

        int energyReceived = Math.min(capacity - energy, Math.min(this.maxRecieve, maxReceive));

        if (!simulate) {
            energy += energyReceived;
        }
        return energyReceived;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {

        int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));

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
}