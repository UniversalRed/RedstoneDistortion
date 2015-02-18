package redstonedistortion.factory.tiles.cells;


import cofh.api.energy.IEnergyContainerItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import redstonedistortion.core.inventories.CustomInventory;
import redstonedistortion.bases.tiles.TileCell;
import redstonedistortion.libs.ModLibs;
import redstonedistortion.utils.ModUtils;

/**
 * Created by UniversalRed on 15-02-17.
 */
public class TileCellIron extends TileCell implements ISidedInventory
{
    private final CustomInventory inventory = new CustomInventory("cellIron", 2, 64, this);
    public int energy = 250;



    public TileCellIron(int capacity)
    {
        super(ModLibs.cellIronCapacity);
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();
        int charge = 6000;
        if (charge > energy)
            charge = energy;
        if (charge > 0) {
            if (getRequiredEnergy() > 0) {
                ItemStack stack = getStackInSlot(0);
                IEnergyContainerItem containerItem = (IEnergyContainerItem) stack.getItem();
                energy -= containerItem.receiveEnergy(stack, charge, false);
                setInventorySlotContents(0, stack);
            }
        }
    }

    public int getRequiredEnergy() {
        ItemStack stack = getStackInSlot(0);
        if (stack != null && stack.getItem() != null && stack.getItem() instanceof IEnergyContainerItem) {
            IEnergyContainerItem containerItem = (IEnergyContainerItem) stack.getItem();
            return containerItem.getMaxEnergyStored(stack) - containerItem.getEnergyStored(stack);
        }

        return 0;
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

    @Override
    public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
        return ModUtils.createSlotArray(0, 2);
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack p_102007_2_, int p_102007_3_) {
        return slot == 0;
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack p_102008_2_, int p_102008_3_) {
        return slot == 1;
    }
}
