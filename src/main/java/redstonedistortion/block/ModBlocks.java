package redstonedistortion.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import redstonedistortion.bases.blocks.BaseBlock;
import redstonedistortion.block.ores.*;
import redstonedistortion.libs.ModLibs;

public class ModBlocks
{
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
    public static Block blockPlatinum;
    public static Material m;

    public static void init()
    {
        //Ores
        oreCopper = new OreCopper(m.rock).setBlockName("oreCopper").setBlockTextureName("oreCopper");
        oreTin = new OreTin(m.rock).setBlockName("oreTin").setBlockTextureName(ModLibs.texturesPath + "oreTin");
        oreSilver = new OreSilver(m.rock).setBlockName("oreSilver").setBlockTextureName(ModLibs.texturesPath + "oreSilver");
        oreLead = new OreLead(m.rock).setBlockName("oreLead").setBlockTextureName(ModLibs.texturesPath + "oreLead");
        oreBronze = new OreBronze(m.rock).setBlockName("oreBronze").setBlockTextureName(ModLibs.texturesPath + "oreBronze");
        orePlatinum = new OrePlatinum(m.rock).setBlockName("orePlatinum").setBlockTextureName(ModLibs.texturesPath + "orePlatinum");

        blockCopper = new BaseBlock(m.rock, "blockCopper");
        blockTin = new BaseBlock(m.rock, "blockTin");
        blockSilver = new BaseBlock(m.rock, "blockSilver");
        blockLead = new BaseBlock(m.rock, "blockLead");
        blockBronze = new BaseBlock(m.rock, "blockBronze");
        blockPlatinum = new BaseBlock(m.rock, "blockPlatinum");
    }

    public static void registry()
    {
        GameRegistry.registerBlock(oreCopper, "oreCopper");
        GameRegistry.registerBlock(oreTin, "oreTin");
        GameRegistry.registerBlock(oreSilver, "oreSilver");
        GameRegistry.registerBlock(oreLead, "oreLead");
        GameRegistry.registerBlock(oreBronze, "oreBronze");
        GameRegistry.registerBlock(orePlatinum, "orePlatinum");

        GameRegistry.registerBlock(blockCopper, "blockCopper");
        GameRegistry.registerBlock(blockTin, "blockTin");
        GameRegistry.registerBlock(blockSilver, "blockSilver");
        GameRegistry.registerBlock(blockLead, "blockLead");
        GameRegistry.registerBlock(blockBronze, "blockBronze");
        GameRegistry.registerBlock(blockPlatinum, "blockPlatinum");
    }
}
