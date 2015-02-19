package redstonedistortion.factory.guis;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import redstonedistortion.ModRedstoneDistortion;
import redstonedistortion.bases.containers.ContainerCell;
import redstonedistortion.bases.guis.GuiCell;
import redstonedistortion.bases.tiles.TileCell;
import redstonedistortion.factory.containers.ContainerMechanicalFurnace;
import redstonedistortion.factory.tiles.TileMechanicalFurnace;
import redstonedistortion.libs.ModLibs;

public class GuiHandler implements IGuiHandler {

    public GuiHandler() {
        NetworkRegistry.INSTANCE.registerGuiHandler(ModRedstoneDistortion.instance, this);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        TileEntity te = world.getTileEntity(x, y, z);

        switch (ID) {
            case ModLibs.guiMechanicalFurnace:
                if (te instanceof TileMechanicalFurnace)
                    return new ContainerMechanicalFurnace(player.inventory, (TileMechanicalFurnace) te);

            case ModLibs.guiCell:
                if (te instanceof TileCell)
                    return new ContainerCell(player.inventory, (TileCell) te);

        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        TileEntity te = world.getTileEntity(x, y, z);

        switch (ID) {
            case ModLibs.guiMechanicalFurnace:
                if (te instanceof TileMechanicalFurnace)
                    return new GuiMechanicalFurnace(player.inventory, (TileMechanicalFurnace) te);

            case ModLibs.guiCell:
                if (te instanceof TileCell)
                    return new GuiCell(player.inventory, (TileCell) te);
        }
        return null;
    }
}