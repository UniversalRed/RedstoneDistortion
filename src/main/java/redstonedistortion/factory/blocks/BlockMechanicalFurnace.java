package redstonedistortion.factory.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import redstonedistortion.factory.tiles.TileMechanicalFurnace;

public class BlockMechanicalFurnace extends BlockContainer
{

    public BlockMechanicalFurnace(Material p_i45386_1_) {
        super(p_i45386_1_.iron);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileMechanicalFurnace(32000);
    }
}
