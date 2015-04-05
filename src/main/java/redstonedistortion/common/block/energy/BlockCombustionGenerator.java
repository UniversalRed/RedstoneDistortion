package redstonedistortion.common.block.energy;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import redstonedistortion.ModRedstoneDistortion;
import redstonedistortion.bases.blocks.BlockContainerBase;
import redstonedistortion.common.tiles.energy.TileCombustionGenerator;
import redstonedistortion.bases.libs.ModLibs;
import redstonedistortion.bases.utils.ModUtils;

/**
 * Created by UniversalRed on 15-03-03.
 */
public class BlockCombustionGenerator extends BlockContainerBase {

    public BlockCombustionGenerator(String name) {
        super(Material.iron, "combustionGenerator");
        setHardness(5F);
        setBlockName(name);

    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
        if (!world.isRemote) {
            entityplayer.openGui(ModRedstoneDistortion.instance, ModLibs.guiCombustionGenerator, world, x, y, z);
        }
        return true;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof TileCombustionGenerator) {
            TileCombustionGenerator equipment = (TileCombustionGenerator) tile;
            equipment.updateEntity();
        }
    }

    @Override
    public void onPostBlockPlaced(World world, int x, int y, int z, int meta) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof TileCombustionGenerator) {
            TileCombustionGenerator equipment = (TileCombustionGenerator) tile;
            equipment.updateEntity();
        }
    }

    @Override
    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack stack) {
        super.onBlockPlacedBy(world, i, j, k, entityliving, stack);

        ForgeDirection orientation = ModUtils.get2dOrientation(entityliving);
        world.setBlockMetadataWithNotify(i, j, k, orientation.getOpposite().ordinal(), 1);

    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        TileCombustionGenerator equipment = (TileCombustionGenerator) world.getTileEntity(x, y, z);
        for (int t = 0; t < 2; t++) {
            ItemStack stack = equipment.getStackInSlot(t);
            if (stack != null) {
                equipment.setInventorySlotContents(t, null);
                ModUtils.dropItemstack(world, x, y, z, stack);
            }
        }
        super.breakBlock(world, x, y, z, block, meta);

    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileCombustionGenerator();
    }
}
