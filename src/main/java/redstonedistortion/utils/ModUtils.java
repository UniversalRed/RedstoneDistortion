package redstonedistortion.utils;

import buildcraftAdditions.api.configurableOutput.EnumPriority;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;

import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import buildcraft.api.transport.IPipeTile;

import org.lwjgl.opengl.GL11;
import redstonedistortion.utils.helpers.Location;
import redstonedistortion.utils.helpers.SideConfiguration;
/**
 * Copyright (c) 2014, AEnterprise
 * http://buildcraftadditions.wordpress.com/
 * Buildcraft Additions is distributed under the terms of GNU GPL v3.0
 * Please check the contents of the license located in
 * http://buildcraftadditions.wordpress.com/wiki/licensing-stuff/
 */
public class ModUtils
{
    public static ForgeDirection get2dOrientation(EntityLivingBase entityliving) {
        ForgeDirection[] orientationTable = {ForgeDirection.SOUTH,
                ForgeDirection.WEST, ForgeDirection.NORTH, ForgeDirection.EAST};
        int orientationIndex = MathHelper.floor_double((entityliving.rotationYaw + 45.0) / 90.0) & 3;
        return orientationTable[orientationIndex];
    }

    public static ForgeDirection get3dOrientation(EntityLivingBase entity) {
        if (entity.rotationPitch < -45.5F)
            return ForgeDirection.UP;
        else if (entity.rotationPitch > 45.5F)
            return ForgeDirection.DOWN;
        return get2dOrientation(entity);
    }

    public static int[] createSlotArray(int first, int count) {
        int[] slots = new int[count];
        for (int k = first; k < first + count; k++) {
            slots[k - first] = k;
        }
        return slots;
    }

    public static void dropItemstack(World world, int x, int y, int z, ItemStack stack) {
        float f1 = 0.7F;
        double d = (world.rand.nextFloat() * f1) + (1.0F - f1) * 0.5D;
        double d1 = (world.rand.nextFloat() * f1) + (1.0F - f1) * 0.5D;
        double d2 = (world.rand.nextFloat() * f1) + (1.0F - f1) * 0.5D;
        EntityItem itemToDrop = new EntityItem(world, x + d, y + d1, z + d2, stack);
        itemToDrop.delayBeforeCanPickup = 10;
        if (!world.isRemote)
            world.spawnEntityInWorld(itemToDrop);
    }

    public static String localize(String msg) {
        return ("" + StatCollector.translateToLocal(msg)).trim();
    }

    public static ItemStack outputStack(Location from, ItemStack output, SideConfiguration configuration) {
        for (EnumPriority priority : EnumPriority.values()) {

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

    public static void setGLColorFromInt(int color) {
        float red = (color >> 16 & 255) / 255.0F;
        float green = (color >> 8 & 255) / 255.0F;
        float blue = (color & 255) / 255.0F;
        GL11.glColor4f(red, green, blue, 1.0F);
    }
}
