package redstonedistortion.factory.tiles;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import redstonedistortion.core.inventories.CustomInventory;
import redstonedistortion.bases.tiles.TileMachine;
import redstonedistortion.utils.ModUtils;
import redstonedistortion.utils.enums.EnumSideStatus;
import redstonedistortion.utils.helpers.SideConfiguration;

public class TileMechanicalFurnace extends TileMachine implements ISidedInventory
{
    private final CustomInventory inventory = new CustomInventory("MechanicalFurnace", 2, 64, this);

    public int progress;
    public boolean isCooking;
    private SideConfiguration configuration = new SideConfiguration();

    public TileMechanicalFurnace()
    {

    }

    public TileMechanicalFurnace(int maxEnergy) {
        super(32000);
        progress = 0;
        isCooking = false;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        inventory.readNBT(tag);
        progress = tag.getInteger("progress");
        isCooking = tag.getBoolean("isCooking");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        inventory.writeNBT(tag);
        tag.setInteger("progress", progress);
        tag.setBoolean("isCooking", isCooking);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (worldObj.isRemote)
            return;

        if (canCook()) {
            if (!isCooking) {

            }

            if (energy > POWER_USAGE)
            {
                energy -= POWER_USAGE;
            } else {
                return;
            }

            if (progress > 0) {
                isCooking = true;
                if (progress == MAX_WORK_TICKS) {
                    ItemStack inputStack = getStackInSlot(0);
                    ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(inputStack);
                    if (getStackInSlot(1) == null) {
                        setInventorySlotContents(1, result.copy());
                    } else {
                        getStackInSlot(1).stackSize += result.stackSize;
                    }
                    if (getStackInSlot(0).stackSize <= 1)
                        setInventorySlotContents(0, null);
                    else
                        getStackInSlot(0).stackSize--;
                    progress = 0;
                }
            }
                progress++;
        } else {
            stop();
        }
    }

    public void stop() {
        isCooking = false;
        doBlockUpdate();
        progress = 0;
    }

    public void doBlockUpdate() {
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    public boolean canCook()
    {
        ItemStack stack0 = getStackInSlot(0);
        ItemStack stack1 = getStackInSlot(1);
        if (stack0 == null || getResult(stack0) == null)
            return false;
        ItemStack result = getResult(stack0);
        return stack1 == null || (result.getItem() == stack1.getItem() && result.stackSize + stack1.stackSize <= result.getMaxStackSize());
    }

    public ItemStack getResult(ItemStack iStack) {
        return FurnaceRecipes.smelting().getSmeltingResult(iStack);
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

    public EnumSideStatus getStatus(ForgeDirection side) {
        return configuration.getStatus(side);
    }

    @Override
    public ByteBuf writeToByteBuff(ByteBuf buf) {
        buf.writeInt(progress);
        buf = configuration.writeToByteBuff(buf);
        return buf;
    }

    @Override
    public ByteBuf readFromByteBuff(ByteBuf buf) {
        progress = buf.readInt();
        buf = configuration.readFromByteBuff(buf);
        return buf;
    }
}
