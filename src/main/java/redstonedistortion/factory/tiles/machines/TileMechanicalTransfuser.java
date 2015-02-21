package redstonedistortion.factory.tiles.machines;

import cofh.api.energy.IEnergyContainerItem;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import redstonedistortion.bases.tiles.TileMachine;
import redstonedistortion.core.inventories.CustomInventory;
import redstonedistortion.libs.ModLibs;
import redstonedistortion.utils.ModUtils;
import redstonedistortion.utils.helpers.SideConfiguration;

/**
 * Created by UniversalRed on 15-02-20.
 */
public class TileMechanicalTransfuser extends TileMachine implements ISidedInventory
{
    public SideConfiguration configuration = new SideConfiguration();
    private final CustomInventory inventory = new CustomInventory("mechanicalTransfuser", 2, 64, this);

    public int capacity, maxExtract, maxReceive;

    public TileMechanicalTransfuser(int capacity, int maxExtract, int maxReceive) {
        super(ModLibs.machineCapacity, ModLibs.machineExtract, ModLibs.machineRecieve);
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
    }

    public TileMechanicalTransfuser()
    {

    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if(!worldObj.isRemote)
        {
            return;
        }

        int charge = 5000;

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
        } else if(charge < 0) {
            if(requiredEnergy() < 0)
            {
                ItemStack stack = getStackInSlot(1);
                IEnergyContainerItem containerItem = (IEnergyContainerItem) stack.getItem();
                energy += containerItem.extractEnergy(stack, charge, true);
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
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        inventory.writeNBT(tag);
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
    public ByteBuf writeToByteBuff(ByteBuf buf) {
        buf = configuration.writeToByteBuff(buf);
        return buf;
    }

    @Override
    public ByteBuf readFromByteBuff(ByteBuf buf) {
        buf = configuration.readFromByteBuff(buf);
        return buf;
    }
}
