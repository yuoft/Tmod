package com.yuo.Tmod.Gui;

import com.yuo.Tmod.Tmod;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//客户端注解 GUI绘制
@SideOnly(Side.CLIENT)
public class GuiContainerDemo extends GuiContainer {
    private static final String TEXTURE_PATH = Tmod.MOD_ID + ":" + "textures/gui/power_extractor.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);

    private final InventoryPlayer playerInventory;
    private final IInventory inventory;

    public GuiContainerDemo(InventoryPlayer playerInv, IInventory furnaceInv) {
        super(new ContainerDemo(playerInv, furnaceInv));
        this.xSize = 176;
        this.ySize = 166;
        this.playerInventory = playerInv;
        this.inventory = furnaceInv;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
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
        int burnTime = this.inventory.getField(0);
        int totalTime = this.inventory.getField(1);
        int exp = this.inventory.getField(2);
        if (burnTime > 0) {
            int textureWidth = (int) Math.ceil(34.0 * burnTime / totalTime);
            this.drawTexturedModalRect(offsetX + 73, offsetY + 23, 176, 0, textureWidth, 40);
        }
        int expIn = (int) Math.ceil(49.0 * exp / 10);
        if (expIn > 0)
            this.drawTexturedModalRect(offsetX + 42, offsetY + 18 + 49 - expIn, 210, 49 - expIn, 3, expIn);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = this.inventory.getDisplayName().getUnformattedText();
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString("burn:" + this.inventory.getField(0),  8, 16, 4210752);
        this.fontRenderer.drawString("total:" + this.inventory.getField(1),  8, 26, 4210752);
        this.fontRenderer.drawString("exp:" + this.inventory.getField(2),  8, 36, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }
}
