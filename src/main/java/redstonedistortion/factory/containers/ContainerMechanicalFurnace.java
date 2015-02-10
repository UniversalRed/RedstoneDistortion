package redstonedistortion.factory.containers;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import redstonedistortion.factory.tiles.*;
import redstonedistortion.utils.helpers.SlotOutput;

public class ContainerMechanicalFurnace extends ContainerBase
{

    public ContainerMechanicalFurnace(InventoryPlayer inventoryPlayer, TileMechanicalFurnace tile) {
        super(inventoryPlayer, tile);
        addSlotToContainer(new Slot(tile, 0, 56, 34));
        addSlotToContainer(new SlotOutput(tile, 1, 116, 34));
        addPlayerInventory(8, 84);    }
}
