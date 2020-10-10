package com.yuoMod.Tmod.Gui;

import com.yuoMod.Tmod.tmod;
import com.yuoMod.Tmod.Common.Items.itemLoader;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//�ͻ���ע��
@SideOnly(Side.CLIENT)
public class GuiContainerDemo extends GuiContainer
{
	private static final String TEXTURE_PATH = tmod.MODID + ":" + "textures/gui/power_extractor.png";
	private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);

	public GuiContainerDemo(ContainerDemo inventorySlotsIn) {
		super(inventorySlotsIn);
		this.xSize = 176;
        this.ySize = 166;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		//ȷ�����Ƴ�������ɫ��������
		GlStateManager.color(1.0F, 1.0F, 1.0F);

		//ʹguiλ�ڽ����м�
        this.mc.getTextureManager().bindTexture(TEXTURE);
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;

        this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
//		this.drawVerticalLine(30, 19, 36, 0xFF000000);
//        this.drawHorizontalLine(8, 167, 43, 0xFF000000);
		//gui��������
        String title = I18n.format("container.tmod.gui");
        this.fontRenderer.drawString(title, (this.xSize - this.fontRenderer.getStringWidth(title)) / 2, 6, 0x404040);
        //��Ʒ��Ӱ
        ItemStack item = new ItemStack(itemLoader.emerald_tree);
        this.itemRender.renderItemAndEffectIntoGUI(item, 56, 17);
        ItemStack item1 = new ItemStack(Items.DIAMOND);
        this.itemRender.renderItemAndEffectIntoGUI(item1, 56, 53);
	}
}
