package redstonedistortion.block.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import redstonedistortion.bases.blocks.BlockContainerBase;
import redstonedistortion.core.creativetabs.CreativeTabRedstoneDistortion;

/**
 * Created by UniversalRed on 15-02-21.
 */
public class BlockSolarPanelTier1 extends BlockContainerBase
{
    public BlockSolarPanelTier1(Material material, String name)
    {
        super(Material.iron, name);
        this.setHardness(10f);
        this.setResistance(20f);
        this.setCreativeTab(CreativeTabRedstoneDistortion.RDBlockTab);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int par2)
    {
        return new TileSolarPanelTier1();
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
}
