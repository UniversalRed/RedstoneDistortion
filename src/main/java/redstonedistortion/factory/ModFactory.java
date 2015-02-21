package redstonedistortion.factory;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import redstonedistortion.factory.blocks.machines.BlockMechanicalFurnace;
import redstonedistortion.factory.blocks.cells.BlockCellIron;
import redstonedistortion.factory.blocks.machines.BlockMechanicalTransfuser;
import redstonedistortion.factory.items.ItemDebugger;
import redstonedistortion.factory.tiles.machines.TileMechanicalFurnace;
import redstonedistortion.factory.tiles.cells.TileCellIron;
import redstonedistortion.factory.tiles.machines.TileMechanicalTransfuser;

public class ModFactory {
    //Machines
    public static Block mechanicalFurnace;
    public static Block mineralCompressor;
    public static Block mechanicalTransfuser;

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
     */

    public static Material m;

    public static void init() {
        //Machines
        mechanicalFurnace = new BlockMechanicalFurnace(Material.iron, "mechanicalFurnace");
        mechanicalTransfuser = new BlockMechanicalTransfuser(Material.iron, "mechanicalTransfuser");

        //Containers/capsules
        cellIron = new BlockCellIron(Material.iron, "cellIron");

        //Other
    }

    public static void registry() {
        GameRegistry.registerBlock(mechanicalFurnace, "mechanicalFurnace");
        GameRegistry.registerTileEntity(TileMechanicalFurnace.class, "mechanicalFurnace");
        GameRegistry.registerBlock(mechanicalTransfuser, "mechanicalTransfuser");
        GameRegistry.registerTileEntity(TileMechanicalTransfuser.class, "mechanicalTransfuser");

        GameRegistry.registerBlock(cellIron, "cellIron");
        GameRegistry.registerTileEntity(TileCellIron.class, "cellIron");

        //Other
    }
}
