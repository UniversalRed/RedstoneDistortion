package redstonedistortion.common.initialization;

import net.minecraftforge.oredict.OreDictionary;
import redstonedistortion.common.block.ModBlocks;
import redstonedistortion.common.item.ModItems;

public class ModOreDictionary
{
    public static void initOreDictionary()
    {
        //Ores
        OreDictionary.registerOre("oreCopper", ModBlocks.oreCopper);
        OreDictionary.registerOre("oreTin", ModBlocks.oreTin);
        OreDictionary.registerOre("oreSilver", ModBlocks.oreSilver);
        OreDictionary.registerOre("oreLead", ModBlocks.oreLead);

        OreDictionary.registerOre("blockCopper", ModBlocks.blockCopper);
        OreDictionary.registerOre("blockTin", ModBlocks.blockTin);
        OreDictionary.registerOre("blockSilver", ModBlocks.blockSilver);
        OreDictionary.registerOre("blockLead", ModBlocks.blockLead);
        OreDictionary.registerOre("blockBronze", ModBlocks.blockBronze);

        //Ingots
        OreDictionary.registerOre("ingotCopper", ModItems.ingotCopper);
        OreDictionary.registerOre("ingotTin", ModItems.ingotTin);
        OreDictionary.registerOre("ingotSilver", ModItems.ingotSilver);
        OreDictionary.registerOre("ingotLead", ModItems.ingotLead);
        OreDictionary.registerOre("ingotBronze", ModItems.ingotBronze);

        //Dusts
        OreDictionary.registerOre("dustCopper", ModItems.dustCopper);
        OreDictionary.registerOre("dustTin", ModItems.dustTin);
        OreDictionary.registerOre("dustSilver", ModItems.dustSilver);
        OreDictionary.registerOre("dustLead", ModItems.dustLead);
        OreDictionary.registerOre("dustBronze", ModItems.dustBronze);

        OreDictionary.registerOre("plateCopper", ModItems.plateCopper);
        OreDictionary.registerOre("plateTin", ModItems.plateTin);
        OreDictionary.registerOre("plateSilver", ModItems.plateSilver);
        OreDictionary.registerOre("plateLead", ModItems.plateLead);
        OreDictionary.registerOre("plateBronze", ModItems.plateBronze);
    }
}
