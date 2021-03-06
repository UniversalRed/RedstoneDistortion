package redstonedistortion.client.guis;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import redstonedistortion.client.containers.ContainerMechanicalTransfuser;
import redstonedistortion.common.tiles.factory.TileMechanicalTransfuser;

/**
 * Created by UniversalRed on 15-02-20.
 */
@SideOnly(Side.CLIENT)
public class GuiMechanicalTransfuser extends GuiContainer {
    private static final ResourceLocation textureBackground = new ResourceLocation("reddistortion", "textures/gui/guiMechanicalFurnace.png");
    private TileMechanicalTransfuser te;

    public GuiMechanicalTransfuser(InventoryPlayer invPlayer, TileMechanicalTransfuser tile) {
        super(new ContainerMechanicalTransfuser(invPlayer, tile));

        this.te = tile;

        xSize = 176;
        ySize = 185;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
        GL11.glColor4f(1, 1, 1, 1);

        Minecraft.getMinecraft().renderEngine.bindTexture(textureBackground);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, 1, 16);
    }
}