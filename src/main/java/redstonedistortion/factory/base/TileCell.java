package redstonedistortion.factory.base;

import cofh.api.block.IBlockInfo;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

public class TileCell extends TileEntity implements IEnergyHandler, IBlockInfo
{
    public static int capacity;

    protected EnergyStorage storage = new EnergyStorage(capacity);

    public TileCell(int capacity)
    {
        this.capacity = capacity;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        storage.readFromNBT(nbt);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        storage.writeToNBT(nbt);
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        return storage.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        return storage.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored(ForgeDirection from) {
        return storage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        return storage.getMaxEnergyStored();
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