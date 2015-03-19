package redstonedistortion;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.config.Configuration;
import redstonedistortion.common.block.ModBlocks;
import redstonedistortion.client.configurations.ConfigHandler;
import redstonedistortion.common.initialization.ModOreDictionary;
import redstonedistortion.common.worldgen.WorldManager;
import redstonedistortion.common.energy.ModEnergy;
import redstonedistortion.common.factory.ModFactory;
import redstonedistortion.common.factory.guis.GuiHandler;
import redstonedistortion.common.integration.ModIntegration;
import redstonedistortion.common.item.ModItems;
import redstonedistortion.bases.libs.ModLibs;
import redstonedistortion.common.packets.PacketHandler;
import redstonedistortion.proxies.CommonProxy;
import redstonedistortion.common.recipes.ModRecipes;
import redstonedistortion.bases.utils.ModLogger;

import java.io.File;

/**
 * TODO: fix the regain value of the energy repitition method
 * TODO: start working on a food engine(as suggested on the forums)
 */
@Mod(modid = ModLibs.modId,
        name = ModLibs.modName,
        version = ModLibs.modVersion,
        acceptedMinecraftVersions = ModLibs.aceptedMinecraftVersions,
        guiFactory = "redstonedistortion.client.configurations.GuiFactory",
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
        ModEnergy.registry();

        ModRecipes.addRecipes();

        proxy.renderObjects();

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
