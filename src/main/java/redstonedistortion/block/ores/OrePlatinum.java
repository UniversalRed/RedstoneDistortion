package redstonedistortion.block.ores;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import redstonedistortion.block.ModBlocks;
import redstonedistortion.core.creativetabs.CreativeTabRedstoneDistortion;

public class OrePlatinum extends Block
{
    public OrePlatinum(Material m)
    {
        super(Material.rock);
        setCreativeTab(CreativeTabRedstoneDistortion.RDBlockTab);
        setHardness(20.0F);
        setHarvestLevel("pickaxe", 2);
    }


    public Block idDropped(int par1, int par2)
    {
        return ModBlocks.orePlatinum;
    }
}
