package com.yuoMod.Tmod.Gui;

import com.yuoMod.Tmod.tmod;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ChestGuiContainer extends GuiContainer
{
	private static final String TEXTURE_PATH = tmod.MODID + ":" + "textures/gui/six_gui.png";
	private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);
	public ChestGuiContainer(ChestContainer inventorySlotsIn) {
		super(inventorySlotsIn);
		this.xSize = 178;
        this.ySize = 222;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		// ȷ�����Ƴ�������ɫ��������
		GlStateManager.color(1.0F, 1.0F, 1.0F);
		// ʹguiλ�ڽ����м�
		this.mc.getTextureManager().bindTexture(TEXTURE);
		int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);
	}
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		//gui��������
        String title = I18n.format("container.tmod.ChestGui");
        this.fontRenderer.drawString(title, 7, 6, 0x404040);
	}
}
