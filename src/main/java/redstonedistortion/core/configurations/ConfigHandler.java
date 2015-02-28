package redstonedistortion.core.configurations;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler
{
    public static Configuration config;
    public static boolean toggleHardModeRecipes;
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
            config.addCustomCategoryComment("misc", "Misc. Section");
            toggleHardModeRecipes = config.get("Toggle to Enable Hard Mode BC Recipes", "misc" , false).getBoolean(false);
            POWER_USAGE = config.getInt("Power Usage Option for machines", "misc", 30, 20, 110, "Base Usage = 30, minValue = 20, maxValue = 110");


            config.addCustomCategoryComment("IDS'", "IDS' Section");

        }finally{

            if(config.hasChanged());
            config.save();
        }
    }
}
