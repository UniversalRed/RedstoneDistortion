package redstonedistortion.bases.tiles;

import buildcraftAdditions.api.configurableOutput.EnumPriority;
import buildcraftAdditions.api.configurableOutput.EnumSideStatus;
import buildcraftAdditions.api.configurableOutput.IConfigurableOutput;
import buildcraftAdditions.api.networking.ISyncronizedTile;
import cofh.api.energy.IEnergyProvider;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import redstonedistortion.utils.helpers.SideConfiguration;

/**
 * Created by UniversalRed on 15-02-20.
 */
public class TileGeneratorBase extends TileBase implements IEnergyProvider, ISyncronizedTile, IConfigurableOutput
{
    public SideConfiguration configuration = new SideConfiguration();


    public int capacity;
    public int energy;
    public int maxRecieve;
    public int maxExtract;

    public TileGeneratorBase(int capacity, int maxExtract)
    {
        this.capacity = capacity;
        this.maxExtract = maxExtract;
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
    public SideConfiguration getSideConfiguration() {
        return getSideConfiguration();
    }

    @Override
    public void setSideConfiguration(SideConfiguration configuration) {
        setSideConfiguration(configuration);
    }

    @Override
    public EnumSideStatus getStatus(ForgeDirection side) {
        return getStatus(side);
    }

    @Override
    public void changeStatus(ForgeDirection side) {
        changeStatus(side);
    }

    @Override
    public EnumPriority getPriority(ForgeDirection side) {
        return getPriority(side);
    }

    @Override
    public void changePriority(ForgeDirection side) {
        changePriority(side);
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
    public boolean canConnectEnergy(ForgeDirection from) {
        return configuration.canSend(from) || configuration.canReceive(from);
    }
}
