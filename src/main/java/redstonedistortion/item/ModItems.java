package redstonedistortion.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import redstonedistortion.item.bases.ModIngots;
import redstonedistortion.libs.ModLibs;

public class ModItems
{
    public static Item ingotCopper;
    public static Item ingotTin;
    public static Item ingotSilver;
    public static Item ingotLead;
    public static Item ingotBronze;
    public static Item ingotPlatinum;

    public static Item dustCopper;
    public static Item dustTin;
    public static Item dustSilver;
    public static Item dustLead;
    public static Item dustBronze;
    public static Item dustPlatinum;

    public static void init()
    {
        //Ingots
        ingotCopper = new ModIngots("ingotCopper").setTextureName(ModLibs.texturesPath + "ingotCopper");
        ingotTin = new ModIngots("ingotTin").setTextureName(ModLibs.texturesPath + "ingotTin");
        ingotSilver = new ModIngots("ingotSilver").setTextureName(ModLibs.texturesPath + "ingotSilver");
        ingotLead = new ModIngots("ingotLead").setTextureName(ModLibs.texturesPath + "ingotLead");
        ingotBronze = new ModIngots("ingotBronze").setTextureName(ModLibs.texturesPath + "ingotBronze");
        ingotPlatinum = new ModIngots("ingotPlatinum").setTextureName(ModLibs.texturesPath + "ingotPlatinum");

        //Dusts
        dustCopper = new ModIngots("dustCopper").setTextureName(ModLibs.texturesPath + "dustCopper");
        dustTin = new ModIngots("dustTin").setTextureName(ModLibs.texturesPath + "dustTin");
        dustSilver = new ModIngots("dustSilver").setTextureName(ModLibs.texturesPath + "dustSilver");
        dustLead = new ModIngots("dustLead").setTextureName(ModLibs.texturesPath + "dustLead");
        dustBronze = new ModIngots("dustBronze").setTextureName(ModLibs.texturesPath + "dustBronze");
        dustPlatinum = new ModIngots("dustPlatinum").setTextureName(ModLibs.texturesPath + "dustPlatinum");
    }

    public static void registry()
    {
        GameRegistry.registerItem(ingotCopper, "ingotCopper");
        GameRegistry.registerItem(ingotTin, "ingotTin");
        GameRegistry.registerItem(ingotSilver, "ingotSilver");
        GameRegistry.registerItem(ingotLead, "ingotLead");
        GameRegistry.registerItem(ingotBronze, "ingotBronze");
        GameRegistry.registerItem(ingotPlatinum, "ingotPlatinum");

        GameRegistry.registerItem(dustCopper, "dustCopper");
        GameRegistry.registerItem(dustTin, "dustTin");
        GameRegistry.registerItem(dustSilver, "dustSilver");
        GameRegistry.registerItem(dustLead, "dustLead");
        GameRegistry.registerItem(dustBronze, "dustBronze");
        GameRegistry.registerItem(dustPlatinum, "dustPlatinum");
    }
}
