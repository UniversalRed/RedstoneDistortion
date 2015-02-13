package redstonedistortion.factory.base;

import cofh.api.block.IBlockInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

import cofh.api.energy.IEnergyReceiver;

import java.util.List;

public class TileMachine extends TileEntity implements IEnergyReceiver, IBlockInfo {
    protected int energy;
    private int maxEnergy;

    protected TileMachine(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        energy = tag.getInteger("energy");
        readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        tag.setInteger("energy", energy);
        writeToNBT(tag);
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        if (energy >= maxEnergy)
            return 0;
        int energyRecieved = maxReceive;
        if (energyRecieved > maxEnergy - energy)
            energyRecieved = maxEnergy - energy;
        if (!simulate)
            energy += energyRecieved;
        return energyRecieved;
    }


    @Override
    public int getEnergyStored(ForgeDirection from) {
        return energy;
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        return maxEnergy;
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return true;
    }

    //IBlockInfo
    @Override
    public void getBlockInfo(IBlockAccess world, int x, int y, int z, ForgeDirection side, EntityPlayer player, List<IChatComponent> info, boolean debug) {
        getBlockInfo(world, x, y, z, side, player, info, true);
    }
}
