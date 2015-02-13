package redstonedistortion.factory;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import redstonedistortion.factory.base.BaseModule;
import redstonedistortion.factory.blocks.*;
import redstonedistortion.factory.items.ItemDebugger;
import redstonedistortion.factory.items.ItemPowerTool;
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
    public static Block cellCreative;

    //Mechanical Tools
    public static Item powerTool;

    //Debug Tool
    public static Item debuggerTester;

    //Modules
    public static BaseModule pickaxe;

    /**
     * UPGRADES: - pickaxe, - sword, - axe, - shovel, - hoe, - multitool
     *
     */

    public static Material m;

    public static void init()
    {
        //Machines
        mechanicalFurnace = new BlockMechanicalFurnace(m).setBlockName("mechanicalFurnace");

        //Item Containers/capsules
        powerTool = new ItemPowerTool(32000, 300, 300, "powerTool");

        //Other
        debuggerTester = new ItemDebugger("itemDebugger");
        pickaxe = new BaseModule(pickaxe, "pickaxeModule", "powerTool", Item.ToolMaterial.EMERALD);
    }

    public static void registry()
    {
        GameRegistry.registerBlock(mechanicalFurnace, "mechanicalFurnace");
        GameRegistry.registerTileEntity(TileMechanicalFurnace.class, "mechanicalFurnace");

        //Other
        GameRegistry.registerItem(debuggerTester, "itemDebugger");
        GameRegistry.registerItem(pickaxe, "pickaxeModule");
    }
}
