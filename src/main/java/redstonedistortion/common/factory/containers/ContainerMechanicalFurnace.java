package redstonedistortion.common.factory.containers;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.SlotFurnace;
import redstonedistortion.api.redstonedistortion.slots.SlotValid;
import redstonedistortion.bases.containers.ContainerBase;
import redstonedistortion.common.factory.tiles.machines.TileMechanicalFurnace;

public class ContainerMechanicalFurnace extends ContainerBase<TileMechanicalFurnace> {

    public ContainerMechanicalFurnace(InventoryPlayer inventoryPlayer, TileMechanicalFurnace tile) {
        super(inventoryPlayer, tile);
        addSlotToContainer(new SlotValid(tile, 0, 55, 35));
        addSlotToContainer(new SlotFurnace(inventoryPlayer.player, tile, 1, 113, 35));
        addPlayerInventory(8, 84);
    }
}