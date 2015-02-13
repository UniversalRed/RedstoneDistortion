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
        ingotCopper = new ModIngots("ingotCopper").setTextureName("ingotCopper");
        ingotTin = new ModIngots("ingotTin").setTextureName("ingotTin");
        ingotSilver = new ModIngots("ingotSilver").setTextureName("ingotSilver");
        ingotLead = new ModIngots("ingotLead").setTextureName("ingotLead");
        ingotBronze = new ModIngots("ingotBronze").setTextureName("ingotBronze");
        ingotPlatinum = new ModIngots("ingotPlatinum").setTextureName("ingotPlatinum");

        //Dusts
        dustCopper = new ModIngots("dustCopper").setTextureName("dustCopper");
        dustTin = new ModIngots("dustTin").setTextureName("dustTin");
        dustSilver = new ModIngots("dustSilver").setTextureName("dustSilver");
        dustLead = new ModIngots("dustLead").setTextureName("dustLead");
        dustBronze = new ModIngots("dustBronze").setTextureName("dustBronze");
        dustPlatinum = new ModIngots("dustPlatinum").setTextureName("dustPlatinum");
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
