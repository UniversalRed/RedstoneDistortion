package redstonedistortion.integration.buildcraft;

import buildcraft.api.blueprints.SchematicBlock;
import cpw.mods.fml.common.Mod;
import redstonedistortion.block.ModBlocks;
import redstonedistortion.block.ores.OreCopper;

public class BuildCraftIntegration
{
    buildcraft.api.blueprints.ISchematicRegistry schemes = buildcraft.api.blueprints.BuilderAPI.schematicRegistry;

    public static void integrateMod()
    {
        addModRecipes();
    }

    public static void addModRecipes()
    {

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
