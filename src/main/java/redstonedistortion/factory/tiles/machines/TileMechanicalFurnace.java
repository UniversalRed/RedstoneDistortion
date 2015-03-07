package redstonedistortion.factory.tiles.machines;

import buildcraftAdditions.api.configurableOutput.IConfigurableOutput;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import redstonedistortion.bases.tiles.TileMachine;
import redstonedistortion.core.inventories.CustomInventory;
import redstonedistortion.libs.ModLibs;
import redstonedistortion.utils.ModUtils;
import redstonedistortion.utils.helpers.Location;
import redstonedistortion.utils.helpers.SideConfiguration;

public class TileMechanicalFurnace extends TileMachine implements ISidedInventory, IConfigurableOutput {
    private final CustomInventory inventory = new CustomInventory("MechanicalFurnace", 2, 64, this);

    public boolean isCooking = true;
    public SideConfiguration configuration = new SideConfiguration();
    public int maxExtract = ModLibs.machineExtract;
    public int maxReceive = ModLibs.machineRecieve;

    public TileMechanicalFurnace() {

    }

    public TileMechanicalFurnace(int capacity, int maxExtract, int maxReceive) {
        super(ModLibs.machineCapacity, ModLibs.machineExtract, ModLibs.machineRecieve);
        progress = 0;
        isCooking = false;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        inventory.readNBT(tag);
        progress = tag.getInteger("progress");
        isCooking = tag.getBoolean("isCooking");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        inventory.writeNBT(tag);
        tag.setFloat("progress", progress);
        tag.setBoolean("isCooking", isCooking);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (worldObj.isRemote)
            return;

        if (canCook()) {
            if (energy > POWER_USAGE) {
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
                    if (getStackInSlot(0).stackSize <= 1) {
                        setInventorySlotContents(0, null);
                    } else {
                        getStackInSlot(0).stackSize--;
                        //machineContinuation();
                    }
                    progress = 0;
                }
                //regainTicks();
            }
            progress++;
        } else {
            stop();
        }
        output();
    }

    public void regainTicks() {
        int timer = 300;

        if (isCooking == true) {
            if (MAX_WORK_TICKS < MAX_WORK_TICKS_TOTAL) {
                while (MAX_WORK_TICKS < 30 && MAX_WORK_TICKS != 1) {
                    if (MAX_WORK_TICKS == MAX_WORK_TICKS_TOTAL) {
                        break;
                    }
                    // Stupid timer doesn't work ;(
                    if (timer <= 0) {
                        timer = 300;
                        MAX_WORK_TICKS++;
                    }
                    timer--;
                }
            }
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
    public ByteBuf writeToByteBuff(ByteBuf buf) {
        buf.writeFloat(progress);
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
