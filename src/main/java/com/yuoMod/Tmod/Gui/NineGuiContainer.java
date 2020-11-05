package com.yuoMod.Tmod.Gui;

import com.yuoMod.Tmod.tmod;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class NineGuiContainer extends GuiContainer
{
	private static final String TEXTURE_PATH = tmod.MODID + ":" + "textures/gui/nine_gui.png";
	private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);
	
	public NineGuiContainer(Container inventorySlotsIn) {
		super(inventorySlotsIn);
		this.xSize = 256;
        this.ySize = 256;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		// 确保绘制出来的颜色是正常的
		GlStateManager.color(1.0F, 1.0F, 1.0F);
		// 使gui位于界面中间
		this.mc.getTextureManager().bindTexture(TEXTURE);
		int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);
	}
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		//gui界面名称
        String title = I18n.format("container.tmod.NineGui");
        this.fontRenderer.drawString(title, ((this.xSize - this.fontRenderer.getStringWidth(title)) / 2), 6, 0x404040);
	}
}
