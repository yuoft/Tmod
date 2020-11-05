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

//客户端注解 GUI绘制
@SideOnly(Side.CLIENT)
public class GuiContainerDemo extends GuiContainer
{
	private static final String TEXTURE_PATH = tmod.MODID + ":" + "textures/gui/power_extractor.png";
	private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);

	protected ContainerDemo inventory;
	private int totalBurnTime;
	
	public GuiContainerDemo(ContainerDemo inventorySlotsIn) {
		super(inventorySlotsIn);
		this.xSize = 176;
        this.ySize = 166;
        this.inventory=inventorySlotsIn;
        this.totalBurnTime = inventorySlotsIn.getTotalBurnTime();
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		//确保绘制出来的颜色是正常的
		GlStateManager.color(1.0F, 1.0F, 1.0F);

		//使gui位于界面中间
        this.mc.getTextureManager().bindTexture(TEXTURE);
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);
        //绘制进度条 
        int burnTime = this.inventory.getBurnTime();
        int textureWidth = 1 + (int) Math.ceil(22.0 * burnTime / this.totalBurnTime);
        this.drawTexturedModalRect(offsetX + 79, offsetY + 35, 176, 0, textureWidth, 17);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
//		this.drawVerticalLine(30, 19, 36, 0xFF000000);
//        this.drawHorizontalLine(8, 167, 43, 0xFF000000);
		//gui界面名称
        String title = I18n.format("container.tmod.gui");
        this.fontRenderer.drawString(title, (this.xSize - this.fontRenderer.getStringWidth(title)) / 2, 6, 0x404040);
        //物品虚影
        ItemStack item = new ItemStack(itemLoader.emerald_tree);
        this.itemRender.renderItemAndEffectIntoGUI(item, 56, 17);
        ItemStack item1 = new ItemStack(Items.DIAMOND);
        this.itemRender.renderItemAndEffectIntoGUI(item1, 56, 53);
	}
}
