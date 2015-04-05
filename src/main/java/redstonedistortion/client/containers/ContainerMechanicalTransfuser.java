package redstonedistortion.client.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import redstonedistortion.bases.containers.ContainerBase;
import redstonedistortion.common.tiles.factory.TileMechanicalTransfuser;

/**
 * Created by UniversalRed on 15-02-20.
 */
public class ContainerMechanicalTransfuser extends ContainerBase<TileMechanicalTransfuser> {

    private int progress;

    public ContainerMechanicalTransfuser(InventoryPlayer inventoryPlayer, TileMechanicalTransfuser tile) {
        super(inventoryPlayer, tile);
        addSlotToContainer(new Slot(tile, 0, 55, 35));
        addSlotToContainer(new Slot(tile, 1, 113, 35));
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
