package redstonedistortion.item.items;

import buildcraft.api.tools.IToolWrench;
import cofh.api.item.IToolHammer;
import mods.railcraft.api.core.items.IToolCrowbar;
import net.minecraft.block.Block;
import net.minecraft.block.BlockButton;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockLever;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import redstonedistortion.bases.items.BaseItems;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by UniversalRed on 15-02-16.
 */
public class ItemWrench extends BaseItems implements IToolWrench, IToolCrowbar, IToolHammer
{

    private final Set<Class<? extends Block>> shiftRotations = new HashSet<Class<? extends Block>>();

    public ItemWrench(String name) {
        super("itemWrench");
        setFull3D();
        setMaxStackSize(1);
        shiftRotations.add(BlockLever.class);
        shiftRotations.add(BlockButton.class);
        shiftRotations.add(BlockChest.class);
        setHarvestLevel("wrench", 0);
    }

    private boolean isShiftRotation(Class<? extends Block> cls) {
        for (Class<? extends Block> shift : shiftRotations) {
            if (shift.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        Block block = world.getBlock(x, y, z);

        if (block == null) {
            return false;
        }

        if (player.isSneaking() != isShiftRotation(block.getClass())) {
            return false;
        }

        if (block.rotateBlock(world, x, y, z, ForgeDirection.getOrientation(side))) {
            player.swingItem();
            return !world.isRemote;
        }
        return false;
    }

    @Override
    public boolean canWrench(EntityPlayer player, int x, int y, int z) {
        return true;
    }

    @Override
    public void wrenchUsed(EntityPlayer player, int x, int y, int z) {
        player.swingItem();
    }

    @Override
    public boolean doesSneakBypassUse(World world, int x, int y, int z, EntityPlayer player) {
        return true;
    }

    @Override
    public boolean canWhack(EntityPlayer player, ItemStack crowbar, int x, int y, int z) {
        return false;
    }

    @Override
    public void onWhack(EntityPlayer player, ItemStack crowbar, int x, int y, int z) {
        onWhack(player, crowbar, x, y, z);
    }

    @Override
    public boolean canLink(EntityPlayer player, ItemStack crowbar, EntityMinecart cart) {
        canLink(player, crowbar, cart);
        player.swingItem();
        return true;
    }

    @Override
    public void onLink(EntityPlayer player, ItemStack crowbar, EntityMinecart cart) {
        onLink(player, crowbar, cart);
        player.isSneaking();
    }

    @Override
    public boolean canBoost(EntityPlayer player, ItemStack crowbar, EntityMinecart cart) {
        canBoost(player, crowbar, cart);
        return true;
    }

    @Override
    public void onBoost(EntityPlayer player, ItemStack crowbar, EntityMinecart cart) {
        player.swingItem();
    }

    @Override
    public boolean isUsable(ItemStack item, EntityLivingBase user, int x, int y, int z) {
        if(item.getItem() instanceof ItemWrench) {
            return true;
        }
        return true;
    }

    @Override
    public void toolUsed(ItemStack item, EntityLivingBase user, int x, int y, int z) {
        user.swingItem();
    }
}
