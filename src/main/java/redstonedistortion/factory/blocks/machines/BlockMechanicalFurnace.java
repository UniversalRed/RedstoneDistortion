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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import redstonedistortion.ModRedstoneDistortion;
import redstonedistortion.bases.blocks.BlockContainerBase;
import redstonedistortion.core.creativetabs.CreativeTabRedstoneDistortion;
import redstonedistortion.factory.tiles.machines.TileMechanicalFurnace;
import redstonedistortion.libs.ModLibs;
import redstonedistortion.utils.ModUtils;

public class BlockMechanicalFurnace extends BlockContainerBase {

    @SideOnly(Side.CLIENT)
    IIcon textureFrontOn, textureFrontOff, textureTop, textureSide, textureBack, textureBottom;

    public TileMechanicalFurnace tile;

    public BlockMechanicalFurnace(String name) {
        super(Material.iron, name);
        setCreativeTab(CreativeTabRedstoneDistortion.RDBlockTab);
        setHardness(3.0F);
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
        if (tile instanceof TileMechanicalFurnace) {
            TileMechanicalFurnace furnace = (TileMechanicalFurnace) tile;
            furnace.updateEntity();
        }
    }

    @Override
    public void onPostBlockPlaced(World world, int x, int y, int z, int meta) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof TileMechanicalFurnace) {
            TileMechanicalFurnace furnace = (TileMechanicalFurnace) tile;
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
        TileMechanicalFurnace furnace = (TileMechanicalFurnace) world.getTileEntity(x, y, z);
        for (int t = 0; t < 2; t++) {
            ItemStack stack = furnace.getStackInSlot(t);
            if (stack != null) {
                furnace.setInventorySlotContents(t, null);
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
            TileMechanicalFurnace tile = (TileMechanicalFurnace) access.getTileEntity(x, y, z);
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
        textureFrontOn = par1IconRegister.registerIcon(ModLibs.texturesPath + "poweredfurnace_on");

        textureFrontOff = par1IconRegister.registerIcon(ModLibs.texturesPath + "poweredfurnace_off");
        textureSide = par1IconRegister.registerIcon(ModLibs.texturesPath + "machineSide");
        textureTop = par1IconRegister.registerIcon(ModLibs.texturesPath + "machineTop");
        textureBack = par1IconRegister.registerIcon(ModLibs.texturesPath + "machineSide");
        textureBottom = par1IconRegister.registerIcon(ModLibs.texturesPath + "machineTop");
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileMechanicalFurnace(ModLibs.machineCapacity, ModLibs.machineExtract, ModLibs.machineRecieve);
    }
}