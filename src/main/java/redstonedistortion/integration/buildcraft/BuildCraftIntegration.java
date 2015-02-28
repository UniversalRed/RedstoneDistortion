package redstonedistortion.integration.buildcraft;

import buildcraft.api.blueprints.SchematicBlock;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import redstonedistortion.block.ModBlocks;
import redstonedistortion.block.ores.OreCopper;
import redstonedistortion.core.configurations.ConfigHandler;
import redstonedistortion.item.ModItems;
import redstonedistortion.recipes.RecipeRemover;

public class BuildCraftIntegration
{
    buildcraft.api.blueprints.ISchematicRegistry schemes = buildcraft.api.blueprints.BuilderAPI.schematicRegistry;
    public static Block quarryBlock = GameRegistry.findBlock("BuildCraftFactory", "quarryBlock");
    public static Block floodGateBlock = GameRegistry.findBlock("BuildCraftFactory", "floodGateBlock");
    public static Item ironGearItem = GameRegistry.findItem("BuildCraftCore", "ironGearItem");
    public static Item pipeFluidsGold = GameRegistry.findItem("BuildCraftTransport", "pipeFluidsGold");


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
        schemes.registerSchematicBlock(ModBlocks.oreBronze, SchematicBlock.class);
        schemes.registerSchematicBlock(ModBlocks.orePlatinum, SchematicBlock.class);
    }
}
