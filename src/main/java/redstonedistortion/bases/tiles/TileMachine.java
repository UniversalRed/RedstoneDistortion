package redstonedistortion.bases.tiles;

import cofh.api.energy.IEnergyProvider;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import net.minecraftforge.common.util.ForgeDirection;

import cofh.api.energy.IEnergyReceiver;
import redstonedistortion.utils.enums.EnumSideStatus;
import redstonedistortion.utils.helpers.SideConfiguration;

public class TileMachine extends TileBase implements IEnergyProvider, IEnergyReceiver, ISidedInventory {

    private SideConfiguration configuration = new SideConfiguration();

    // Basic Machine Requirements
    public int capacity;
    public int energy;
    public int maxReceive;
    public int maxExtract;
    public static int progress;

    protected static int POWER_USAGE = 20;

    protected int currentWorkTime;
    public static int MAX_WORK_TICKS = 15;

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
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        this.maxExtract = maxExtract;

        if (!configuration.canSend(from))
            return 0;
        int extracted = maxExtract;
        if (extracted > energy)
            extracted = energy;
        if (extracted > maxExtract)
            extracted = maxExtract;
        if (!simulate) {
            energy += extracted;
        }
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
    public boolean canConnectEnergy(ForgeDirection from) {
        return true;
    }
    @Override
    public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
        return false;
    }

    @Override
    public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
        return false;
    }

    @Override
    public int getSizeInventory() {
        return 0;
    }

    @Override
    public ItemStack getStackInSlot(int p_70301_1_) {
        return null;
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
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 0;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return false;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return false;
    }

    public EnumSideStatus getStatus(ForgeDirection side) {
        return configuration.getStatus(side);
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