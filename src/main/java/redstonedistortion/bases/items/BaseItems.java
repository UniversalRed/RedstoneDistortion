package redstonedistortion.bases.items;

import net.minecraft.item.Item;
import redstonedistortion.client.creativetabs.CreativeTabRedstoneDistortion;
import redstonedistortion.bases.libs.ModLibs;

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
        setFull3D();
    }
}
