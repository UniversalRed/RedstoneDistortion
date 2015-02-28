package redstonedistortion.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import redstonedistortion.block.ModBlocks;
import redstonedistortion.factory.ModFactory;
import redstonedistortion.item.ModItems;
import redstonedistortion.recipes.factory.DesolatorRecipes;

public class ModRecipes
{
    public static void addRecipes()
    {
        GameRegistry.addSmelting(ModBlocks.oreBronze, new ItemStack(ModItems.ingotBronze), 1.0F);
        GameRegistry.addSmelting(ModBlocks.oreCopper, new ItemStack(ModItems.ingotCopper), 1.0F);
        GameRegistry.addSmelting(ModBlocks.oreLead, new ItemStack(ModItems.ingotLead), 1.0F);
        GameRegistry.addSmelting(ModBlocks.oreSilver, new ItemStack(ModItems.ingotSilver), 1.0F);
        GameRegistry.addSmelting(ModBlocks.oreTin, new ItemStack(ModItems.ingotTin), 1.0F);

        GameRegistry.addSmelting(ModItems.dustIron, new ItemStack(Items.iron_ingot), 1.0F);
        GameRegistry.addSmelting(ModItems.dustGold, new ItemStack(Items.gold_ingot), 1.0F);

        GameRegistry.addSmelting(ModItems.dustBronze, new ItemStack(ModItems.ingotBronze), 1.0F);
        GameRegistry.addSmelting(ModItems.dustCopper, new ItemStack(ModItems.ingotCopper), 1.0F);
        GameRegistry.addSmelting(ModItems.dustLead, new ItemStack(ModItems.ingotLead), 1.0F);
        GameRegistry.addSmelting(ModItems.dustSilver, new ItemStack(ModItems.ingotSilver), 1.0F);
        GameRegistry.addSmelting(ModItems.dustTin, new ItemStack(ModItems.ingotTin), 1.0F);

        //Plates
        GameRegistry.addRecipe(new ItemStack(ModItems.plateCopper, 1), "SSX",
                "SSX",
                "XXX",
                'S', ModItems.ingotCopper);

        GameRegistry.addRecipe(new ItemStack(ModItems.plateBronze, 1), "SSX",
                "SSX",
                "XXX",
                'S', ModItems.ingotBronze);

        GameRegistry.addRecipe(new ItemStack(ModItems.plateLead, 1), "SSX",
                "SSX",
                "XXX",
                'S', ModItems.ingotLead);

        GameRegistry.addRecipe(new ItemStack(ModItems.plateSilver, 1), "SSX",
                "SSX",
                "XXX",
                'S', ModItems.ingotSilver);

        GameRegistry.addRecipe(new ItemStack(ModItems.plateTin, 1), "SSX",
                "SSX",
                "XXX",
                'S', ModItems.ingotTin);

        GameRegistry.addRecipe(new ItemStack(ModItems.plateIron, 1), "SSX",
                "SSX",
                "XXX",
                'S', Items.iron_ingot);

        GameRegistry.addRecipe(new ItemStack(ModItems.plateGold, 1), "SSX",
                "SSX",
                "XXX",
                'S', Items.gold_ingot);

        //Machines
        GameRegistry.addRecipe(new ItemStack(ModFactory.mechanicalFurnace, 1),
                "CRC",
                "PFP",
                "PPP",
                'P', ModItems.plateIron,'C', ModItems.plateCopper,'R', Blocks.iron_block,'F', Blocks.furnace);

        GameRegistry.addRecipe(new ItemStack(ModFactory.mechanicalTransfuser, 1),
                "CCC",
                "PRP",
                "PPP",
                'P', ModItems.plateIron,'C', ModItems.plateTin,'R', Blocks.redstone_block);

        GameRegistry.addRecipe(new ItemStack(ModFactory.mechanicalDesolator, 1),
                "CRC",
                "PFP",
                "PPP",
                'P', ModItems.plateIron,'C', ModItems.plateCopper,'R', Blocks.piston,'F', Blocks.iron_block);

        //Other
        GameRegistry.addRecipe(new ItemStack(ModItems.ingotBronze, 1), "ISX",
                "SSX",
                "XXX",
                'S', ModItems.ingotCopper,'I', ModItems.ingotTin);

        //Blocks
        GameRegistry.addRecipe(new ItemStack(ModBlocks.blockCopper, 1),
                "XXX",
                "XXX",
                "XXX",
                'X', ModItems.ingotCopper);

        GameRegistry.addRecipe(new ItemStack(ModBlocks.blockTin, 1),
                "XXX",
                "XXX",
                "XXX",
                'X', ModItems.ingotTin);

        GameRegistry.addRecipe(new ItemStack(ModBlocks.blockBronze, 1),
                "XXX",
                "XXX",
                "XXX",
                'X', ModItems.ingotBronze);

        GameRegistry.addRecipe(new ItemStack(ModBlocks.blockLead, 1),
                "XXX",
                "XXX",
                "XXX",
                'X', ModItems.ingotLead);

        GameRegistry.addRecipe(new ItemStack(ModBlocks.blockSilver, 1),
                "XXX",
                "XXX",
                "XXX",
                'X', ModItems.ingotSilver);

        DesolatorRecipes.desolating().recipe(Blocks.iron_ore, new ItemStack(ModItems.dustIron, 2));
        DesolatorRecipes.desolating().recipe(Blocks.gold_ore, new ItemStack(ModItems.dustGold, 2));
        DesolatorRecipes.desolating().recipe(ModBlocks.oreCopper, new ItemStack(ModItems.dustCopper, 2));
        DesolatorRecipes.desolating().recipe(ModBlocks.oreTin, new ItemStack(ModItems.dustTin, 2));
        DesolatorRecipes.desolating().recipe(ModBlocks.oreBronze, new ItemStack(ModItems.dustBronze, 2));
        DesolatorRecipes.desolating().recipe(ModBlocks.oreLead, new ItemStack(ModItems.dustLead, 2));
        DesolatorRecipes.desolating().recipe(ModBlocks.oreSilver, new ItemStack(ModItems.dustSilver, 2));

        DesolatorRecipes.desolating().recipe(Blocks.coal_ore, new ItemStack(Items.coal, 2));
        DesolatorRecipes.desolating().recipe(Blocks.redstone_ore, new ItemStack(Items.redstone, 6));
        DesolatorRecipes.desolating().recipe(Blocks.lapis_ore, new ItemStack(Items.dye, 1, 6));
        DesolatorRecipes.desolating().recipe(Blocks.quartz_ore, new ItemStack(Items.quartz, 4));
    }
}
