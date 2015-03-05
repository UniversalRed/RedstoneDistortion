package redstonedistortion.bases.tiles;

import buildcraftAdditions.api.configurableOutput.EnumPriority;
import buildcraftAdditions.api.configurableOutput.EnumSideStatus;
import buildcraftAdditions.api.configurableOutput.IConfigurableOutput;
import buildcraftAdditions.api.networking.ISyncronizedTile;
import cofh.api.energy.IEnergyProvider;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import redstonedistortion.utils.helpers.SideConfiguration;

/**
 * Created by UniversalRed on 15-03-03.
 */
public class TileGenerator extends TileBase implements IEnergyProvider, IConfigurableOutput, ISyncronizedTile {

    private SideConfiguration configuration = new SideConfiguration();

    // Basic Machine Requirements
    public int capacity;
    public int energy;
    public int maxExtract;

    public TileGenerator()
    {

    }

    public TileGenerator(int capacity, int maxExtract)
    {
        this.capacity = capacity;
        this.maxExtract = maxExtract;
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        if (!configuration.canSend(from))
            return 0;
        int extracted = maxExtract;
        if (extracted > energy)
            extracted = energy;
        if (extracted > this.maxExtract)
            extracted = this.maxExtract;
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
    public boolean canConnectEnergy(ForgeDirection from) {
        return configuration.canReceive(from) || configuration.canSend(from);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        energy = tag.getInteger("energy");
        capacity = tag.getInteger("capacity");
        maxExtract = tag.getInteger("maxExtract");
        configuration.readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("energy", energy);
        tag.setInteger("capacity", capacity);
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

    @Override
    public int energyLoss() {
        return 0;
    }
}
