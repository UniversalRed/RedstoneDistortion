package redstonedistortion.factory.tiles.cells;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import redstonedistortion.core.inventories.CustomInventory;
import redstonedistortion.factory.base.TileCell;
import redstonedistortion.libs.ModLibs;

public class TileIronCell extends TileCell implements IInventory
{
    private final CustomInventory inventory = new CustomInventory("TileIronCell", 2, 64, this);

    public TileIronCell(int capacity) {
        super(ModLibs.cellIronCapacity);
    }

    @Override
    public void updateEntity()
    {

    }

    @Override
    public int getSizeInventory() {
        return inventory.getSizeInventory();
    }

    @Override
    public ItemStack getStackInSlot(int p_70301_1_) {
        return inventory.getStackInSlot(p_70301_1_);
    }

    @Override
    public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
        return inventory.decrStackSize(p_70298_1_, p_70298_2_);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
        return inventory.getStackInSlot(p_70304_1_);
    }

    @Override
    public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
        inventory.setInventorySlotContents(p_70299_1_, p_70299_2_);
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
        return 0;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && p_70300_1_.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return inventory.isItemValidForSlot(p_94041_1_, p_94041_2_);
    }
}
