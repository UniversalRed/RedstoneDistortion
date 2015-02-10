package cofh.lib.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraftforge.common.util.ForgeDirection;

public class InventoryManager {

	public static IInventoryManager create(Object inventory, ForgeDirection targetSide) {

		if (inventory instanceof ISidedInventory) {
			return new InventoryManagerSided((ISidedInventory) inventory, targetSide);
		} else if (inventory instanceof IInventory) {
			return new InventoryManagerStandard((IInventory) inventory, targetSide);
		} else {
			return null;
		}
	}

}
