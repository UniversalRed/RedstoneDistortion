package redstonedistortion.factory.base;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import redstonedistortion.core.creativetabs.CreativeTabRedstoneDistortion;
import redstonedistortion.libs.ModLibs;

public class BlockCell extends BlockContainer
{
    public BlockCell(Material m, String name) {
        super(Material.iron);
        this.setBlockTextureName(ModLibs.texturesPath + name);
        this.setBlockName(name);
        this.setCreativeTab(CreativeTabRedstoneDistortion.RDBlockTab);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return null;
    }
}
