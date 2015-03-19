package redstonedistortion.bases.utils.helpers;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import net.minecraftforge.common.util.ForgeDirection;
/**
 * Copyright (c) 2014, AEnterprise
 * http://buildcraftadditions.wordpress.com/
 * Buildcraft Additions is distributed under the terms of GNU GPL v3.0
 * Please check the contents of the license located in
 * http://buildcraftadditions.wordpress.com/wiki/licensing-stuff/
 */
public class Location {
    public int x, y, z;
    public World world;

    public Location(World world, int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
    }

    public Location(TileEntity entity) {
        world = entity.getWorldObj();
        x = entity.xCoord;
        y = entity.yCoord;
        z = entity.zCoord;
    }

    public Location copy() {
        return new Location(world, x, y, z);
    }

    public Location move (ForgeDirection direction) {
        return move(direction, 1);
    }

    public Location move(ForgeDirection direction, int blocks){
        x += (direction.offsetX * blocks);
        y += (direction.offsetY * blocks);
        z += direction.offsetZ;
        return this;
    }

    public Location move(ForgeDirection[] directions) {
        for (ForgeDirection direction : directions)
            move(direction);
        return this;
    }

    public TileEntity getTileEntity() {
        return world.getTileEntity(x, y, z);
    }

    public Block getBlock() {
        return world.getBlock(x, y, z);
    }

    public void setMetadata(int meta) {
        world.setBlockMetadataWithNotify(x, y, z, meta, 2);
    }

    public int getMeatadata() {
        return world.getBlockMetadata(x, y, z);
    }

    public void addTileEntity(TileEntity entity) {
        world.setTileEntity(x, y, z, entity);
    }

    public void removeTileEntity() {
        world.removeTileEntity(x, y, z);
    }

    public void scheduleBlockUpdate(int delay) {
        world.scheduleBlockUpdate(x, y, z, getBlock(), delay);
    }

    public boolean isSameLocation(Location location) {
        return world.provider.dimensionId == location.world.provider.dimensionId && x == location.x && y == location.y && z == location.z;
    }

    public void neighbourUpdate() {
        world.notifyBlockOfNeighborChange(x, y, z, getBlock());
    }

    public void setBlock(Block block) {
        world.setBlock(x, y, z, block);
    }
}
