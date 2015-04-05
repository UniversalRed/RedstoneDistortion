package redstonedistortion.client.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import redstonedistortion.api.redstonedistortion.slots.SlotValid;
import redstonedistortion.bases.containers.ContainerBase;
import redstonedistortion.bases.slots.SlotOutput;
import redstonedistortion.common.tiles.factory.TileMechanicalDesolator;

/**
 * Created by UniversalRed on 15-02-21.
 */
public class ContainerMechanicalDesolator extends ContainerBase<TileMechanicalDesolator> {

    private int progress;

    public ContainerMechanicalDesolator(InventoryPlayer inventoryPlayer, TileMechanicalDesolator tile) {
        super(inventoryPlayer, tile);
        addSlotToContainer(new SlotValid(tile, 0, 55, 35));
        addSlotToContainer(new SlotOutput(tile, 1, 113, 35));
        addPlayerInventory(8, 84);
    }


    @Override
    public void addCraftingToCrafters(ICrafting crafting) {
        super.addCraftingToCrafters(crafting);
        crafting.sendProgressBarUpdate(this, 0, inventory.progress);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        if (progress != inventory.progress && crafters != null)
            for (Object o : crafters)
                if (o != null && o instanceof ICrafting)
                    ((ICrafting) o).sendProgressBarUpdate(this, 0, inventory.progress);
        progress = inventory.progress;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int value) {
        super.updateProgressBar(id, value);
        switch (id) {
            case 0:
                inventory.progress = value;
                break;
            default:
                break;
        }
    }
}
