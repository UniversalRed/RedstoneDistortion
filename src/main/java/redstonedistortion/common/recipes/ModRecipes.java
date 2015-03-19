package redstonedistortion.common.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import redstonedistortion.common.block.ModBlocks;
import redstonedistortion.common.factory.ModFactory;
import redstonedistortion.common.item.ModItems;
import redstonedistortion.common.recipes.factory.DesolatorRecipes;

public class ModRecipes {

    public static int desolatorOutput = 2;

    public static void addRecipes() {
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

        //Shapeless Recipes

        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.plateIron, 1),
                new ItemStack(Items.iron_ingot), Items.iron_ingot, Items.iron_ingot, Items.iron_ingot);

        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.plateGold, 1),
                new ItemStack(Items.gold_ingot), Items.gold_ingot, Items.gold_ingot, Items.gold_ingot);

        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.plateBronze),
                new ItemStack(ModItems.ingotBronze), ModItems.ingotBronze, ModItems.ingotBronze, ModItems.ingotBronze);

        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.plateTin, 1),
                new ItemStack(ModItems.ingotTin), ModItems.ingotTin, ModItems.ingotTin, ModItems.ingotTin);

        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.plateCopper),
                new ItemStack(ModItems.ingotCopper), ModItems.ingotCopper, ModItems.ingotCopper, ModItems.ingotCopper);

        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.plateSilver),
                new ItemStack(ModItems.ingotSilver), ModItems.ingotSilver, ModItems.ingotSilver, ModItems.ingotSilver);

        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.plateLead),
                new ItemStack(ModItems.ingotLead), ModItems.ingotLead, ModItems.ingotLead, ModItems.ingotLead);

        //Plates
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.plateCopper, 1), "SSX",
                "SSX",
                "XXX",
                'S', ModItems.ingotCopper);

        GameRegistry.addShapedRecipe(new ItemStack(ModItems.plateBronze, 1), "SSX",
                "SSX",
                "XXX",
                'S', ModItems.ingotBronze);

        GameRegistry.addShapedRecipe(new ItemStack(ModItems.plateLead, 1), "SSX",
                "SSX",
                "XXX",
                'S', ModItems.ingotLead);

        GameRegistry.addShapedRecipe(new ItemStack(ModItems.plateSilver, 1), "SSX",
                "SSX",
                "XXX",
                'S', ModItems.ingotSilver);

        GameRegistry.addShapedRecipe(new ItemStack(ModItems.plateTin, 1), "SSX",
                "SSX",
                "XXX",
                'S', ModItems.ingotTin);

        GameRegistry.addShapedRecipe(new ItemStack(ModItems.plateIron, 1),
                "SSX",
                "SSX",
                "XXX",
                'S', Items.iron_ingot);

        GameRegistry.addShapedRecipe(new ItemStack(ModItems.plateGold, 1), "SSX",
                "SSX",
                "XXX",
                'S', Items.gold_ingot);

        //Machines
        GameRegistry.addShapedRecipe(new ItemStack(ModFactory.mechanicalFurnace, 1),
                "CRC",
                "PFP",
                "PPP",
                'P', ModItems.plateIron, 'C', ModItems.plateCopper, 'R', Blocks.iron_block, 'F', Blocks.furnace);

        GameRegistry.addShapedRecipe(new ItemStack(ModFactory.mechanicalTransfuser, 1),
                "CCC",
                "PRP",
                "PPP",
                'P', ModItems.plateIron, 'C', ModItems.plateTin, 'R', Blocks.redstone_block);

        GameRegistry.addShapedRecipe(new ItemStack(ModFactory.mechanicalDesolator, 1),
                "CRC",
                "PFP",
                "PPP",
                'P', ModItems.plateIron, 'C', ModItems.plateCopper, 'R', Blocks.piston, 'F', Blocks.iron_block);

        //Other
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.ingotBronze, 1), "ISX",
                "SSX",
                "XXX",
                'S', ModItems.ingotCopper, 'I', ModItems.ingotTin);

        //Blocks
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockCopper, 1),
                "XXX",
                "XXX",
                "XXX",
                'X', ModItems.ingotCopper);

        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockTin, 1),
                "XXX",
                "XXX",
                "XXX",
                'X', ModItems.ingotTin);

        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockBronze, 1),
                "XXX",
                "XXX",
                "XXX",
                'X', ModItems.ingotBronze);

        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockLead, 1),
                "XXX",
                "XXX",
                "XXX",
                'X', ModItems.ingotLead);

        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockSilver, 1),
                "XXX",
                "XXX",
                "XXX",
                'X', ModItems.ingotSilver);

        DesolatorRecipes.desolating().recipe(Blocks.iron_ore, new ItemStack(ModItems.dustIron, desolatorOutput));
        DesolatorRecipes.desolating().recipe(Blocks.gold_ore, new ItemStack(ModItems.dustGold, desolatorOutput));
        DesolatorRecipes.desolating().recipe(ModBlocks.oreCopper, new ItemStack(ModItems.dustCopper, desolatorOutput));
        DesolatorRecipes.desolating().recipe(ModBlocks.oreTin, new ItemStack(ModItems.dustTin, desolatorOutput));
        DesolatorRecipes.desolating().recipe(ModBlocks.oreBronze, new ItemStack(ModItems.dustBronze, desolatorOutput));
        DesolatorRecipes.desolating().recipe(ModBlocks.oreLead, new ItemStack(ModItems.dustLead, desolatorOutput));
        DesolatorRecipes.desolating().recipe(ModBlocks.oreSilver, new ItemStack(ModItems.dustSilver, desolatorOutput));

        DesolatorRecipes.desolating().recipe(Items.iron_ingot, new ItemStack(ModItems.dustIron, 1));
        DesolatorRecipes.desolating().recipe(Items.gold_ingot, new ItemStack(ModItems.dustGold, 1));
        DesolatorRecipes.desolating().recipe(ModItems.ingotBronze, new ItemStack(ModItems.dustBronze, 1));
        DesolatorRecipes.desolating().recipe(ModItems.ingotCopper, new ItemStack(ModItems.dustCopper, 1));
        DesolatorRecipes.desolating().recipe(ModItems.ingotLead, new ItemStack(ModItems.dustLead, 1));
        DesolatorRecipes.desolating().recipe(ModItems.ingotSilver, new ItemStack(ModItems.dustSilver, 1));
        DesolatorRecipes.desolating().recipe(ModItems.ingotTin, new ItemStack(ModItems.dustTin, 1));

        DesolatorRecipes.desolating().recipe(Blocks.coal_ore, new ItemStack(Items.coal, 2));
        DesolatorRecipes.desolating().recipe(Blocks.redstone_ore, new ItemStack(Items.redstone, 6));
        DesolatorRecipes.desolating().recipe(Blocks.lapis_ore, new ItemStack(Items.dye, 6, 4));
        DesolatorRecipes.desolating().recipe(Blocks.quartz_ore, new ItemStack(Items.quartz, 4));
    }
}
