package redstonedistortion.libs;

public class ModLibs
{
    //Base mod
    public static final String modVersion = "@VERSION@";
    public static final String modId = "reddistortion";
    public static final String modName = "Redstone Distortion";

    public static final String aceptedMinecraftVersions = "1.7.10";

    public static final String dependencies = "required-after:RedCore";

    //proxies
    public static final String clientSide = "redstonedistortion.proxies.ClientProxy";
    public static final String serverSide = "redstonedistortion.proxies.CommonProxy";

    public static String texturesPath = "reddistortion" + ":";

    //GUIIDS
    public static final int guiMechanicalFurnace = 101;
}

