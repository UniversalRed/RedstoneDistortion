package redstonedistortion.item.bases;

import net.minecraft.item.Item;
import redstonedistortion.core.creativetabs.CreativeTabRedstoneDistortion;

public class ModDusts extends Item
{
    public ModDusts(String string)
    {
        super();
        setMaxStackSize(64);
        setCreativeTab(CreativeTabRedstoneDistortion.RDItemTab);
        setUnlocalizedName(string);
    }
}
