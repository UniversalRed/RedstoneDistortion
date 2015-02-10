package redstonedistortion.core.initialization;

import net.minecraftforge.oredict.OreDictionary;
import redstonedistortion.block.ModBlocks;
import redstonedistortion.item.ModItems;

public class ModOreDictionary
{
    public static void initOreDictionary()
    {
        //Ores
        OreDictionary.registerOre("oreCopper", ModBlocks.oreCopper);
        OreDictionary.registerOre("oreTin", ModBlocks.oreTin);
        OreDictionary.registerOre("oreSilver", ModBlocks.oreSilver);
        OreDictionary.registerOre("oreLead", ModBlocks.oreLead);
        OreDictionary.registerOre("oreBronze", ModBlocks.oreBronze);
        OreDictionary.registerOre("orePlatinum", ModBlocks.orePlatinum);

        //Ingots
        OreDictionary.registerOre("ingotCopper", ModItems.ingotCopper);
        OreDictionary.registerOre("ingotTin", ModItems.ingotTin);
        OreDictionary.registerOre("ingotSilver", ModItems.ingotSilver);
        OreDictionary.registerOre("ingotLead", ModItems.ingotLead);
        OreDictionary.registerOre("ingotBronze", ModItems.ingotBronze);
        OreDictionary.registerOre("ingotPlatinum", ModItems.ingotPlatinum);

        //Dusts
        OreDictionary.registerOre("dustCopper", ModItems.dustCopper);
        OreDictionary.registerOre("dustTin", ModItems.dustTin);
        OreDictionary.registerOre("dustSilver", ModItems.dustSilver);
        OreDictionary.registerOre("dustLead", ModItems.dustLead);
        OreDictionary.registerOre("dustBronze", ModItems.dustBronze);
        OreDictionary.registerOre("dustPlatinum", ModItems.dustPlatinum);
    }
}
