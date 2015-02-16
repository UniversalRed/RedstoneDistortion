package redstonedistortion.api.redstonedistortion.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by UniversalRed on 15-02-15.
 */
public class SlotValid extends Slot
{

    public SlotValid(IInventory inv, int id, int x, int y) {
        super(inv, id, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        return inventory.isItemValidForSlot(this.getSlotIndex(), itemStack);
    }
}

