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

    public static final int guiCell = 120;

    //Energy Cell/Capsule Storage
    public static final int cellIronCapacity = 40000;
    public static final int cellGoldCapacity = 80000;
    public static final int cellDiamondCapacity = 160000;
    public static final int cellEmeraldCapacity = 320000;
}

