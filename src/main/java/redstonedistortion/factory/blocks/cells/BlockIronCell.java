package redstonedistortion.factory.blocks.cells;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import redstonedistortion.factory.base.BlockCell;
import redstonedistortion.factory.tiles.cells.TileIronCell;
import redstonedistortion.libs.ModLibs;

public class BlockIronCell extends BlockCell
{
    public BlockIronCell(Material m) {
        super(m);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileIronCell(ModLibs.cellIronCapacity);
    }
}
