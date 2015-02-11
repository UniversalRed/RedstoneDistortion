package redstonedistortion.factory;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import redstonedistortion.factory.blocks.*;
import redstonedistortion.factory.tiles.*;

public class ModFactory
{
    //Machines
    public static Block mechanicalFurnace;
    public static Block mineralCompressor;

    //Energy Cells/Capsules
    public static Block cellIron;
    public static Block cellGold;
    public static Block cellDiamond;
    public static Block cellEmerald;

    public static Item powerTool;

    /**
     * UPGRADES: - pickaxe, - sword, - axe, - shovel, - hoe, - multitool
     *
     */

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
