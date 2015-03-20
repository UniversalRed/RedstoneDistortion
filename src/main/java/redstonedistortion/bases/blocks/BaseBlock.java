package redstonedistortion.bases.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import redstonedistortion.client.creativetabs.CreativeTabRedstoneDistortion;
import redstonedistortion.bases.libs.ModLibs;

/**
 * Created by UniversalRed on 15-02-20.
 */
public class BaseBlock extends Block
{
    public BaseBlock(Material material, String name) {
        super(material);
        setBlockTextureName(ModLibs.texturesPath + name);
        setBlockName(name);
        setCreativeTab(CreativeTabRedstoneDistortion.RDBlockTab);
    }
}
