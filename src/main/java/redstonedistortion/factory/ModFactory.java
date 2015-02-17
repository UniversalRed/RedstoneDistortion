package redstonedistortion.factory;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import redstonedistortion.factory.blocks.*;
import redstonedistortion.factory.blocks.cells.BlockIronCell;
import redstonedistortion.factory.items.ItemDebugger;
import redstonedistortion.factory.tiles.*;
import redstonedistortion.factory.tiles.cells.TileIronCell;

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

    //Debug Tool
    public static Item debuggerTester;

    //Modules

    /**
     * UPGRADES: - pickaxe, - sword, - axe, - shovel, - hoe, - multitool
     *
     */

    public static Material m;

    public static void init()
    {
        //Machines
        mechanicalFurnace = new BlockMechanicalFurnace(Material.iron, "mechanicalFurnace");

        //Containers/capsules
        cellIron = new BlockIronCell(Material.iron).setBlockName("cellIron");

        //Other
        debuggerTester = new ItemDebugger("itemDebugger");
    }

    public static void registry()
    {
        GameRegistry.registerBlock(mechanicalFurnace, "mechanicalFurnace");
        GameRegistry.registerTileEntity(TileMechanicalFurnace.class, "mechanicalFurnace");

        GameRegistry.registerBlock(cellIron, "cellIron");
        GameRegistry.registerTileEntity(TileIronCell.class, "cellIron");

        //Other
        GameRegistry.registerItem(debuggerTester, "itemDebugger");
    }
}
