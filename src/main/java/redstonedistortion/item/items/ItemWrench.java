package redstonedistortion.item.items;

import buildcraft.api.tools.IToolWrench;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import redstonedistortion.item.bases.BaseItems;

/**
 * Created by UniversalRed on 15-02-16.
 */
public class ItemWrench extends BaseItems implements IToolWrench
{

    public ItemWrench(String name)
    {
        super(name);
        this.setMaxStackSize(1);
    }

    public boolean onShiftRotation(Class<? extends Block> cls)
    {
        return true;
    }

    @Override
    public boolean canWrench(EntityPlayer player, int x, int y, int z) {
        return false;
    }

    @Override
    public void wrenchUsed(EntityPlayer player, int x, int y, int z) {
        player.swingItem();
    }
}
