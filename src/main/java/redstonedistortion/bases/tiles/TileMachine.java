package redstonedistortion.bases.tiles;

import buildcraftAdditions.api.configurableOutput.*;
import buildcraftAdditions.api.networking.ISyncronizedTile;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import cofh.api.energy.IEnergyReceiver;
import redstonedistortion.core.configurations.ConfigHandler;
import redstonedistortion.factory.tiles.machines.TileMechanicalDesolator;
import redstonedistortion.factory.tiles.machines.TileMechanicalFurnace;
import redstonedistortion.recipes.ModRecipes;
import redstonedistortion.utils.helpers.SideConfiguration;

public class TileMachine extends TileBase implements IEnergyReceiver, IConfigurableOutput, ISyncronizedTile, IInventory
{

    private SideConfiguration configuration = new SideConfiguration();

    // Basic Machine Requirements
    public int capacity;
    public int energy;
    public int maxReceive;
    public int maxExtract;

    //Progress for machines
    protected int progress = 0;
    protected int POWER_USAGE = ConfigHandler.POWER_USAGE;
    protected int currentWorkTime;
    protected int MAX_WORK_TICKS = 50;
    protected int MAX_WORK_TICKS_TOTAL = 50;

    protected boolean hasMalfunctioned;


    public TileMachine()
    {
        super();
        hasMalfunctioned = false;
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
        int total = (int) vermenigvuldiging;
        return total;
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
        MAX_WORK_TICKS = tag.getInteger("MAX_WORK_TICKS");
        hasMalfunctioned = tag.getBoolean("hasMalfunctioned");
        configuration.readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("energy", energy);
        tag.setInteger("capacity", capacity);
        tag.setInteger("maxReceive", maxReceive);
        tag.setInteger("maxExtract", maxExtract);
        tag.setInteger("MAX_WORK_TICKS", MAX_WORK_TICKS);
        tag.setBoolean("hasMalfunctioned", hasMalfunctioned);
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
        if(!worldObj.isRemote) {
            return energy--;
        }
        if(energy < 0) {
            energy = 0;
        }
        return energy;
    }

    @Override
    public void machineProcessing() {
        if(worldObj.isRaining() && !worldObj.canBlockSeeTheSky(getX(), getY() + 1, getZ())) {
            POWER_USAGE = POWER_USAGE + 20;
        }
    }

    //This is the energy repetition system
    public int machineContinuation() {
        System.out.println(MAX_WORK_TICKS);
        if(getStackInSlot(0) != null) {
            for (int x = 0; x < getStackInSlot(0).stackSize; x++) {
                if (MAX_WORK_TICKS == 1) {
                    machineMalfunction(0);
                    hasMalfunctioned = true;
                    break;
                }
                return MAX_WORK_TICKS--;
            }
        }
        return MAX_WORK_TICKS;
    }

    public void machineMalfunction(int timer) {
        if(hasMalfunctioned == true) {
            if (timer <= 0) {
                machinePenalties();
                timer = 6000;
            } else {
                if(MAX_WORK_TICKS == MAX_WORK_TICKS_TOTAL) {
                    return;
                }
                MAX_WORK_TICKS++;
                timer--;
            }
        }
    }

    public void machinePenalties() {
        TileEntity tile = worldObj.getTileEntity(getX(), getY(), getZ());
        if(tile instanceof TileMechanicalDesolator) {
            ModRecipes.desolatorOutput = 1;
            POWER_USAGE = POWER_USAGE + 20;
        }

        if(tile instanceof TileMechanicalFurnace) {
            POWER_USAGE = POWER_USAGE + 30;
            MAX_WORK_TICKS = MAX_WORK_TICKS + 10;
        }
    }

    @Override
    public int getSizeInventory() {
        return 0;
    }

    @Override
    public ItemStack getStackInSlot(int p_70301_1_) {
        return getStackInSlot(0);
    }

    @Override
    public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {

    }

    @Override
    public String getInventoryName() {
        return null;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return true;
    }

    @Override
    public int getInventoryStackLimit() {
        return 0;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return true;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return true;
    }
}