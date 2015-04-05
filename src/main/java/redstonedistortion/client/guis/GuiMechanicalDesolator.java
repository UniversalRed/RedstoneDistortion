package redstonedistortion.client.guis;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import redstonedistortion.client.containers.ContainerMechanicalDesolator;
import redstonedistortion.common.tiles.factory.TileMechanicalDesolator;

/**
 * Created by UniversalRed on 15-02-21.
 */
@SideOnly(Side.CLIENT)
public class GuiMechanicalDesolator extends GuiContainer
{
    private static final ResourceLocation textureBackground = new ResourceLocation("reddistortion", "textures/gui/guiMechanicalFurnace.png");
    private TileMechanicalDesolator te;

    public GuiMechanicalDesolator(InventoryPlayer invPlayer, TileMechanicalDesolator tile) {
        super(new ContainerMechanicalDesolator(invPlayer, tile));

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

        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, te.getProgress(), 16);
    }
}
