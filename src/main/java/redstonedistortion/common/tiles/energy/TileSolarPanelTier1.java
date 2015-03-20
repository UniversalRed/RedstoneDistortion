package redstonedistortion.common.tiles.energy;


import redstonedistortion.bases.tiles.TileBase;
import redstonedistortion.bases.libs.ModLibs;

/**
 * Created by UniversalRed on 15-02-21.
 */
public class TileSolarPanelTier1 extends TileBase
{

    public int capacity = ModLibs.tier1SolarPanelCapacity;
    public int maxExtract = ModLibs.machineExtract;
    public int energy;

    /*
    public TileSolarPanelTier1(int capacity, int maxExtract) {
        super(capacity, maxExtract);
        this.capacity = capacity;
        this.maxExtract = maxExtract;
    }

    public TileSolarPanelTier1()
    {

    }

    @Override
    public void updateEntity() {
        super.updateEntity();
    }
    */
    @Override
    public int energyLoss() {
        return energy = 0;
    }
}
