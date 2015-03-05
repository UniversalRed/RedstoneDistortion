package redstonedistortion.factory;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import redstonedistortion.factory.blocks.energyproducers.generators.BlockCombustionGenerator;
import redstonedistortion.factory.blocks.machines.*;
import redstonedistortion.factory.tiles.machines.*;

public class ModFactory {
    //Machines
    public static Block mechanicalFurnace;
    public static Block mechanicalDesolator;
    public static Block mechanicalTransfuser;

    public static Block combustionGenerator;

    //Energy Cells/Capsules
    public static Block cellIron;
    public static Block cellGold;
    public static Block cellDiamond;
    public static Block cellEmerald;
    public static Block cellCreative;

    //Modules

    /**
     * UPGRADES: - pickaxe, - sword, - axe, - shovel, - hoe, - multitool
     */

    public static Material m;

    public static void init() {
        //Machines
        mechanicalFurnace = new BlockMechanicalFurnace("mechanicalFurnace");
        mechanicalTransfuser = new BlockMechanicalTransfuser("mechanicalTransfuser");
        mechanicalDesolator = new BlockMechanicalDesolator("mechanicalDesolator");

        //Other

    }

    public static void registry() {
        GameRegistry.registerBlock(mechanicalFurnace, "mechanicalFurnace");
        GameRegistry.registerTileEntity(TileMechanicalFurnace.class, "mechanicalFurnace");
        GameRegistry.registerBlock(mechanicalTransfuser, "mechanicalTransfuser");
        GameRegistry.registerTileEntity(TileMechanicalTransfuser.class, "mechanicalTransfuser");
        GameRegistry.registerBlock(mechanicalDesolator, "mechanicalDesolator");
        GameRegistry.registerTileEntity(TileMechanicalDesolator.class, "mechanicalDesolator");

        //Other
    }
}
