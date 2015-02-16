package redstonedistortion.factory.blocks.cells;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
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
import redstonedistortion.factory.base.BlockCell;
import redstonedistortion.factory.tiles.TileMechanicalFurnace;
import redstonedistortion.factory.tiles.cells.TileIronCell;
import redstonedistortion.libs.ModLibs;
import redstonedistortion.utils.ModUtils;

public class BlockIronCell extends BlockCell
{
    IIcon textureFront;
    IIcon textureTop;
    IIcon textureSide;
    IIcon textureBack;
    IIcon textureBottom;

    public BlockIronCell(Material m) {
        super(m, "cellIron");
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileIronCell(ModLibs.cellIronCapacity);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {

        if (player.isSneaking()) return false;

        if (!world.isRemote) FMLNetworkHandler.openGui(player, ModRedstoneDistortion.instance, ModLibs.guiMechanicalFurnace, world, x, y, z);

        return true;
    }

    @Override
    public IIcon getIcon(int i, int j)
    {
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
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        textureFront = par1IconRegister.registerIcon(ModLibs.texturesPath + "cellIron");
        textureSide = par1IconRegister.registerIcon(ModLibs.texturesPath + "cellIron");
        textureTop = par1IconRegister.registerIcon(ModLibs.texturesPath + "cellIron");
        textureBack = par1IconRegister.registerIcon(ModLibs.texturesPath + "cellIron");
        textureBottom = par1IconRegister.registerIcon(ModLibs.texturesPath + "cellIron");
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
        TileMechanicalFurnace furnace = (TileMechanicalFurnace) world.getTileEntity(x, y, z);
        furnace.openInventory();
        for (int t = 0; t < 2; t++){
            ItemStack stack = furnace.getStackInSlot(t);
            if (stack != null) {
                furnace.setInventorySlotContents(t, null);
                ModUtils.dropItemstack(world, x, y, z, stack);
            }
        }
        furnace.closeInventory();
        super.breakBlock(world, x, y, z, block, meta);
    }


    @Override
    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack stack)
    {
        ForgeDirection orientation = ModUtils.get2dOrientation(entityliving);
        world.setBlockMetadataWithNotify(i, j, k, orientation.getOpposite().ordinal(),1);
    }
}
