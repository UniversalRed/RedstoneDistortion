package redstonedistortion.bases.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import redstonedistortion.libs.ModLibs;

/**
 * Created by UniversalRed on 15-02-20.
 */
public class BlockContainerBase extends BlockContainer
{
    public BlockContainerBase(Material material, String name) {
        super(material);
        setBlockTextureName(ModLibs.texturesPath + name);
        setBlockName(name);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return null;
    }
}
