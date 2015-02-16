package redstonedistortion.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import redstonedistortion.block.ModBlocks;
import redstonedistortion.item.ModItems;

public class ModRecipes
{
    public static void addRecipes()
    {
        GameRegistry.addSmelting(ModBlocks.oreBronze, new ItemStack(ModItems.ingotBronze), 1.0F);
        GameRegistry.addSmelting(ModBlocks.oreCopper, new ItemStack(ModItems.ingotCopper), 1.0F);
        GameRegistry.addSmelting(ModBlocks.oreLead, new ItemStack(ModItems.ingotLead), 1.0F);
        GameRegistry.addSmelting(ModBlocks.orePlatinum, new ItemStack(ModItems.ingotPlatinum), 1.0F);
        GameRegistry.addSmelting(ModBlocks.oreSilver, new ItemStack(ModItems.ingotSilver), 1.0F);
        GameRegistry.addSmelting(ModBlocks.oreTin, new ItemStack(ModItems.ingotTin), 1.0F);
    }
}
