package redstonedistortion.factory.items;

import redstonedistortion.factory.base.BasePowerContainer;

public class ItemPowerTool extends BasePowerContainer
{
    public int capacity;

    public ItemPowerTool(int capacity, int maxReceive, int maxExtract, String name) {
        super(capacity, maxReceive, maxExtract, name);
        this.capacity = capacity;
    }
}
