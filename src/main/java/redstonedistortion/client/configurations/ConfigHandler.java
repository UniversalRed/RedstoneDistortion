package redstonedistortion.client.configurations;

import net.minecraftforge.common.config.Configuration;
import redstonedistortion.bases.utils.VersionChecker;

import java.io.File;

public class ConfigHandler
{
    public static Configuration config;
    public static boolean toggleHardModeRecipes, shouldPrintChangelog;
    public static int POWER_USAGE;

    public static void init(File file){
        config = new Configuration(file);
        loadConfig(config);
        POWER_USAGE = 30;
        toggleHardModeRecipes = false;
    }

    public static void loadConfig(Configuration config)
    {
        try
        {
            config.addCustomCategoryComment("Updates", "Section about updates");
            if (config.get("Updates", "shouldCheckForUpdates", true).getBoolean())
                VersionChecker.start();
            shouldPrintChangelog = config.get("Updates", "shouldPrintOutChangelog", false).getBoolean();


            config.addCustomCategoryComment("misc", "Misc. Section");
            toggleHardModeRecipes = config.get("Toggle to Enable Hard Mode BC Recipes", "misc", false).getBoolean(false);
            POWER_USAGE = config.getInt("Power Usage Option for machines (RF/t)", "misc", 60, 20, 110, "Base Usage = 60, minValue = 20, maxValue = 110");

            config.addCustomCategoryComment("IDS'", "IDS' Section");

        }finally{

            if(config.hasChanged());
            config.save();
        }
    }
}
