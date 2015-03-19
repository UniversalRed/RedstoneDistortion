package redstonedistortion.common.factory.tiles.machines;

import buildcraftAdditions.api.configurableOutput.SideConfiguration;
import buildcraftAdditions.api.networking.ISynchronizedTile;
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
import redstonedistortion.bases.utils.helpers.Location;

/**
 * Created by UniversalRed on 15-03-08.
 */
public class TileAqueousPressurizer extends TileMachine implements ISidedInventory, ISynchronizedTile {

    public CustomInventory inventory = new CustomInventory("aqueousPressurizer", 3, 64, this);
    public SideConfiguration configuration = new SideConfiguration();

    public int capacity = ModLibs.machineCapacity;
    public int maxExtract = ModLibs.machineExtract;
    public int maxReceive = ModLibs.machineRecieve;

    public boolean isCooking;

    public TileAqueousPressurizer(int capacity, int maxExtract, int maxReceive) {
        super(capacity, maxExtract, maxReceive);
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        isCooking = false;
    }

    public TileAqueousPressurizer() {

    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if(worldObj.isRemote) {
            return;
        }

        if(energy < 0) {
            energy = 0;
        }

        if(energy > POWER_USAGE) {
            energy -= POWER_USAGE;
        }

        if(canCook()) {
            if(progress > 1) {
                isCooking = true;
                if(progress == MAX_WORK_TICKS) {


                    progress = 0;
                }

            }
            progress++;
        }
    }

    public void stop() {
        isCooking = false;
        doBlockUpdate();
        progress = 0;
    }

    private void output() {
        if (getStackInSlot(1) == null) {
            return;
        }
        setInventorySlotContents(1, ModUtils.outputStack(new Location(this), getStackInSlot(1), configuration));
    }

    public void doBlockUpdate() {
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    public boolean canCook() {
        ItemStack stack0 = getStackInSlot(0);
        ItemStack stack1 = getStackInSlot(1);
        if (stack0 == null || getResult(stack0) == null)
            return false;
        ItemStack result = getResult(stack0);
        return stack1 == null || (result.getItem() == stack1.getItem() && result.stackSize + stack1.stackSize <= result.getMaxStackSize());
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        inventory.writeNBT(tag);
        tag.setBoolean("isCooking", isCooking);
        tag.setInteger("progress", progress);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        inventory.readNBT(tag);
        isCooking = tag.getBoolean("isCooking");
        progress = tag.getInteger("progress");
    }

    public ItemStack getResult(ItemStack iStack) {
        return getResult(iStack);
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
        return getResult(stack) != null && slot == 0;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return ModUtils.createSlotArray(0, 3);
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
        buf.writeInt(progress);
        configuration.writeToByteBuff(buf);
    }

    @Override
    public void readFromByteBuff(ByteBuf buf) {
        progress = buf.readInt();
        configuration.readFromByteBuff(buf);
    }
}
