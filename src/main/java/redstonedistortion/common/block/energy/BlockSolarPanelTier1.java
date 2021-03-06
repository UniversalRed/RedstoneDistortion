package redstonedistortion.common.block.energy;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import redstonedistortion.bases.blocks.BlockContainerBase;
import redstonedistortion.client.creativetabs.CreativeTabRedstoneDistortion;
import redstonedistortion.common.tiles.energy.TileSolarPanelTier1;

/**
 * Created by UniversalRed on 15-02-21.
 */
public class BlockSolarPanelTier1 extends BlockContainerBase
{
    public BlockSolarPanelTier1(String name)
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
}
