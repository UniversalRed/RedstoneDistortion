package redstonedistortion.factory.base;

import cofh.api.block.IBlockInfo;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

import cofh.api.energy.IEnergyReceiver;
import redstonedistortion.libs.ModLibs;

import java.util.List;

public class TileMachine extends TileBase implements IEnergyReceiver, ISidedInventory {
    protected double rfStored = 0;

    public int energy;
    public int maxEnergy;

    protected static int POWER_USAGE = 10;

    protected int currentWorkTime;
    public static int MAX_WORK_TICKS = 15;

    protected TileMachine(int maxEnergy) {
        this.maxEnergy = maxEnergy;
        currentWorkTime = 0;
    }

    public int getCookProgressScaled(int i) {
        double deling = (double) currentWorkTime / (double) MAX_WORK_TICKS;
        double vermenigvuldiging = deling * i;
        int totaal = (int) vermenigvuldiging;
        return totaal;
    }

    public int getCurrentWorkTime() {
        return currentWorkTime;
    }

    public int getMaxWorkTime() {
        return MAX_WORK_TICKS;
    }

    public void setCurrentWorkTime(int currentWorkTime) {
        this.currentWorkTime = currentWorkTime;
    }

    public void setMaxWorkTime(int maxWorkTime) {
        this.MAX_WORK_TICKS = maxWorkTime;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        energy = tag.getInteger("energy");
        readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        tag.setInteger("energy", energy);
        writeToNBT(tag);
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        if (energy >= maxEnergy)
            return 0;
        int energyRecieved = maxReceive;
        if (energyRecieved > maxEnergy - energy)
            energyRecieved = maxEnergy - energy;
        if (!simulate)
            energy += energyRecieved;
        return energyRecieved;
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
}
