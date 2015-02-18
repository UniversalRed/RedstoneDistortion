package redstonedistortion.integration.thermalexpansion;

import cofh.api.modhelpers.ThermalExpansionHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import redstonedistortion.block.ModBlocks;
import redstonedistortion.item.ModItems;

public class ThermalExpansionIntegration
{
    public static void integrateMod()
    {
        addModRecipes();
    }

    public static void addModRecipes()
    {
        //Pulverizer
        ThermalExpansionHelper.addPulverizerRecipe(1000, new ItemStack(ModBlocks.oreCopper), new ItemStack(ModItems.dustCopper, 2));
        ThermalExpansionHelper.addPulverizerRecipe(1000, new ItemStack(ModBlocks.oreTin), new ItemStack(ModItems.dustTin, 2));
        ThermalExpansionHelper.addPulverizerRecipe(1000, new ItemStack(ModBlocks.oreSilver), new ItemStack(ModItems.dustSilver, 2));
        ThermalExpansionHelper.addPulverizerRecipe(1000, new ItemStack(ModBlocks.oreLead), new ItemStack(ModItems.dustLead, 2));
        ThermalExpansionHelper.addPulverizerRecipe(1000, new ItemStack(ModBlocks.oreBronze), new ItemStack(ModItems.dustBronze, 2));
        ThermalExpansionHelper.addPulverizerRecipe(1000, new ItemStack(ModBlocks.orePlatinum), new ItemStack(ModItems.dustPlatinum, 2));

        //Smelter:
            //Ores
            ThermalExpansionHelper.addSmelterRecipe(1000, new ItemStack(ModBlocks.oreCopper), new ItemStack(Items.coal), new ItemStack(ModItems.ingotCopper));
            ThermalExpansionHelper.addSmelterRecipe(1000, new ItemStack(ModBlocks.oreTin), new ItemStack(Items.coal), new ItemStack(ModItems.ingotTin));
            ThermalExpansionHelper.addSmelterRecipe(1000, new ItemStack(ModBlocks.oreSilver), new ItemStack(Items.coal), new ItemStack(ModItems.ingotSilver));
            ThermalExpansionHelper.addSmelterRecipe(1000, new ItemStack(ModBlocks.oreLead), new ItemStack(Items.coal), new ItemStack(ModItems.ingotLead));
            ThermalExpansionHelper.addSmelterRecipe(1000, new ItemStack(ModBlocks.oreBronze), new ItemStack(Items.coal), new ItemStack(ModItems.ingotBronze));
            ThermalExpansionHelper.addSmelterRecipe(1000, new ItemStack(ModBlocks.orePlatinum), new ItemStack(Items.coal), new ItemStack(ModItems.ingotPlatinum));

            //Dusts
            ThermalExpansionHelper.addSmelterRecipe(1000, new ItemStack(ModItems.dustCopper), new ItemStack(Items.coal), new ItemStack(ModItems.ingotCopper));
            ThermalExpansionHelper.addSmelterRecipe(1000, new ItemStack(ModItems.dustTin), new ItemStack(Items.coal), new ItemStack(ModItems.ingotTin));
            ThermalExpansionHelper.addSmelterRecipe(1000, new ItemStack(ModItems.dustSilver), new ItemStack(Items.coal), new ItemStack(ModItems.ingotSilver));
            ThermalExpansionHelper.addSmelterRecipe(1000, new ItemStack(ModItems.dustLead), new ItemStack(Items.coal), new ItemStack(ModItems.ingotLead));
            ThermalExpansionHelper.addSmelterRecipe(1000, new ItemStack(ModItems.dustBronze), new ItemStack(Items.coal), new ItemStack(ModItems.ingotBronze));
            ThermalExpansionHelper.addSmelterRecipe(1000, new ItemStack(ModItems.dustPlatinum), new ItemStack(Items.coal), new ItemStack(ModItems.ingotPlatinum));
    }
}