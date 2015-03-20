package redstonedistortion.common.integration;

import cpw.mods.fml.common.Loader;
import redstonedistortion.common.integration.buildcraft.BuildCraftIntegration;
import redstonedistortion.common.integration.thermalexpansion.ThermalExpansionIntegration;

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
