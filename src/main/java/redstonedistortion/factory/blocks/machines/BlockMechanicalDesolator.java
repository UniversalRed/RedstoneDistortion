package redstonedistortion.factory.blocks.machines;

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
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import redstonedistortion.ModRedstoneDistortion;
import redstonedistortion.bases.blocks.BlockContainerBase;
import redstonedistortion.core.creativetabs.CreativeTabRedstoneDistortion;
import redstonedistortion.factory.tiles.machines.TileMechanicalDesolator;
import redstonedistortion.libs.ModLibs;
import redstonedistortion.utils.ModUtils;

/**
 * Created by UniversalRed on 15-02-21.
 */
public class BlockMechanicalDesolator extends BlockContainerBase
{
    IIcon textureFront;
    IIcon textureTop;
    IIcon textureSide;
    IIcon textureBack;
    IIcon textureBottom;

    public BlockMechanicalDesolator(Material material, String name) {
        super(material.iron, "mechanicalDesolator");
        setCreativeTab(CreativeTabRedstoneDistortion.RDBlockTab);
        setHardness(3.0F);
        setBlockName(name);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
        if (!world.isRemote)
            entityplayer.openGui(ModRedstoneDistortion.instance, ModLibs.guiMechanicalTransfuser, world, x, y, z);
        return true;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof TileMechanicalDesolator) {
            TileMechanicalDesolator charger = (TileMechanicalDesolator) tile;
            charger.updateEntity();
        }
    }

    @Override
    public void onPostBlockPlaced(World world, int x, int y, int z, int meta) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof TileMechanicalDesolator) {
            TileMechanicalDesolator furnace = (TileMechanicalDesolator) tile;
            furnace.updateEntity();
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
        TileMechanicalDesolator charger = (TileMechanicalDesolator) world.getTileEntity(x, y, z);
        for (int t = 0; t < 2; t++) {
            ItemStack stack = charger.getStackInSlot(t);
            if (stack != null) {
                charger.setInventorySlotContents(t, null);
                ModUtils.dropItemstack(world, x, y, z, stack);
            }
        }
        super.breakBlock(world, x, y, z, block, meta);

    }

    @Override
    public IIcon getIcon(int i, int j) {
        if (j == 0 && i == 3)
            return textureFront;

        if (i == j && i > 1)
            return textureFront;

        switch (i) {
            case 0:
                return textureBottom;
            case 1:
                return textureTop;
            default:
                return textureSide;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        textureFront = par1IconRegister.registerIcon(ModLibs.texturesPath + "");
        textureSide = par1IconRegister.registerIcon(ModLibs.texturesPath + "");
        textureTop = par1IconRegister.registerIcon(ModLibs.texturesPath + "");
        textureBack = par1IconRegister.registerIcon(ModLibs.texturesPath + "");
        textureBottom = par1IconRegister.registerIcon(ModLibs.texturesPath + "");
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileMechanicalDesolator(ModLibs.machineCapacity, ModLibs.machineExtract, ModLibs.machineRecieve);
    }
}