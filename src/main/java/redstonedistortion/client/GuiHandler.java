package redstonedistortion.client;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import redstonedistortion.ModRedstoneDistortion;
import redstonedistortion.client.containers.ContainerMechanicalDesolator;
import redstonedistortion.client.containers.ContainerMechanicalFurnace;
import redstonedistortion.client.containers.ContainerMechanicalTransfuser;
import redstonedistortion.client.guis.GuiMechanicalDesolator;
import redstonedistortion.client.guis.GuiMechanicalFurnace;
import redstonedistortion.client.guis.GuiMechanicalTransfuser;
import redstonedistortion.common.tiles.factory.TileMechanicalDesolator;
import redstonedistortion.common.tiles.factory.TileMechanicalFurnace;
import redstonedistortion.common.tiles.factory.TileMechanicalTransfuser;
import redstonedistortion.bases.libs.ModLibs;

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

            case ModLibs.guiMechanicalTransfuser:
                if (te instanceof TileMechanicalTransfuser)
                    return new ContainerMechanicalTransfuser(player.inventory, (TileMechanicalTransfuser) te);

            case ModLibs.guiMechanicalDesolator:
                if (te instanceof TileMechanicalDesolator)
                    return new ContainerMechanicalDesolator(player.inventory, (TileMechanicalDesolator) te);
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

            case ModLibs.guiMechanicalTransfuser:
                if (te instanceof TileMechanicalTransfuser)
                    return new GuiMechanicalTransfuser(player.inventory, (TileMechanicalTransfuser) te);

            case ModLibs.guiMechanicalDesolator:
                if (te instanceof TileMechanicalDesolator)
                    return new GuiMechanicalDesolator(player.inventory, (TileMechanicalDesolator) te);
        }
        return null;
    }
}