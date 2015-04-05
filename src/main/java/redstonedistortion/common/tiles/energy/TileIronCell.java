package redstonedistortion.common.tiles.energy;

import buildcraftAdditions.api.networking.ISynchronizedTile;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import redstonedistortion.bases.tiles.TileCell;
import redstonedistortion.bases.libs.ModLibs;

/**
 * Created by UniversalRed on 15-03-17.
 */
public class TileIronCell extends TileCell implements ISynchronizedTile {

    public int capacity = 100000;

    public TileIronCell() {
        super(100000, 10000);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if(worldObj.isRemote) {
            return;
        }

        if(energy < 0) {
            energy = 0;
        }

        if(energy > capacity) {
            energy = capacity;
        }

        outputEnergy();
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
    }

    @Override
    public void writeToByteBuff(ByteBuf buf) {
        super.writeToByteBuff(buf);
    }

    @Override
    public void readFromByteBuff(ByteBuf buf) {
        super.readFromByteBuff(buf);
    }

    @Override
    public int energyLoss() {
        return 0;
    }
}
