package redstonedistortion.common.tiles.energy;

import buildcraftAdditions.api.configurableOutput.SideConfiguration;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import redstonedistortion.bases.inventories.CustomInventory;
import redstonedistortion.bases.libs.ModLibs;
import redstonedistortion.bases.tiles.TileProvider;

/**
 * Created by UniversalRed on 15-03-03.
 */
public class TileCombustionGenerator extends TileProvider implements IInventory
{
    public int capacity = ModLibs.machineCapacity;
    public int maxExtract = ModLibs.machineExtract;

    public CustomInventory inventory = new CustomInventory("combustionGenerator", 1, 64, this);
    public SideConfiguration configuration = new SideConfiguration();

    public TileCombustionGenerator() {
        super(ModLibs.machineCapacity, ModLibs.machineExtract);
    }

    @Override
    public int getSizeInventory() {
        return 64;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory.getStackInSlot(0);
    }

    @Override
    public ItemStack decrStackSize(int slot, int side) {
        return inventory.decrStackSize(0, side);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        return inventory.getStackInSlotOnClosing(0);
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack p_70299_2_) {

    }

    @Override
    public String getInventoryName() {
        return "combustionGenerator";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return true;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack stack) {
        return inventory.isItemValidForSlot(0, stack);
    }
}
