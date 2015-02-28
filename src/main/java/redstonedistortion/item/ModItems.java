package redstonedistortion.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.*;
import net.minecraftforge.common.util.EnumHelper;
import redstonedistortion.bases.items.*;
import redstonedistortion.core.configurations.ConfigHandler;
import redstonedistortion.integration.thermalexpansion.ThermalExpansionIntegration;
import redstonedistortion.item.items.*;

public class ModItems
{
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

    public static Item RDHelmet;
    public static Item RDChestplate;
    public static Item RDLeggings;
    public static Item RDBoots;

    public static Item powerPickaxe;
    public static Item powerAxe;
    public static Item powerShovel;
    public static Item powerSword;

    public static final Item.ToolMaterial TOOL_RD_MATERIAL = EnumHelper.addToolMaterial("RD_Tool", 3, 100, 8.0F, 0, 25);
    public static final ItemArmor.ArmorMaterial ARMOUR_RD_MATERIAL = EnumHelper.addArmorMaterial("RD_Armour", 10, new int[] { 3, 8, 6, 3 }, 20);
    public static final String[] TEXTURES_ARMOUR = { "redstonearsenal:textures/armor/" + "Flux_1.png", "redstonearsenal:textures/armor/" + "Flux_2.png" };

    public static void init()
    {
        if(!ThermalExpansionIntegration.toggleOres) {
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
        }

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

    public static void registry()
    {
        if (!ThermalExpansionIntegration.toggleOres) {
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
        }

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
