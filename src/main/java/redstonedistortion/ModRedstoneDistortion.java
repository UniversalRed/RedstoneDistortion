package redstonedistortion;

import cpw.mods.fml.common.*;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraftforge.common.config.Configuration;
import redstonedistortion.block.*;
import redstonedistortion.core.configurations.ConfigHandler;
import redstonedistortion.core.initialization.ModOreDictionary;
import redstonedistortion.factory.ModFactory;
import redstonedistortion.factory.guis.GuiHandler;
import redstonedistortion.integration.ModIntegration;
import redstonedistortion.item.*;
import redstonedistortion.libs.*;
import redstonedistortion.proxies.*;
import redstonedistortion.utils.*;

import java.io.File;

/**
 * TODO: create a working energy cell with features to extract, recieve energy from container items
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
        config = new Configuration(new File(event.getModConfigurationDirectory(), "RedstoneDistortion/RedstoneDistortion.cfg"));
        ConfigHandler.loadConfig(config);

        logger.initialize();

        ModBlocks.init();
        ModBlocks.registry();

        ModItems.init();
        ModItems.registry();

        ModFactory.init();
        ModFactory.registry();

        proxy.renderObjects();

        ModOreDictionary.initOreDictionary();

        ModIntegration.integration();

        ConfigHandler.init(event.getSuggestedConfigurationFile());

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
