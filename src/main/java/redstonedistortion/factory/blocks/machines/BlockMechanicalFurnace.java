package redstonedistortion.factory.blocks.machines;

import cofh.api.energy.IEnergyReceiver;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
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
import redstonedistortion.core.creativetabs.CreativeTabRedstoneDistortion;
import redstonedistortion.factory.tiles.machines.TileMechanicalFurnace;
import redstonedistortion.libs.ModLibs;
import redstonedistortion.utils.ModUtils;

public class BlockMechanicalFurnace extends BlockContainer {

    @SideOnly(Side.CLIENT)
    IIcon textureFrontOn, textureFrontOff, textureTop, textureSide, textureBack, textureBottom;

    public BlockMechanicalFurnace(Material m, String name) {
        super(Material.iron);
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
    public IIcon getIcon(int i, int j) {
        if (j == 0 && i == 3)
            return textureFrontOff;

        if (i == j && i > 1) {
            TileMechanicalFurnace tile = new TileMechanicalFurnace();
            if(tile.isCooking) {
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