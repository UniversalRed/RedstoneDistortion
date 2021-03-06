package redstonedistortion.common.integration.buildcraft;

import buildcraft.api.blueprints.SchematicBlock;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import redstonedistortion.common.block.ModBlocks;
import redstonedistortion.client.configurations.ConfigHandler;
import redstonedistortion.common.item.ModItems;
import redstonedistortion.common.recipes.RecipeRemover;

public class BuildCraftIntegration
{
    buildcraft.api.blueprints.ISchematicRegistry schemes = buildcraft.api.blueprints.BuilderAPI.schematicRegistry;
    public static Block quarryBlock = GameRegistry.findBlock("BuildCraft|Factory", "quarryBlock");
    public static Block floodGateBlock = GameRegistry.findBlock("BuildCraft|Factory", "floodGateBlock");
    public static Item ironGearItem = GameRegistry.findItem("BuildCraft|Core", "ironGearItem");
    public static Item pipeFluidsGold = GameRegistry.findItem("BuildCraft|Transport", "pipeFluidsGold");


    public static void integrateMod()
    {
        addModRecipes();
        addHardModeRecipes();
    }

    public static void addModRecipes()
    {

    }

    public static void addHardModeRecipes()
    {
        if(ConfigHandler.toggleHardModeRecipes == true)
        {
            RecipeRemover.removeRecipes(new ItemStack(quarryBlock));
            RecipeRemover.removeRecipes(new ItemStack(floodGateBlock));

            GameRegistry.addRecipe(new ItemStack(quarryBlock,1), "SIS",
                    "IRI",
                    "SSS",
                    'I',ironGearItem, 'S', ModItems.plateIron,'R', Blocks.redstone_block);

            GameRegistry.addRecipe(new ItemStack(floodGateBlock, 1), "SBS",
                    "GRG",
                    "SGS",
                    'G', pipeFluidsGold, 'S', ModItems.plateIron, 'R', Blocks.redstone_block, 'B', Blocks.iron_block);
        }
    }

    @Mod.EventHandler
    public void addModSchematics()
    {
        schemes.registerSchematicBlock(ModBlocks.oreCopper, SchematicBlock.class);
        schemes.registerSchematicBlock(ModBlocks.oreTin, SchematicBlock.class);
        schemes.registerSchematicBlock(ModBlocks.oreSilver, SchematicBlock.class);
        schemes.registerSchematicBlock(ModBlocks.oreLead, SchematicBlock.class);

        schemes.registerSchematicBlock(ModBlocks.blockCopper, SchematicBlock.class);
        schemes.registerSchematicBlock(ModBlocks.blockTin, SchematicBlock.class);
        schemes.registerSchematicBlock(ModBlocks.blockSilver, SchematicBlock.class);
        schemes.registerSchematicBlock(ModBlocks.blockLead, SchematicBlock.class);
        schemes.registerSchematicBlock(ModBlocks.blockBronze, SchematicBlock.class);
    }
}
