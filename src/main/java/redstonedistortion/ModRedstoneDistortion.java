package redstonedistortion;

import cpw.mods.fml.common.*;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.config.Configuration;
import redstonedistortion.block.*;
import redstonedistortion.core.configurations.ConfigHandler;
import redstonedistortion.core.initialization.ModOreDictionary;
import redstonedistortion.factory.ModFactory;
import redstonedistortion.factory.guis.GuiHandler;
import redstonedistortion.integration.ModIntegration;
import redstonedistortion.item.*;
import redstonedistortion.libs.*;
import redstonedistortion.network.NetWorkHandler;
import redstonedistortion.proxies.*;
import redstonedistortion.recipes.ModRecipes;
import redstonedistortion.utils.*;
import redstonedistortion.packets.*;

import java.io.File;

/**
 * TODO: create batteries for power tool as well as get started on modular upgrades
 * TODO: create a working energy cell with features to extract, recieve energy from container items
 * TODO: Fix updateEntity() for TileMechanicalFurnace
 */
@Mod(modid = ModLibs.modId, name = ModLibs.modName, version = ModLibs.modVersion, acceptedMinecraftVersions = ModLibs.aceptedMinecraftVersions, guiFactory = "redstonedistortion.core.configurations.GuiFactory")
public class ModRedstoneDistortion
{
    //Declaring Proxies
    @SidedProxy(clientSide = ModLibs.clientSide, serverSide = ModLibs.serverSide)
    public static CommonProxy proxy;

    //Declaring Instances
    @Mod.Instance(ModLibs.modId)
    public static ModRedstoneDistortion instance;

    ModLogger logger = new ModLogger();
    public static Configuration config;

    //Initializing loaders
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger.initialize();

        config = new Configuration(new File(event.getModConfigurationDirectory(), "RedstoneDistortion/RedstoneDistortion.cfg"));
        ConfigHandler.loadConfig(config);

        ModLogger.initialize();

        ModBlocks.init();
        ModBlocks.registry();

        ModItems.init();
        ModItems.registry();

        ModFactory.init();
        ModFactory.registry();

        NetWorkHandler.init();

        ModRecipes.addRecipes();

        CommonProxy.renderObjects();

        ModOreDictionary.initOreDictionary();

        ModIntegration.integration();

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        logger.initialize();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        logger.initialize();
    }
}
