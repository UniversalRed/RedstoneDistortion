package redstonedistortion.core.configurations;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler
{
    public static Configuration config;

    public static void init(File file){
        config = new Configuration(file);
        loadConfig(config);
    }

    public static void loadConfig(Configuration config)
    {
        try
        {
            config.addCustomCategoryComment("IDS'", "IDS' Section");

        }finally{

            if(config.hasChanged());
            config.save();
        }
    }
}
