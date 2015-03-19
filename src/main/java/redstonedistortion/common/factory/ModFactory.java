package redstonedistortion.common.factory;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import redstonedistortion.common.factory.blocks.machines.*;
import redstonedistortion.common.factory.tiles.machines.*;

public class ModFactory {
    //Machines
    public static Block mechanicalFurnace;
    public static Block mechanicalDesolator;
    public static Block mechanicalTransfuser;

    public static Block aqueousPressurizer;

    //Modules

    /**
     * UPGRADES: - pickaxe, - sword, - axe, - shovel, - hoe, - multitool
     */

    public static void init() {
        //Machines
        mechanicalFurnace = new BlockMechanicalFurnace("mechanicalFurnace");
        mechanicalTransfuser = new BlockMechanicalTransfuser("mechanicalTransfuser");
        mechanicalDesolator = new BlockMechanicalDesolator("mechanicalDesolator");

        //aqueousPressurizer = new BlockAqueousPressurizer("aqueousPressurizer");

        //Energy Cells

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
