package redstonedistortion.common.block.energy;

import buildcraft.api.tools.IToolWrench;
import buildcraftAdditions.api.configurableOutput.SideConfiguration;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import redstonedistortion.bases.blocks.BlockCell;
import redstonedistortion.bases.tiles.TileCell;
import redstonedistortion.common.tiles.energy.TileIronCell;
import redstonedistortion.common.item.ModItems;
import redstonedistortion.bases.libs.ModLibs;

import java.util.List;

/**
 * Created by UniversalRed on 15-03-17.
 */
public class BlockIronCell extends BlockCell {

    public BlockIronCell(String name) {
        super(name);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile == null || !(tile instanceof TileCell))
            return;
        if (stack.stackTagCompound != null) {
            NBTBase nbtBase = stack.stackTagCompound.copy();
            if (nbtBase instanceof NBTTagCompound) {
                NBTTagCompound tag = (NBTTagCompound) nbtBase;
                tag.setInteger("x", x);
                tag.setInteger("y", y);
                tag.setInteger("z", z);
                tile.readFromNBT(tag);
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileIronCell();
    }

    public ItemStack emptyIronCell() {
        ItemStack stack = new ItemStack(this, 1, 0);
        stack.stackTagCompound = new NBTTagCompound();
        stack.stackTagCompound.setInteger("energy", 0);
        stack.stackTagCompound.setInteger("maxEnergy", ModLibs.cellIronCapacity);
        stack.stackTagCompound.setInteger("maxInput", ModLibs.cellIronCapacity / 10);
        stack.stackTagCompound.setInteger("maxOutput", ModLibs.cellIronCapacity / 10);
        new SideConfiguration().writeToNBT(stack.stackTagCompound);
        return stack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        list.add(emptyIronCell());
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
        if(new ItemStack(ModItems.itemWrench).getItem() instanceof IToolWrench) {
            player.swingItem();

        }
        return world.getBlockMetadata(x, y, z) == 9 ? emptyIronCell() : super.getPickBlock(target, world, x, y, z, player);
    }
}
