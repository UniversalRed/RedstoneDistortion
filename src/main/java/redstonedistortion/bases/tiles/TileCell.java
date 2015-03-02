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

public class TileCell extends TileBase implements IEnergyStorage, IEnergyHandler, IConfigurableOutput, ISyncronizedTile {

    public int energy, maxEnergy, maxInput, maxOutput;
    protected final SideConfiguration configuration = new SideConfiguration();

    public TileCell() {

    }

    public TileCell(int maxEnergy, int maxInput, int maxOutput) {
        this.maxEnergy = maxEnergy;
        this.maxInput = maxInput;
        this.maxOutput = maxOutput;
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        if (!configuration.canReceive(from))
            return 0;
        int recieved = maxReceive;
        if (recieved > maxEnergy - energy)
            recieved = maxEnergy - energy;
        if (recieved > maxInput)
            recieved = maxInput;
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
        if (extracted > maxOutput)
            extracted = maxOutput;
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
        return maxEnergy;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        energy = tag.getInteger("energy");
        maxEnergy = tag.getInteger("maxEnergy");
        maxInput = tag.getInteger("maxInput");
        maxOutput = tag.getInteger("maxOutput");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("energy", energy);
        tag.setInteger("maxEnergy", maxEnergy);
        tag.setInteger("maxInput", maxInput);
        tag.setInteger("maxOutput", maxOutput);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (energy < 0)
            energy = 0;
        sendEnergy();
    }

    protected void sendEnergy() {
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
                    if (sendEnergy > maxOutput)
                        sendEnergy = maxOutput;

                    int output = energyHandler.receiveEnergy(direction.getOpposite(), sendEnergy, false);
                        energy -= output;
                }
            }
        }
    }

    @Override
    public boolean canConnectEnergy (ForgeDirection from){
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

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {

        int energyReceived = Math.min(maxEnergy - energy, Math.min(maxInput, maxReceive));

        if (!simulate) {
            energy += energyReceived;
        }
        return energyReceived;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {

        int energyExtracted = Math.min(energy, Math.min(this.maxOutput, maxExtract));

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

        return maxEnergy;
    }

    @Override
    public int energyLoss() {
        return energy;
    }
}
