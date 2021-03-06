package redstonedistortion.client.configurations;

import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import redstonedistortion.bases.libs.ModLibs;
import redstonedistortion.bases.utils.ModUtils;

import java.util.ArrayList;
import java.util.List;

public class ConfigGui extends GuiConfig
{

    public ConfigGui(GuiScreen parentScreen) {
        super(parentScreen, getList(), ModLibs.modId, false, false, ModUtils.localize("config.title"));
    }

    public static List<IConfigElement> getList() {
        List list = new ArrayList<IConfigElement>();
        list.add(new ConfigElement(ConfigHandler.config.getCategory("misc")));
        return list;
    }
}