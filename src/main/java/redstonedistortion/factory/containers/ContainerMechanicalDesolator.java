package redstonedistortion.factory.containers;

import net.minecraft.entity.player.InventoryPlayer;
import redstonedistortion.api.redstonedistortion.slots.SlotValid;
import redstonedistortion.bases.containers.ContainerBase;
import redstonedistortion.bases.slots.SlotOutput;
import redstonedistortion.factory.tiles.machines.TileMechanicalDesolator;

/**
 * Created by UniversalRed on 15-02-21.
 */
public class ContainerMechanicalDesolator extends ContainerBase<TileMechanicalDesolator> {

public ContainerMechanicalDesolator(InventoryPlayer inventoryPlayer, TileMechanicalDesolator tile) {
        super(inventoryPlayer, tile);
        addSlotToContainer(new SlotValid(tile, 0, 55, 35));
        addSlotToContainer(new SlotOutput(tile, 1, 113, 35));
        addPlayerInventory(8, 84);
        }
        }
