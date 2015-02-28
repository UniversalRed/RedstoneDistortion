package redstonedistortion.bases.tiles;

import buildcraftAdditions.api.configurableOutput.EnumPriority;
import buildcraftAdditions.api.configurableOutput.EnumSideStatus;
import buildcraftAdditions.api.configurableOutput.IConfigurableOutput;
import buildcraftAdditions.api.networking.ISyncronizedTile;
import cofh.api.energy.*;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import redstonedistortion.utils.helpers.Location;
import redstonedistortion.utils.helpers.SideConfiguration;

public class TileCell extends TileBase implements IEnergyReceiver, IEnergyProvider, IConfigurableOutput, ISyncronizedTile {

    public int energy, capacity, maxRecieve, maxExtract;
    protected boolean[] blocked = new boolean[6];
    protected final SideConfiguration configuration = new SideConfiguration();

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
        if (energy < 0)
            energy = 0;
        sendEnergy();
    }

    protected void sendEnergy()
    {
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            for (EnumPriority priority : EnumPriority.values()) {
                if (configuration.getPriority(direction) != priority)
                    continue;
                if (!configuration.canSend(direction))
                    continue;
                Location location = new Location(worldObj, xCoord, yCoord, zCoord);
                location.move(direction);
                IEnergyReceiver energyHandler = null;
                if (location.getTileEntity() != null && location.getTileEntity() instanceof IEnergyReceiver)
                    energyHandler = (IEnergyReceiver) location.getTileEntity();
                if (energyHandler != null) {
                    int sendEnergy = energy;
                    if (sendEnergy < 0)
                        sendEnergy = 0;
                    if (sendEnergy > maxExtract)
                        sendEnergy = maxExtract;

                    int output = energyHandler.receiveEnergy(direction.getOpposite(), sendEnergy, false);
                    if(output > maxExtract) {
                        energy -= output;
                    }
                }
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
}