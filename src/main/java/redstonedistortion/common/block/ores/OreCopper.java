package redstonedistortion.common.block.ores;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import redstonedistortion.common.block.ModBlocks;
import redstonedistortion.client.creativetabs.CreativeTabRedstoneDistortion;

public class OreCopper extends Block
{
    public OreCopper(Material m)
    {
        super(Material.rock);
        setCreativeTab(CreativeTabRedstoneDistortion.RDBlockTab);
        setHardness(20.0F);
        setHarvestLevel("pickaxe", 1);
    }


    public Block idDropped(int par1, int par2)
    {
        return ModBlocks.oreCopper;
    }
}
