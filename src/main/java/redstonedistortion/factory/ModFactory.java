package redstonedistortion.factory;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import redstonedistortion.factory.blocks.*;
import redstonedistortion.factory.tiles.*;

public class ModFactory
{
    public static Block mechanicalFurnace;

    public static Material m;

    public static void init()
    {
        mechanicalFurnace = new BlockMechanicalFurnace(m);
    }

    public static void registry()
    {
        GameRegistry.registerBlock(mechanicalFurnace, "mechanicalFurnace");
        GameRegistry.registerTileEntity(TileMechanicalFurnace.class, "mechanicalFurnace");
    }
}
