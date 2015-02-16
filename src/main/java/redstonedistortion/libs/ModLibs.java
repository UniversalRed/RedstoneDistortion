package redstonedistortion.libs;

public class ModLibs
{
    //Base Mod
    public static final String modVersion = "@VERSION@";
    public static final String modId = "reddistortion";
    public static final String modName = "Redstone Distortion";

    public static final String aceptedMinecraftVersions = "1.7.10";

    public static final String dependencies = "required-after:RedCore";

    //Proxies
    public static final String clientSide = "redstonedistortion.proxies.ClientProxy";
    public static final String serverSide = "redstonedistortion.proxies.CommonProxy";

    //Textures_Path
    public static final String texturesPath = "reddistortion" + ":";

    //Channel
    public static final String CHANNEL = modId;

    //GUIIDS
    public static final int guiMechanicalFurnace = 101;

    public static final int guiCell = 120;

    //Energy Cell/Capsule Storage
    public static final int cellIronCapacity = 40000;
    public static final int cellGoldCapacity = 80000;
    public static final int cellDiamondCapacity = 160000;
    public static final int cellEmeraldCapacity = 320000;
}

