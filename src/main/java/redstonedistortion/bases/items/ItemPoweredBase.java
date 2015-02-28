package redstonedistortion.bases.items;

import cofh.api.energy.IEnergyContainerItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by UniversalRed on 15-02-28.
 */
public class ItemPoweredBase extends ItemSword implements IEnergyContainerItem {
    public int capacity;
    public int maxRecieve;
    public int maxExtract;
    public int energy;

    public ItemPoweredBase(ToolMaterial material) {
        super(ToolMaterial.EMERALD);
    }

    public void readFromNBT(NBTTagCompound tag) {
        tag.setInteger("capacity", capacity);
        tag.setInteger("maxRecieve", maxRecieve);
        tag.setInteger("maxExtract", maxExtract);

    }

    public void writeToNBT(NBTTagCompound tag) {
        capacity = tag.getInteger("capacity");
        maxRecieve = tag.getInteger("maxRecieve");
        maxExtract = tag.getInteger("maxExtract");
    }

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        this.maxRecieve = maxReceive;

        if (energy < 0) {
            return 0;
        }
        int recieved = maxReceive;
        if (recieved > capacity - energy)
            recieved = capacity - energy;
        if (recieved > maxReceive)
            recieved = maxReceive;
        if (!simulate) {
            energy += recieved;
        }
        return recieved;
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        this.maxExtract = maxExtract;

        if (energy < 0) {
            return 0;
        }
        int extracted = maxExtract;
        if (extracted > energy)
            extracted = energy;
        if (extracted > maxExtract)
            extracted = maxExtract;
        if (!simulate)
            energy -= extracted;
        return extracted;
    }

    @Override
    public int getEnergyStored(ItemStack container) {
        return energy;
    }

    @Override
    public int getMaxEnergyStored(ItemStack container) {
        return capacity;
    }
}
