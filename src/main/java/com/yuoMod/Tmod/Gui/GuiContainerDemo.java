package com.yuoMod.Tmod.Gui;

import com.yuoMod.Tmod.Tmod;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//�ͻ���ע�� GUI����
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
        //ȷ�����Ƴ�������ɫ��������
        GlStateManager.color(1.0F, 1.0F, 1.0F);

        //ʹguiλ�ڽ����м�
        this.mc.getTextureManager().bindTexture(TEXTURE);
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);
        //���ƽ�����
        int burnTime = this.inventory.getField(0);
        if (burnTime > 0) {
            int textureWidth = 1 + (int) Math.ceil(22.0 * burnTime / 160);
            this.drawTexturedModalRect(offsetX + 79, offsetY + 35, 176, 0, textureWidth, 17);
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
//		this.drawVerticalLine(30, 19, 36, 0xFF000000);
//        this.drawHorizontalLine(8, 167, 43, 0xFF000000);
        //gui��������
//        String title = I18n.format("container.tmod.gui".toString());
//        this.fontRenderer.drawString(title, (this.xSize - this.fontRenderer.getStringWidth(title)) / 2, 6, 0x404040);
        //��Ʒ��Ӱ
//        ItemStack item = new ItemStack(itemLoader.emerald_tree);
//        this.itemRender.renderItemAndEffectIntoGUI(item, 56, 17);
//        ItemStack item1 = new ItemStack(Items.DIAMOND);
//        this.itemRender.renderItemAndEffectIntoGUI(item1, 56, 53);

        String s = this.inventory.getDisplayName().getUnformattedText();
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);

    }
}
