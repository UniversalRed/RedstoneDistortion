package redstonedistortion.bases.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by UniversalRed on 15-02-20.
 */
public abstract class BlockContainerBase extends BlockContainer
{
    public BlockContainerBase(Material material, String name) {
        super(material);
        setBlockName(name);
    }

    @Override
    public abstract TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_);
}
