package redstonedistortion.bases.tiles;

import buildcraftAdditions.api.configurableOutput.EnumPriority;
import buildcraftAdditions.api.configurableOutput.EnumSideStatus;
import buildcraftAdditions.api.configurableOutput.IConfigurableOutput;
import buildcraftAdditions.api.configurableOutput.SideConfiguration;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyReceiver;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import redstonedistortion.bases.utils.helpers.Location;

/**
 * Created by UniversalRed on 15-03-17.
 */
public abstract class TileCell extends TileBase implements IEnergyHandler, IConfigurableOutput {

    protected final SideConfiguration configuration = new SideConfiguration();
    public int energy, capacity, maxTransfer;

    public TileCell(int capacity, int maxTransfer) {
        this.capacity = capacity;
        this.maxTransfer = maxTransfer;
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
        if (extracted > this.maxTransfer)
            extracted = this.maxTransfer;
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
        capacity = tag.getInteger("maxEnergy");
        maxTransfer = tag.getInteger("maxExtract");
        maxTransfer = tag.getInteger("maxInput");
        configuration.readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("energy", energy);
        tag.setInteger("maxEnergy", capacity);
        tag.setInteger("maxTransfer", maxTransfer);
        configuration.writeToNBT(tag);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if(worldObj.isRemote) {
            return;
        }

        if (energy < 0) {
            energy = 0;
        }
        outputEnergy();
    }

    protected void outputEnergy() {
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
                    if (sendEnergy > maxTransfer)
                        sendEnergy = maxTransfer;

                    int output = energyHandler.receiveEnergy(direction.getOpposite(), sendEnergy, true);
                        energy -= output;
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
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    @Override
    public void writeToByteBuff(ByteBuf buf) {
        buf.writeInt(energy);
        configuration.writeToByteBuff(buf);
    }

    @Override
    public void readFromByteBuff(ByteBuf buf) {
        energy = buf.readInt();
        configuration.readFromByteBuff(buf);
    }

    @Override
    public int energyLoss() {
        return 0;
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
    public void setSideConfiguration(SideConfiguration configuration) {
        this.configuration.load(configuration);
    }
}