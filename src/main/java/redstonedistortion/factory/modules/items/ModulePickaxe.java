package redstonedistortion.factory.modules.items;

import cofh.lib.util.helpers.EnergyHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import redstonedistortion.factory.base.BaseModule;

public class ModulePickaxe extends BaseModule
{
    public ModulePickaxe(Item item, String name, String forToolName, Item.ToolMaterial toolMaterial) {
        super(item, name, forToolName, toolMaterial);
        damage = 2;
        toolFunction(toolMaterial, 4);
    }

    public void toolFunction(ToolMaterial toolMaterial, int harvestLevel)
    {
        toolMaterial = ToolMaterial.EMERALD;

        if(toolMaterial == ToolMaterial.EMERALD)
        {
            toolMaterial.getHarvestLevel();
        }
    }

    @Override
    public float getDigSpeed(ItemStack stack, Block block, int meta)
    {
        if (getEnergyStored(stack) < energyPerUse)
        {
            return 1.0F;
        }
        return super.getDigSpeed(stack, block, meta);
    }


    public void writeToNBT(NBTTagCompound tag)
    {
        writeToNBT(tag);
    }

    public void readFromNBT(NBTTagCompound tag)
    {
        readFromNBT(tag);
    }

}
