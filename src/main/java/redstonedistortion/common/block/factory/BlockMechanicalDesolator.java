package redstonedistortion.common.block.factory;

import buildcraft.api.tools.IToolWrench;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import redstonedistortion.ModRedstoneDistortion;
import redstonedistortion.bases.blocks.BlockContainerBase;
import redstonedistortion.client.creativetabs.CreativeTabRedstoneDistortion;
import redstonedistortion.common.tiles.factory.TileMechanicalDesolator;
import redstonedistortion.bases.libs.ModLibs;
import redstonedistortion.bases.utils.ModUtils;

/**
 * Created by UniversalRed on 15-02-21.
 */
public class BlockMechanicalDesolator extends BlockContainerBase
{
    IIcon textureFrontOff, textureFrontOn;
    IIcon textureTop;
    IIcon textureSide;
    IIcon textureBack;
    IIcon textureBottom;

    public BlockMechanicalDesolator(String name) {
        super(Material.iron, "mechanicalDesolator");
        setCreativeTab(CreativeTabRedstoneDistortion.RDBlockTab);
        setHardness(5.0F);
        setBlockName(name);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (!world.isRemote)
            player.openGui(ModRedstoneDistortion.instance, ModLibs.guiMechanicalTransfuser, world, x, y, z);

        Item equipped = player.getCurrentEquippedItem() != null ? player.getCurrentEquippedItem().getItem() : null;
        if (equipped instanceof IToolWrench && ((IToolWrench) equipped).canWrench(player, x, y, z)) {

            ((IToolWrench) equipped).wrenchUsed(player, x, y, z);
            return true;
        }

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
            return textureFrontOff;

        if (i == j && i > 1) {
            TileMechanicalDesolator tile = new TileMechanicalDesolator();
            if(!tile.isCooking)
            {
                return textureFrontOn;
            }
            return textureFrontOff;
        }

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
        textureFrontOn = par1IconRegister.registerIcon(ModLibs.texturesPath + "grinder_on");

        textureFrontOff = par1IconRegister.registerIcon(ModLibs.texturesPath + "grinder_off");
        textureSide = par1IconRegister.registerIcon(ModLibs.texturesPath + "machineSide");
        textureTop = par1IconRegister.registerIcon(ModLibs.texturesPath + "machineTop");
        textureBack = par1IconRegister.registerIcon(ModLibs.texturesPath + "machineSide");
        textureBottom = par1IconRegister.registerIcon(ModLibs.texturesPath + "machineTop");
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileMechanicalDesolator();
    }
}
