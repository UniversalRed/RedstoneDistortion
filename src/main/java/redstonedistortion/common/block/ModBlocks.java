package redstonedistortion.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import redstonedistortion.bases.blocks.BaseBlock;
import redstonedistortion.bases.libs.ModLibs;
import redstonedistortion.common.block.energy.BlockIronCell;
import redstonedistortion.common.block.energy.BlockSolarPanelTier1;
import redstonedistortion.common.block.factory.BlockMechanicalDesolator;
import redstonedistortion.common.block.factory.BlockMechanicalFurnace;
import redstonedistortion.common.block.factory.BlockMechanicalTransfuser;
import redstonedistortion.common.block.ores.OreCopper;
import redstonedistortion.common.block.ores.OreLead;
import redstonedistortion.common.block.ores.OreSilver;
import redstonedistortion.common.block.ores.OreTin;
import redstonedistortion.common.tiles.energy.TileIronCell;
import redstonedistortion.common.tiles.energy.TileSolarPanelTier1;
import redstonedistortion.common.tiles.factory.TileMechanicalDesolator;
import redstonedistortion.common.tiles.factory.TileMechanicalFurnace;
import redstonedistortion.common.tiles.factory.TileMechanicalTransfuser;

public class ModBlocks {
    public static Block solarPanelTier1;

    public static Block oreCopper;
    public static Block oreTin;
    public static Block oreSilver;
    public static Block oreLead;
    public static Block oreBronze;
    public static Block orePlatinum;

    public static Block blockCopper;
    public static Block blockTin;
    public static Block blockSilver;
    public static Block blockLead;
    public static Block blockBronze;

    //Energy Cells/Capsules
    public static Block cellIron;
    public static Block cellGold;
    public static Block cellDiamond;
    public static Block cellEmerald;
    public static Block cellCreative;

    //Machines
    public static Block mechanicalFurnace;
    public static Block mechanicalDesolator;
    public static Block mechanicalTransfuser;

    public static Block aqueousPressurizer;


    public static Material m;

    public static void init() {
        //Ores
        oreCopper = new OreCopper(Material.rock).setBlockName("oreCopper").setBlockTextureName(ModLibs.texturesPath + "oreCopper");
        oreTin = new OreTin(Material.rock).setBlockName("oreTin").setBlockTextureName(ModLibs.texturesPath + "oreTin");
        oreSilver = new OreSilver(Material.rock).setBlockName("oreSilver").setBlockTextureName(ModLibs.texturesPath + "oreSilver");
        oreLead = new OreLead(Material.rock).setBlockName("oreLead").setBlockTextureName(ModLibs.texturesPath + "oreLead");

        blockCopper = new BaseBlock(Material.rock, "blockCopper");
        blockTin = new BaseBlock(Material.rock, "blockTin");
        blockSilver = new BaseBlock(Material.rock, "blockSilver");
        blockLead = new BaseBlock(Material.rock, "blockLead");
        blockBronze = new BaseBlock(Material.rock, "blockBronze");

        cellIron = new BlockIronCell("blockIronCell");

        //Machines
        mechanicalFurnace = new BlockMechanicalFurnace("mechanicalFurnace");
        mechanicalTransfuser = new BlockMechanicalTransfuser("mechanicalTransfuser");
        mechanicalDesolator = new BlockMechanicalDesolator("mechanicalDesolator");

        //Other
        solarPanelTier1 = new BlockSolarPanelTier1("solarPanelTier1").setBlockName("solarPanelTier1").setBlockTextureName("solarPanelTier1");

    }

    public static void registry() {

        GameRegistry.registerBlock(oreCopper, "oreCopper");
        GameRegistry.registerBlock(oreTin, "oreTin");
        GameRegistry.registerBlock(oreSilver, "oreSilver");
        GameRegistry.registerBlock(oreLead, "oreLead");

        GameRegistry.registerBlock(blockCopper, "blockCopper");
        GameRegistry.registerBlock(blockTin, "blockTin");
        GameRegistry.registerBlock(blockSilver, "blockSilver");
        GameRegistry.registerBlock(blockLead, "blockLead");
        GameRegistry.registerBlock(blockBronze, "blockBronze");

        GameRegistry.registerBlock(cellIron, "blockIronCell");
        GameRegistry.registerTileEntity(TileIronCell.class, "blockIronCell");

        GameRegistry.registerBlock(mechanicalFurnace, "mechanicalFurnace");
        GameRegistry.registerTileEntity(TileMechanicalFurnace.class, "mechanicalFurnace");
        GameRegistry.registerBlock(mechanicalTransfuser, "mechanicalTransfuser");
        GameRegistry.registerTileEntity(TileMechanicalTransfuser.class, "mechanicalTransfuser");
        GameRegistry.registerBlock(mechanicalDesolator, "mechanicalDesolator");
        GameRegistry.registerTileEntity(TileMechanicalDesolator.class, "mechanicalDesolator");

        //GameRegistry.registerBlock(solarPanelTier1, "solarPanelTier1"); NEXT VERSION
        //GameRegistry.registerTileEntity(TileSolarPanelTier1.class, "solarPanelTier1");
    }
}

