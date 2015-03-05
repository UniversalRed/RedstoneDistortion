package redstonedistortion.integration.thermalexpansion;

import cofh.api.modhelpers.ThermalExpansionHelper;
import cpw.mods.fml.common.Loader;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import redstonedistortion.block.ModBlocks;
import redstonedistortion.item.ModItems;

public class ThermalExpansionIntegration
{
    public static boolean toggleOres;

    public  ThermalExpansionIntegration () {
        toggleOres = true;
    }

    public static void integrateMod()
    {
        addModRecipes();
        if(Loader.isModLoaded("ThermalExpansion")) {
            toggleOres = false;
        }
    }

    public static void addModRecipes()
    {
        //Pulverizer
        ThermalExpansionHelper.addPulverizerRecipe(1000, new ItemStack(ModBlocks.oreCopper), new ItemStack(ModItems.dustCopper, 2));
        ThermalExpansionHelper.addPulverizerRecipe(1000, new ItemStack(ModBlocks.oreTin), new ItemStack(ModItems.dustTin, 2));
        ThermalExpansionHelper.addPulverizerRecipe(1000, new ItemStack(ModBlocks.oreSilver), new ItemStack(ModItems.dustSilver, 2));
        ThermalExpansionHelper.addPulverizerRecipe(1000, new ItemStack(ModBlocks.oreLead), new ItemStack(ModItems.dustLead, 2));
        ThermalExpansionHelper.addPulverizerRecipe(1000, new ItemStack(ModBlocks.oreBronze), new ItemStack(ModItems.dustBronze, 2));
    }
}
