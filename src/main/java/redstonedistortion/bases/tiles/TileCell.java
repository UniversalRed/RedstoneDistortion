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
import redstonedistortion.packets.MessageConfiguration;
import redstonedistortion.packets.PacketHandler;
import redstonedistortion.utils.helpers.SideConfiguration;

public abstract class TileCell extends TileBase implements IEnergyReceiver, IEnergyProvider, ISyncronizedTile, IConfigurableOutput
{
    public SideConfiguration configuration = new SideConfiguration();

    protected boolean[] blocked = new boolean[6];

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
    public void updateEntity()
    {
        super.updateEntity();

        if (energy < 0)
            energy = 0;
        sendEnergy();
    }

    public abstract void sendEnergy();

    protected boolean canSharePower(TileEntity target, ForgeDirection outputSide) {
        if (configuration.canReceive(outputSide) && configuration.canSend(outputSide) && target instanceof TileCell) {
            TileCell keb = (TileCell) target;
            if (keb.getStatus(outputSide.getOpposite()) == EnumSideStatus.BOTH)
                return true;
        }
        return false;
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return configuration.canReceive(from) || configuration.canSend(from);
    }

    public void sendConfigurationToSever() {
        PacketHandler.INSTANCE.sendToServer(new MessageConfiguration(this));
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