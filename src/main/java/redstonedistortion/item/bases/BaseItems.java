package redstonedistortion.item.bases;

import net.minecraft.item.Item;
import redstonedistortion.core.creativetabs.CreativeTabRedstoneDistortion;
import redstonedistortion.libs.ModLibs;

/**
 * Created by UniversalRed on 15-02-16.
 */
public class BaseItems extends Item
{

    public BaseItems(String name)
    {
        this.setCreativeTab(CreativeTabRedstoneDistortion.RDItemTab);
        this.setTextureName(ModLibs.texturesPath + name);
        this.setUnlocalizedName(name);
    }
}
