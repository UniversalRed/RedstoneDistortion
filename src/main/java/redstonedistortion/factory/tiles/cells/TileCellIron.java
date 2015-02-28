package redstonedistortion.factory.tiles.cells;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import redstonedistortion.bases.tiles.TileCell;

import redstonedistortion.core.inventories.CustomInventory;
import redstonedistortion.libs.ModLibs;
import redstonedistortion.utils.ModUtils;

/**
 * Created by UniversalRed on 15-02-17.
 */
public class TileCellIron extends TileCell implements ISidedInventory {
    private final CustomInventory inventory = new CustomInventory("Iron Cell", 2, 64, this);
    public EntityPlayer player;

    public int capacity = ModLibs.cellIronCapacity;
    public int energy;
    public int maxRecieve = 1000;
    public int maxExtract = 1000;


    public TileCellIron()
    {

    }

    public TileCellIron(int capacity, int maxReceive, int maxExtract)
    {
        super(capacity, maxReceive, maxExtract);
        this.capacity = capacity;
        this.maxExtract = maxExtract;
        this.maxRecieve = maxReceive;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (worldObj.isRemote)
            return;

        if (capacity == 0)
            return;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
        return ModUtils.createSlotArray(0, 2);
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        return slot == 0 && getStatus(ForgeDirection.getOrientation(side)).canReceive();
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int side) {
        return slot == 1 && getStatus(ForgeDirection.getOrientation(side)).canSend();
    }

    @Override
    public int getSizeInventory() {
        return inventory.getSizeInventory();
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory.getStackInSlot(slot);
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        return inventory.decrStackSize(slot, amount);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        return inventory.getStackInSlot(slot);
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        inventory.setInventorySlotContents(slot, stack);
    }

    @Override
    public String getInventoryName() {
        return inventory.getInventoryName();
    }

    @Override
    public boolean hasCustomInventoryName() {
        return inventory.hasCustomInventoryName();
    }

    @Override
    public int getInventoryStackLimit() {
        return inventory.getInventoryStackLimit();
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return slot == 0;
    }

}
