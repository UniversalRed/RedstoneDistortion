package redstonedistortion.utils;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;

import net.minecraftforge.common.util.ForgeDirection;

import buildcraft.api.transport.IPipeTile;


import net.minecraft.util.StatCollector;
import redstonedistortion.utils.enums.EnumPriority;
import redstonedistortion.utils.helpers.Location;
import redstonedistortion.utils.helpers.SideConfiguration;

public class ModUtils
{
    public static String localize(String msg) {
        return ("" + StatCollector.translateToLocal(msg)).trim();
    }

    public static ItemStack outputStack(Location from, ItemStack output, SideConfiguration configuration) {
        for (EnumPriority priority : EnumPriority.values()) {

            //first try to put it intro a pipe
            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                Location location = from.copy();
                if (configuration.getPriority(direction) != priority)
                    continue;
                if (!configuration.canSend(direction))
                    continue;
                location.move(direction);
                TileEntity entity = location.getTileEntity();
                if (entity instanceof IPipeTile) {
                    IPipeTile pipe = (IPipeTile) entity;
                    if (output != null && pipe.isPipeConnected(direction.getOpposite()) && pipe.getPipeType() == IPipeTile.PipeType.ITEM) {
                        int leftOver = pipe.injectItem(output.copy(), true, direction.getOpposite(), null);
                        output.stackSize -= leftOver;
                        if (output.stackSize == 0)
                            output = null;
                    }
                }
            }
            //try to put it intro an inventory
            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                Location location = from.copy();
                if (configuration.getPriority(direction) != priority)
                    continue;
                if (!configuration.canSend(direction))
                    continue;
                location.move(direction);
                TileEntity entity = location.getTileEntity();
                if (entity != null && entity instanceof IInventory) {
                    IInventory outputInventory = (IInventory) entity;
                    for (int slot = 0; slot < outputInventory.getSizeInventory(); slot++) {
                        int stackLimit = outputInventory.getInventoryStackLimit();
                        ItemStack testStack = outputInventory.getStackInSlot(slot);
                        if (output != null &&
                                (testStack == null || (testStack.stackSize + output.stackSize <= testStack.getMaxStackSize() && testStack.getItem() == output.getItem() && testStack.getItemDamage() == output.getItemDamage()))) {
                            ItemStack stack = outputInventory.getStackInSlot(slot);
                            int toMove;
                            if (stack == null) {
                                toMove = stackLimit - 1;
                                stack = output.copy();
                                stack.stackSize = 0;
                            } else {
                                toMove = stackLimit - stack.stackSize;
                            }
                            if (toMove > output.stackSize)
                                toMove = output.stackSize;
                            stack.stackSize += toMove;
                            output.stackSize -= toMove;
                            outputInventory.setInventorySlotContents(slot, stack);
                            outputInventory.markDirty();
                            if (output.stackSize == 0)
                                output = null;
                        }
                    }
                }
            }
        }
        return output;
    }
}
