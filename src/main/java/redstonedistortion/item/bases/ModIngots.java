package redstonedistortion.item.bases;

import net.minecraft.item.Item;
import redstonedistortion.core.creativetabs.CreativeTabRedstoneDistortion;
import redstonedistortion.libs.ModLibs;

import java.util.List;

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
