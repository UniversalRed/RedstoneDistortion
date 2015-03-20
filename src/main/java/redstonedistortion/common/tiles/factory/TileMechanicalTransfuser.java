package redstonedistortion.common.tiles.factory;

import buildcraftAdditions.api.configurableOutput.IConfigurableOutput;
import buildcraftAdditions.api.configurableOutput.SideConfiguration;
import cofh.api.energy.IEnergyContainerItem;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import redstonedistortion.bases.tiles.TileMachine;
import redstonedistortion.bases.inventories.CustomInventory;
import redstonedistortion.bases.libs.ModLibs;
import redstonedistortion.bases.utils.ModUtils;

/**
 * Created by UniversalRed on 15-02-20.
 */
public class TileMechanicalTransfuser extends TileMachine implements ISidedInventory, IConfigurableOutput
{
    public SideConfiguration configuration = new SideConfiguration();
    private final CustomInventory inventory = new CustomInventory("mechanicalTransfuser", 2, 64, this);

    public int capacity, maxExtract, maxReceive;
    public boolean isCharging;

    public TileMechanicalTransfuser() {
        super(ModLibs.machineCapacity, ModLibs.machineExtract, ModLibs.machineRecieve);
        isCharging = false;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if(!worldObj.isRemote)
        {
            return;
        }

        int charge = 2000;

        if(charge > energy)
        {
            charge = energy;
        }

        if(charge > 0)
        {
            if(requiredEnergy() > 0)
            {
                ItemStack stack = getStackInSlot(0);
                IEnergyContainerItem containerItem = (IEnergyContainerItem) stack.getItem();
                energy -= containerItem.receiveEnergy(stack, charge, true);
            }
        }
    }

    public int requiredEnergy()
    {
        ItemStack container = getStackInSlot(0);
        if(container.getItem() instanceof IEnergyContainerItem)
        {
            IEnergyContainerItem containerItem = (IEnergyContainerItem) container.getItem();
            return containerItem.getMaxEnergyStored(container) - containerItem.getEnergyStored(container);
        }
        return 0;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        inventory.readNBT(tag);
        tag.getBoolean("isCharging");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        inventory.writeNBT(tag);
        tag.setBoolean("isCharging", isCharging);
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

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return ModUtils.createSlotArray(0, 1);
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
    public void writeToByteBuff(ByteBuf buf) {
        configuration.writeToByteBuff(buf);
    }

    @Override
    public void readFromByteBuff(ByteBuf buf) {
        configuration.readFromByteBuff(buf);
    }
}
