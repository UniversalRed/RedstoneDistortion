package redstonedistortion.factory;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import redstonedistortion.factory.blocks.cells.BlockCellIron;
import redstonedistortion.factory.blocks.machines.BlockMechanicalDesolator;
import redstonedistortion.factory.blocks.machines.BlockMechanicalFurnace;
import redstonedistortion.factory.blocks.machines.BlockMechanicalTransfuser;
import redstonedistortion.factory.tiles.cells.TileCellIron;
import redstonedistortion.factory.tiles.machines.TileMechanicalDesolator;
import redstonedistortion.factory.tiles.machines.TileMechanicalFurnace;
import redstonedistortion.factory.tiles.machines.TileMechanicalTransfuser;

public class ModFactory {
    //Machines
    public static Block mechanicalFurnace;
    public static Block mechanicalDesolator;
    public static Block mechanicalTransfuser;

    //Energy Cells/Capsules
    public static Block cellIron;
    public static Block cellGold;
    public static Block cellDiamond;
    public static Block cellEmerald;
    public static Block cellCreative;

    //Mechanical Tools
    public static Item RDHelmet;
    public static Item RDChestplate;
    public static Item RDLeggings;
    public static Item RDBoots;

    public static Item powerPickaxe;
    public static Item powerAxe;
    public static Item powerShovel;
    public static Item powerSword;
    public static Item powerWrench;

    public static final Item.ToolMaterial TOOL_RD_MATERIAL = EnumHelper.addToolMaterial("RD_Tool", 3, 100, 8.0F, 0, 25);
    public static final ItemArmor.ArmorMaterial ARMOUR_RD_MATERIAL = EnumHelper.addArmorMaterial("RD_Armour", 10, new int[] { 3, 8, 6, 3 }, 20);
    public static final String[] TEXTURES_ARMOUR = { "redstonearsenal:textures/armor/" + "Flux_1.png", "redstonearsenal:textures/armor/" + "Flux_2.png" };

    //Modules

    /**
     * UPGRADES: - pickaxe, - sword, - axe, - shovel, - hoe, - multitool
     */

    public static Material m;

    public static void init() {
        //Machines
        mechanicalFurnace = new BlockMechanicalFurnace("mechanicalFurnace");
        mechanicalTransfuser = new BlockMechanicalTransfuser(Material.iron, "mechanicalTransfuser");
        mechanicalDesolator = new BlockMechanicalDesolator(Material.iron, "mechanicalDesolator");

        //Containers/capsules
        //cellIron = new BlockCellIron(Material.iron, "cellIron");

        //Other

    }

    public static void registry() {
        GameRegistry.registerBlock(mechanicalFurnace, "mechanicalFurnace");
        GameRegistry.registerTileEntity(TileMechanicalFurnace.class, "mechanicalFurnace");
        GameRegistry.registerBlock(mechanicalTransfuser, "mechanicalTransfuser");
        GameRegistry.registerTileEntity(TileMechanicalTransfuser.class, "mechanicalTransfuser");
        GameRegistry.registerBlock(mechanicalDesolator, "mechanicalDesolator");
        GameRegistry.registerTileEntity(TileMechanicalDesolator.class, "mechanicalDesolator");

        //GameRegistry.registerBlock(cellIron, "cellIron");
        //GameRegistry.registerTileEntity(TileCellIron.class, "cellIron");

        //Other
    }
}
