package redstonedistortion.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import redstonedistortion.bases.blocks.BaseBlock;
import redstonedistortion.block.ores.*;
import redstonedistortion.integration.thermalexpansion.ThermalExpansionIntegration;
import redstonedistortion.libs.ModLibs;

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

        //Other
        //solarPanelTier1 = new BlockSolarPanelTier1(m.iron, "solarPanelTier1").setBlockName("solarPanelTier1").setBlockTextureName("solarPanelTier1");

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

        //GameRegistry.registerBlock(solarPanelTier1, "solarPanelTier1");
        //GameRegistry.registerTileEntity(TileSolarPanelTier1.class, "solarPanelTier1");

    }
}

