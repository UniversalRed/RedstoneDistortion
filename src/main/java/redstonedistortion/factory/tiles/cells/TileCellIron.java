package redstonedistortion.factory.tiles.cells;

import redstonedistortion.bases.tiles.TileCell;
import redstonedistortion.libs.ModLibs;

/**
 * Created by UniversalRed on 15-02-17.
 */
public class TileCellIron extends TileCell
{
    public int maxEnergy;
    public int maxInput;
    public int maxOutput;

    public TileCellIron() {

    }

    public TileCellIron(int maxEnergy, int maxInput, int maxOutput) {
        super(ModLibs.cellIronCapacity, ModLibs.machineRecieve, ModLibs.machineExtract);
        this.maxEnergy = maxEnergy;
        this.maxInput = maxInput;
        this.maxOutput = maxOutput;
    }

    @Override
    public void updateEntity() {
        if (worldObj.isRemote)
            return;

        super.updateEntity();
        if (maxEnergy == 0)
            return;
    }
}
