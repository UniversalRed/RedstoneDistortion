package redstonedistortion.bases.tiles;

import buildcraftAdditions.api.configurableOutput.EnumPriority;
import buildcraftAdditions.api.configurableOutput.EnumSideStatus;
import buildcraftAdditions.api.configurableOutput.IConfigurableOutput;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;

import net.minecraftforge.common.util.ForgeDirection;

import cofh.api.energy.IEnergyReceiver;
import redstonedistortion.core.configurations.ConfigHandler;
import redstonedistortion.utils.helpers.SideConfiguration;

public class TileMachine extends TileBase implements IEnergyReceiver, IConfigurableOutput
{

    private SideConfiguration configuration = new SideConfiguration();

    // Basic Machine Requirements
    public int capacity;
    public int energy;
    public int maxReceive;
    public int maxExtract;
    public static int progress;

    protected static int POWER_USAGE = ConfigHandler.POWER_USAGE;

    protected int currentWorkTime;
    public static int MAX_WORK_TICKS = 20;

    public TileMachine()
    {
        super();
    }

    public TileMachine(int capacity, int maxExtract, int maxReceive)
    {
        this.capacity = capacity;
        this.maxExtract = maxExtract;
        this.maxReceive = maxReceive;
    }

    public int getCookProgressScaled(int i) {
        double deling = (double) currentWorkTime / (double) MAX_WORK_TICKS;
        double vermenigvuldiging = deling * i;
        int totaal = (int) vermenigvuldiging;
        return totaal;
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
    public int getEnergyStored(ForgeDirection from) {
        return energy;
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        return capacity;
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return configuration.canReceive(from) || configuration.canSend(from);
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
}