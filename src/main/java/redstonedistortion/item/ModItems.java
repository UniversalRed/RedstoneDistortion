package redstonedistortion.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import redstonedistortion.bases.items.BaseItems;
import redstonedistortion.bases.items.ModIngots;
import redstonedistortion.item.items.ItemWrench;

public class ModItems {
    public static Item ingotCopper;
    public static Item ingotTin;
    public static Item ingotSilver;
    public static Item ingotLead;
    public static Item ingotBronze;
    public static Item dustCopper;
    public static Item dustTin;
    public static Item dustSilver;
    public static Item dustLead;
    public static Item dustBronze;
    public static Item dustIron;
    public static Item dustGold;
    public static Item plateCopper;
    public static Item plateTin;
    public static Item plateSilver;
    public static Item plateLead;
    public static Item plateBronze;
    public static Item plateIron;
    public static Item plateGold;
    public static Item itemWrench;

    public static void init() {
        //Ingots
        ingotCopper = new ModIngots("ingotCopper");
        ingotTin = new ModIngots("ingotTin");
        ingotSilver = new ModIngots("ingotSilver");
        ingotLead = new ModIngots("ingotLead");
        ingotBronze = new ModIngots("ingotBronze");

        //Dusts
        dustCopper = new ModIngots("dustCopper");
        dustTin = new ModIngots("dustTin");
        dustSilver = new ModIngots("dustSilver");
        dustLead = new ModIngots("dustLead");
        dustBronze = new ModIngots("dustBronze");
        dustIron = new ModIngots("dustIron");
        dustGold = new ModIngots("dustGold");

        plateCopper = new BaseItems("plateCopper");
        plateTin = new BaseItems("plateTin");
        plateSilver = new BaseItems("plateSilver");
        plateLead = new BaseItems("plateLead");
        plateBronze = new BaseItems("plateBronze");
        plateIron = new BaseItems("plateIron");
        plateGold = new BaseItems("plateGold");

        //Armour

        //Other
        itemWrench = new ItemWrench("itemWrench");
    }

    public static void registry() {
        GameRegistry.registerItem(ingotCopper, "ingotCopper");
        GameRegistry.registerItem(ingotTin, "ingotTin");
        GameRegistry.registerItem(ingotSilver, "ingotSilver");
        GameRegistry.registerItem(ingotLead, "ingotLead");
        GameRegistry.registerItem(ingotBronze, "ingotBronze");

        GameRegistry.registerItem(dustCopper, "dustCopper");
        GameRegistry.registerItem(dustTin, "dustTin");
        GameRegistry.registerItem(dustSilver, "dustSilver");
        GameRegistry.registerItem(dustLead, "dustLead");
        GameRegistry.registerItem(dustBronze, "dustBronze");
        GameRegistry.registerItem(dustIron, "dustIron");
        GameRegistry.registerItem(dustGold, "dustGold");

        GameRegistry.registerItem(plateCopper, "plateCopper");
        GameRegistry.registerItem(plateTin, "plateTin");
        GameRegistry.registerItem(plateSilver, "plateSilver");
        GameRegistry.registerItem(plateLead, "plateLead");
        GameRegistry.registerItem(plateBronze, "plateBronze");
        GameRegistry.registerItem(plateIron, "plateIron");
        GameRegistry.registerItem(plateGold, "plateGold");

        GameRegistry.registerItem(itemWrench, "itemWrench");
    }
}
