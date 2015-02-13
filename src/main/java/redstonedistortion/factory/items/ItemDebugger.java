package redstonedistortion.factory.items;

import cofh.api.block.IBlockDebug;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import redstonedistortion.core.creativetabs.CreativeTabRedstoneDistortion;

public class ItemDebugger extends Item implements IBlockDebug
{

    public ItemDebugger(String string)
    {
        super();
        setMaxStackSize(64);
        setCreativeTab(CreativeTabRedstoneDistortion.RDItemTab);
        setUnlocalizedName(string);
    }

    @Override
    public void debugBlock(IBlockAccess world, int x, int y, int z, ForgeDirection side, EntityPlayer player) {
        debugBlock(world, x, y, z, side, player);
    }
}
