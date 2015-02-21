package redstonedistortion.factory.containers;

import net.minecraft.entity.player.InventoryPlayer;
import redstonedistortion.bases.containers.ContainerBase;
import redstonedistortion.factory.tiles.machines.TileMechanicalTransfuser;

/**
 * Created by UniversalRed on 15-02-20.
 */
public class ContainerMechanicalTransfuser extends ContainerBase<TileMechanicalTransfuser> {

    public ContainerMechanicalTransfuser(InventoryPlayer inventoryPlayer, TileMechanicalTransfuser tile) {
        super(inventoryPlayer, tile);
        addPlayerInventory(8, 84);
    }
}
