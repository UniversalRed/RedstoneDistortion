package redstonedistortion.common.factory.containers;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import redstonedistortion.bases.containers.ContainerBase;
import redstonedistortion.common.factory.tiles.machines.TileMechanicalTransfuser;

/**
 * Created by UniversalRed on 15-02-20.
 */
public class ContainerMechanicalTransfuser extends ContainerBase<TileMechanicalTransfuser> {

    public ContainerMechanicalTransfuser(InventoryPlayer inventoryPlayer, TileMechanicalTransfuser tile) {
        super(inventoryPlayer, tile);
        addSlotToContainer(new Slot(tile, 0, 55, 35));
        addSlotToContainer(new Slot(tile, 1, 113, 35));
        addPlayerInventory(8, 84);
    }
}
