package redstonedistortion.factory.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by UniversalRed on 15-02-15.
 */
public class ContainerMachine extends Container
{
    protected int workTime;
    protected int maxWorkTime;
    private TileMachine teMachine;

    public ContainerMachine(TileMachine teMachine) {
        this.teMachine = teMachine;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return teMachine.isUseableByPlayer(player);
    }

    public TileMachine getTileEntity() {
        return teMachine;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotId) {
        Slot slot = getSlot(slotId);

        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            ItemStack result = stack.copy();

            if (slotId >= 36) {
                if (!mergeItemStack(stack, 0, 36, false)) { return null; }
                if (!mergeItemStack(stack, 2, 36, false)) { return null; }
            } else {
                int inventorySize = teMachine.getSizeInventory();
                boolean[] isOk = new boolean[inventorySize];
                for (int i = 0; i < inventorySize; i++) {

                }

                boolean isOkInList = false;
                for (boolean ok : isOk) {
                    if (ok) isOkInList = true;
                }

                if (!isOkInList) return null;
            }

            if (stack.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            slot.onPickupFromSlot(player, stack);

            return result;
        }

        return null;
    }

    @Override
    public void addCraftingToCrafters(ICrafting icrafting) {
        icrafting.sendProgressBarUpdate(this, 0, teMachine.getCurrentWorkTime());
        icrafting.sendProgressBarUpdate(this, 1, teMachine.getMaxWorkTime());
        super.addCraftingToCrafters(icrafting);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < crafters.size(); i++) {
            ICrafting iCrafting = (ICrafting) crafters.get(i);

            if (workTime != teMachine.getCurrentWorkTime()) {
                iCrafting.sendProgressBarUpdate(this, 0, teMachine.getCurrentWorkTime());
            }
            if (maxWorkTime != teMachine.getMaxWorkTime()) {
                iCrafting.sendProgressBarUpdate(this, 1, teMachine.getMaxWorkTime());
            }

            workTime = teMachine.getCurrentWorkTime();
            maxWorkTime = teMachine.getMaxWorkTime();
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            teMachine.setCurrentWorkTime(par2);
        }
        if (par1 == 1) {
            teMachine.setMaxWorkTime(par2);
        }
    }
}
