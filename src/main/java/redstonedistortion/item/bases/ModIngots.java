package redstonedistortion.item.bases;

import net.minecraft.item.Item;
import redstonedistortion.core.creativetabs.CreativeTabRedstoneDistortion;

import java.util.List;

public class ModIngots extends Item
{
    public ModIngots(String string)
    {
        super();
        setMaxStackSize(64);
        setCreativeTab(CreativeTabRedstoneDistortion.RDItemTab);
        setUnlocalizedName(string);
    }
}
