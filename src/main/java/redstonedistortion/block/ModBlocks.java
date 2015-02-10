package redstonedistortion.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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

    public static Material m;

    public static void init()
    {
        //Ores
        oreCopper = new OreCopper(m).setBlockName("oreCopper").setBlockTextureName("oreCopper");
        oreTin = new OreTin(m).setBlockName("oreTin").setBlockTextureName(ModLibs.texturesPath + "oreTin");
        oreSilver = new OreSilver(m).setBlockName("oreSilver").setBlockTextureName(ModLibs.texturesPath + "oreSilver");
        oreLead = new OreLead(m).setBlockName("oreLead").setBlockTextureName(ModLibs.texturesPath + "oreLead");
        oreBronze = new OreBronze(m).setBlockName("oreBronze").setBlockTextureName(ModLibs.texturesPath + "oreBronze");
        orePlatinum = new OrePlatinum(m).setBlockName("orePlatinum").setBlockTextureName(ModLibs.texturesPath + "orePlatinum");
    }

    public static void registry()
    {
        GameRegistry.registerBlock(oreCopper, "oreCopper");
        GameRegistry.registerBlock(oreTin, "oreTin");
        GameRegistry.registerBlock(oreSilver, "oreSilver");
        GameRegistry.registerBlock(oreLead, "oreLead");
        GameRegistry.registerBlock(oreBronze, "oreBronze");
        GameRegistry.registerBlock(orePlatinum, "orePlatinum");
    }
}
