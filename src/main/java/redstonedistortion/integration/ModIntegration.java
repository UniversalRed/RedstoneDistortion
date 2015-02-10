package redstonedistortion.integration;

import cpw.mods.fml.common.Loader;
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
        else if(Loader.isModLoaded("Buildcraft|Core"))
        {
            BuildCraftIntegration.integrateMod();
        }
        else if(Loader.isModLoaded(""))
        {

        }
        else if(Loader.isModLoaded(""))
        {

        }
        else if(Loader.isModLoaded(""))
        {

        }
    }
}
