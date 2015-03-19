package redstonedistortion.common.energy;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import redstonedistortion.common.energy.blocks.BlockIronCell;
import redstonedistortion.common.energy.tiles.TileIronCell;

/**
 * Created by UniversalRed on 15-03-05.
 */
public class ModEnergy {

    //Energy Cells/Capsules
    public static Block cellIron;
    public static Block cellGold;
    public static Block cellDiamond;
    public static Block cellEmerald;
    public static Block cellCreative;

    public static void init() {
        cellIron = new BlockIronCell("blockIronCell");
    }

    public static void registry() {
        GameRegistry.registerBlock(cellIron, "blockIronCell");
        GameRegistry.registerTileEntity(TileIronCell.class, "blockIronCell");
    }
}
