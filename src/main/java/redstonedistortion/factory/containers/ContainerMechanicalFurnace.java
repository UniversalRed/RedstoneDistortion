package redstonedistortion.factory.containers;

import net.minecraft.entity.player.InventoryPlayer;
import redstonedistortion.api.redstonedistortion.slots.SlotValid;
import redstonedistortion.bases.containers.ContainerBase;
import redstonedistortion.bases.slots.SlotOutput;
import redstonedistortion.factory.tiles.machines.TileMechanicalFurnace;
import redstonedistortion.factory.tiles.machines.TileMechanicalTransfuser;

public class ContainerMechanicalFurnace extends ContainerBase<TileMechanicalFurnace> {

    public ContainerMechanicalFurnace(InventoryPlayer inventoryPlayer, TileMechanicalFurnace tile) {
        super(inventoryPlayer, tile);
        addSlotToContainer(new SlotValid(tile, 0, 55, 35));
        addSlotToContainer(new SlotOutput(tile, 1, 113, 35));
        addPlayerInventory(8, 84);
    }
}
