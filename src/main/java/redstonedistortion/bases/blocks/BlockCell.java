package redstonedistortion.bases.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import redstonedistortion.client.creativetabs.CreativeTabRedstoneDistortion;
import redstonedistortion.bases.libs.ModLibs;

public abstract class BlockCell extends BlockContainer
{
    public BlockCell(String name) {
        super(Material.iron);
        this.setBlockTextureName(ModLibs.texturesPath + name);
        this.setBlockName(name);
        this.setCreativeTab(CreativeTabRedstoneDistortion.RDBlockTab);
    }

    @Override
    public abstract TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_);
}
