package redstonedistortion.factory.base;

import cofh.api.energy.ItemEnergyContainer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import redstonedistortion.core.creativetabs.CreativeTabRedstoneDistortion;
import redstonedistortion.libs.ModLibs;

import java.util.List;

public class BasePowerContainer extends ItemEnergyContainer
{
    public String name;
    public int capacity;
    public int maxReceive;
    public int maxExtract;

    public BasePowerContainer(int capacity, int maxReceive, int maxExtract, String name) {
        super(capacity, maxReceive, maxExtract);
        this.name = name;
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.setTextureName(ModLibs.texturesPath + name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabRedstoneDistortion.RDItemTab);
    }

    public void addCapacity(int capacity)
    {
        this.capacity = capacity;

    }

    @SuppressWarnings({ "rawtypes", "unchecked"})
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack iStack, EntityPlayer player, List list, boolean visible)
    {
        list.add("Max Extract and Receive: " + maxExtract + "/" + maxReceive);
    }
}
