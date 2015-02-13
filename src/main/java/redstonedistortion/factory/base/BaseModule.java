package redstonedistortion.factory.base;

import cofh.lib.util.helpers.DamageHelper;
import cofh.lib.util.helpers.EnergyHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import redstonedistortion.core.creativetabs.CreativeTabRedstoneDistortion;
import redstonedistortion.libs.ModLibs;

import java.util.List;

public class BaseModule extends Item
{
    public int energyPerUse = 225;

    public String name;
    public Item item;
    public String forToolName;
    public String getType;
    public int damage = 0;

    public BaseModule(Item item, String name, String forToolName, Item.ToolMaterial toolMaterial)
    {
        super();
        this.name = name;
        this.item = item;
        this.forToolName = forToolName;
        this.setTextureName(ModLibs.texturesPath + name);
        this.setCreativeTab(CreativeTabRedstoneDistortion.RDItemTab);
        this.setUnlocalizedName(name);
        this.getType = name;
        damage = 0;
    }

    public String type()
    {
        return getType;
    }

    @SuppressWarnings({ "rawtypes", "unchecked"})
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack iStack, EntityPlayer player, List list, boolean visible)
    {
        list.add(name + " for " + forToolName.toUpperCase());
    }

    public int getEnergyStored(ItemStack container) {

        if (container.stackTagCompound == null) {
            EnergyHelper.setDefaultEnergyTag(container, 0);
        }
        return container.stackTagCompound.getInteger("Energy");
    }
}
