package redstonedistortion.bases.items;

import net.minecraft.item.Item;
import redstonedistortion.client.creativetabs.CreativeTabRedstoneDistortion;
import redstonedistortion.bases.libs.ModLibs;

public class ModIngots extends Item
{
    public ModIngots(String name)
    {
        super();
        setMaxStackSize(64);
        setCreativeTab(CreativeTabRedstoneDistortion.RDItemTab);
        setUnlocalizedName(name);
        setTextureName(ModLibs.texturesPath + name);
    }
}
