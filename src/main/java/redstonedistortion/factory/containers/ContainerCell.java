package redstonedistortion.factory.containers;

import net.minecraft.entity.player.InventoryPlayer;
import redstonedistortion.factory.base.TileCell;

/**
 * Created by UniversalRed on 15-02-15.
 */
public class ContainerCell extends ContainerBase
{

    public ContainerCell(InventoryPlayer inventoryPlayer, TileCell tile)
    {
        super(inventoryPlayer, tile);
        //addSlotToContainer(new Slot(tile, 0, 56, 34));
        //addSlotToContainer(new SlotOutput(tile, 1, 116, 34));
        addPlayerInventory(8, 84);
    }
}
