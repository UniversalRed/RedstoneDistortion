package redstonedistortion.common.energy.tiles;

import net.minecraft.nbt.NBTTagCompound;
import redstonedistortion.bases.tiles.TileCell;
import redstonedistortion.bases.libs.ModLibs;

/**
 * Created by UniversalRed on 15-03-17.
 */
public class TileIronCell extends TileCell {

    public TileIronCell() {
        super(ModLibs.cellIronCapacity, ModLibs.cellIronCapacity / 10);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (worldObj.isRemote) {
            return;
        }
        if (energy < 0) {
            energy = 0;
        }
        if(capacity == 0) {
            return;
        }
        outputEnergy();
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
    }
}
