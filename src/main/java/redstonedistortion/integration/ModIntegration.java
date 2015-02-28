package redstonedistortion.integration;

import cpw.mods.fml.common.Loader;
import redstonedistortion.core.configurations.ConfigHandler;
import redstonedistortion.integration.buildcraft.BuildCraftIntegration;
import redstonedistortion.integration.thermalexpansion.ThermalExpansionIntegration;

public class ModIntegration
{
    public static void integration()
    {
        if(Loader.isModLoaded("ThermalExpansion"))
        {
            ThermalExpansionIntegration.integrateMod();
        }

        if(Loader.isModLoaded("Buildcraft|Core"))
        {
            BuildCraftIntegration.integrateMod();
        }

        if(Loader.isModLoaded(""))
        {

        }

        if(Loader.isModLoaded(""))
        {

        }

        if(Loader.isModLoaded(""))
        {

        }
    }
}
