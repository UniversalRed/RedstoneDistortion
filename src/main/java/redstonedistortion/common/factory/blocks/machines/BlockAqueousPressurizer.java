package redstonedistortion.common.factory.blocks.machines;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import redstonedistortion.ModRedstoneDistortion;
import redstonedistortion.bases.blocks.BlockContainerBase;
import redstonedistortion.common.factory.tiles.machines.TileAqueousPressurizer;
import redstonedistortion.bases.libs.ModLibs;
import redstonedistortion.bases.utils.ModUtils;

/**
 * Created by UniversalRed on 15-03-08.
 */
public class BlockAqueousPressurizer extends BlockContainerBase {

    @SideOnly(Side.CLIENT)
    IIcon textureFrontOn, textureFrontOff, textureTop, textureSide, textureBack, textureBottom;

    public BlockAqueousPressurizer(String name) {
        super(Material.iron, name);
        setBlockName(name);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
        if (!world.isRemote)
            entityplayer.openGui(ModRedstoneDistortion.instance, ModLibs.guiMechanicalFurnace, world, x, y, z);
        return true;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof TileAqueousPressurizer) {
            TileAqueousPressurizer pressurizer = (TileAqueousPressurizer) tile;
            pressurizer.updateEntity();
        }
    }

    @Override
    public void onPostBlockPlaced(World world, int x, int y, int z, int meta) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof TileAqueousPressurizer) {
            TileAqueousPressurizer pressurizer = (TileAqueousPressurizer) tile;
            pressurizer.updateEntity();
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
        TileAqueousPressurizer pressurizer = (TileAqueousPressurizer) world.getTileEntity(x, y, z);
        for (int t = 0; t < 2; t++) {
            ItemStack stack = pressurizer.getStackInSlot(t);
            if (stack != null) {
                pressurizer.setInventorySlotContents(t, null);
                ModUtils.dropItemstack(world, x, y, z, stack);
            }
        }
        super.breakBlock(world, x, y, z, block, meta);

    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess access, int x, int y, int z, int side) {
        int meta = access.getBlockMetadata(x, y, z);
        if (meta == 0 && side == 3)
            return textureFrontOff;

        if (side == meta && meta > 1) {
            TileAqueousPressurizer tile = (TileAqueousPressurizer) access.getTileEntity(x, y, z);
            if (!tile.isCooking) {
                return textureFrontOn;
            }
            return textureFrontOff;
        }

        switch (side) {
            case 0:
                return textureBottom;
            case 1:
                return textureTop;
            case 5:
                return textureBack;
            default:
                return textureSide;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {

        if (meta == 0 && side == 3)
            return textureFrontOff;

        if (side == meta && meta > 1) {
            return textureFrontOff;
        }

        switch (side) {
            case 0:
                return textureBottom;
            case 1:
                return textureTop;
        }
        return textureSide;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        textureFrontOn = par1IconRegister.registerIcon(ModLibs.texturesPath + "");

        textureFrontOff = par1IconRegister.registerIcon(ModLibs.texturesPath + "");
        textureSide = par1IconRegister.registerIcon(ModLibs.texturesPath + "");
        textureTop = par1IconRegister.registerIcon(ModLibs.texturesPath + "");
        textureBack = par1IconRegister.registerIcon(ModLibs.texturesPath + "");
        textureBottom = par1IconRegister.registerIcon(ModLibs.texturesPath + "");
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileAqueousPressurizer(ModLibs.machineCapacity, ModLibs.machineExtract, ModLibs.machineRecieve);
    }
}
