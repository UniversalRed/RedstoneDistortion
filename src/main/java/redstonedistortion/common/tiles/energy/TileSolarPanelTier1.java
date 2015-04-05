package redstonedistortion.common.tiles.energy;


import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import redstonedistortion.bases.tiles.TileProvider;

/**
 * Created by UniversalRed on 15-02-21.
 */
public class TileSolarPanelTier1 extends TileProvider {
    public int capacity = 32000;
    public int maxExtract = 1000;
    public int sunMultiplier;
    public boolean isSunny, isRaining;

    public TileSolarPanelTier1() {
        super(40000, 1000);
        isSunny = false;
        isRaining = false;
        energy = 0;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (worldObj.isRemote) {
            return;
        }

        if (worldObj.isDaytime()) {
            isSunny = true;
        } else {
            return;
        }

        if (energy < 0) {
            energy = 0;
        }

        if (energy >= capacity) {
            energy = capacity;
        }

        getEnergyProduction();
    }

    public void getEnergyProduction() {
        TileEntity tile = worldObj.getTileEntity(getX(), getY(), getZ());
        if (tile instanceof TileProvider && worldObj.canBlockSeeTheSky(getX(), getY() + 1, getZ())) {
            if (isSunny == true) {
                int energyMade;

                if (worldObj.isRaining()) {
                    energyMade = 1;
                }

                energyMade = 2;
                energy = energy + energyMade;
                if (energy >= capacity) {
                    energy = capacity;
                }
                System.out.println(energy);
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        configurations.readFromNBT(tag);
        tag.setInteger("energy", energy);
        tag.setInteger("capacity", capacity);
        tag.setInteger("maxExtract", maxExtract);

    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        configurations.writeToNBT(tag);
        energy = tag.getInteger("energy");
        maxExtract = tag.getInteger("maxExtract");
        capacity = tag.getInteger("capacity");
    }

    @Override
    public void writeToByteBuff(ByteBuf buf) {
        super.writeToByteBuff(buf);
        buf.writeInt(energy);
        configurations.writeToByteBuff(buf);
    }

    @Override
    public void readFromByteBuff(ByteBuf buf) {
        super.readFromByteBuff(buf);
        energy = buf.readInt();
        configurations.readFromByteBuff(buf);
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return from != ForgeDirection.UP && configurations.canSend(from);
    }
}
