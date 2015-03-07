package redstonedistortion;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.config.Configuration;
import redstonedistortion.block.ModBlocks;
import redstonedistortion.core.configurations.ConfigHandler;
import redstonedistortion.core.initialization.ModOreDictionary;
import redstonedistortion.core.worldgen.WorldManager;
import redstonedistortion.energy.ModEnergy;
import redstonedistortion.factory.ModFactory;
import redstonedistortion.factory.guis.GuiHandler;
import redstonedistortion.integration.ModIntegration;
import redstonedistortion.item.ModItems;
import redstonedistortion.libs.ModLibs;
import redstonedistortion.packets.PacketHandler;
import redstonedistortion.proxies.CommonProxy;
import redstonedistortion.recipes.ModRecipes;
import redstonedistortion.utils.ModLogger;

import java.io.File;

/**
 * TODO: fix the regain value of the energy repitition method
 * TODO: start working on a food engine(as suggested on the forums)
 */
@Mod(modid = ModLibs.modId,
        name = ModLibs.modName,
        version = ModLibs.modVersion,
        acceptedMinecraftVersions = ModLibs.aceptedMinecraftVersions,
        guiFactory = "redstonedistortion.core.configurations.GuiFactory",
        dependencies = "required-after:BuildCraft|Core")
public class ModRedstoneDistortion {
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
    public void preInit(FMLPreInitializationEvent event) {
        ModLogger.initialize();

        config = new Configuration(new File(event.getModConfigurationDirectory(), "RedstoneDistortion.cfg"));
        ConfigHandler.loadConfig(config);

        NetworkRegistry.INSTANCE.newSimpleChannel(ModLibs.CHANNEL);
        PacketHandler.init();

        ModLogger.initialize();

        ModBlocks.init();
        ModBlocks.registry();

        ModItems.init();
        ModItems.registry();

        ModFactory.init();
        ModFactory.registry();

        ModEnergy.init();
        ModEnergy.regsitry();

        ModRecipes.addRecipes();

        CommonProxy.renderObjects();

        ModOreDictionary.initOreDictionary();

        ModIntegration.integration();

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

        GameRegistry.registerWorldGenerator(new WorldManager(), 0);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ModLogger.initialize();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        ModLogger.initialize();
    }
}
