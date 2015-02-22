package redstonedistortion.block.blocks;

import redstonedistortion.bases.tiles.TileSolarPanel;
import redstonedistortion.libs.ModLibs;

/**
 * Created by UniversalRed on 15-02-21.
 */
public class TileSolarPanelTier1 extends TileSolarPanel
{
    public int capacity = ModLibs.tier1SolarPanelCapacity;
    public int maxExtract = ModLibs.machineExtract;

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
}
