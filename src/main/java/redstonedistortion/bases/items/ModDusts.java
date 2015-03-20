package redstonedistortion.bases.items;

import net.minecraft.item.Item;
import redstonedistortion.client.creativetabs.CreativeTabRedstoneDistortion;
import redstonedistortion.bases.libs.ModLibs;

public class ModDusts extends Item
{
    public ModDusts(String name)
    {
        super();
        setMaxStackSize(64);
        setCreativeTab(CreativeTabRedstoneDistortion.RDItemTab);
        setUnlocalizedName(name);
        setTextureName(ModLibs.texturesPath + name);
        setFull3D();
    }
}
