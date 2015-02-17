package redstonedistortion.bases.items;

import net.minecraft.item.Item;
import redstonedistortion.core.creativetabs.CreativeTabRedstoneDistortion;
import redstonedistortion.libs.ModLibs;

public class ModDusts extends Item
{
    public ModDusts(String name)
    {
        super();
        setMaxStackSize(64);
        setCreativeTab(CreativeTabRedstoneDistortion.RDItemTab);
        setUnlocalizedName(name);
        setTextureName(ModLibs.texturesPath + name);
    }
}
