package redstonedistortion.bases.tiles;

import buildcraftAdditions.api.configurableOutput.EnumPriority;
import buildcraftAdditions.api.configurableOutput.EnumSideStatus;
import buildcraftAdditions.api.configurableOutput.IConfigurableOutput;
import buildcraftAdditions.api.configurableOutput.SideConfiguration;
import buildcraftAdditions.api.nbt.INBTSaveable;
import buildcraftAdditions.api.networking.ISynchronizedTile;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import redstonedistortion.bases.utils.helpers.Location;
import sun.plugin2.main.client.CALayerProvider;

/**
 * Created by UniversalRed on 15-03-03.
 */
public class TileProvider extends TileBase implements ISynchronizedTile, IEnergyProvider, INBTSaveable, IConfigurableOutput {

    public int capacity, maxExtract, maxInput, energy;
    public SideConfiguration configurations = new SideConfiguration();

    public TileProvider(int capacity, int maxTransfer) {
        this.capacity = capacity;
        this.maxInput = maxTransfer;
        this.maxExtract = maxTransfer;
    }

    public TileProvider() {

    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (worldObj.isRemote) {
            return;
        }

        if (energy < 0) {
            energy = 0;
        }

        if(capacity < energy) {
            capacity = energy;
        }

        if(energy > 0) {
            outputEnergy();
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        configurations.readFromNBT(tag);
        tag.setInteger("energy", energy);
        tag.setInteger("capacity", capacity);
        tag.setInteger("maxInput", maxInput);
        tag.setInteger("maxExtract", maxExtract);

    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        configurations.writeToNBT(tag);
        energy = tag.getInteger("energy");
        maxInput = tag.getInteger("maxInput");
        maxExtract = tag.getInteger("maxExtract");
        capacity = tag.getInteger("capacity");
    }

    @Override
    public void writeToByteBuff(ByteBuf buf) {
        super.writeToByteBuff(buf);
        buf.writeInt(energy);
        configurations.writeToByteBuff(buf);
    }

    @Override
    public void readFromByteBuff(ByteBuf buf) {
        super.readFromByteBuff(buf);
        energy = buf.readInt();
        configurations.readFromByteBuff(buf);
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {

        if (!configurations.canSend(from)) {
            return 0;
        }
        int energyExtracted = Math.min(energy, Math.min(maxExtract, maxExtract));
        if (!simulate)
            energy -= energyExtracted;
        return energyExtracted;
    }

    protected void outputEnergy() {
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            for (EnumPriority priority : EnumPriority.values()) {
                if (configurations.getPriority(direction) != priority)
                    continue;
                if (!configurations.canSend(direction))
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
                    energy -= output;
                }
            }
        }
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
        return configurations.canReceive(from) || configurations.canSend(from);
    }

    @Override
    public EnumPriority getPriority(ForgeDirection side) {
        return configurations.getPriority(side);
    }

    @Override
    public void changePriority(ForgeDirection side) {
        configurations.changePriority(side);
    }

    @Override
    public SideConfiguration getSideConfiguration() {
        return configurations;
    }

    @Override
    public void setSideConfiguration(SideConfiguration configuration) {
        this.configurations.load(configuration);
    }

    @Override
    public EnumSideStatus getStatus(ForgeDirection side) {
        return configurations.getStatus(side);
    }

    @Override
    public void changeStatus(ForgeDirection side) {
        configurations.changeStatus(side);
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    @Override
    public int energyLoss() {
        return 0;
    }
}
