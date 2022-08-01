package com.yuoMod.Tmod.Gui;

import com.yuoMod.Tmod.Tmod;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class NineGuiContainer extends GuiContainer {
    private static final String TEXTURE_PATH = Tmod.MOD_ID + ":" + "textures/gui/nine_gui.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);

    //	private InventoryStorageRingBag Slot;
    public NineGuiContainer(NineContainer inventorySlotsIn) {
        super(inventorySlotsIn);
        this.xSize = 256;
        this.ySize = 256;
//        this.Slot = inventorySlotsIn.getBagSlot();
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
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
        String title = I18n.format("container.tmod.NineGui");
        this.fontRenderer.drawString(title, ((this.xSize - this.fontRenderer.getStringWidth(title)) / 2), 6, 0x404040);
    }
//	@Override
//    public void initGui()
//    {
//        super.initGui();
//        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
//        //��Ӱ�ť
//        /*
//         * GuiButton�Ĺ��췽���ĵ�һ����������ľ����ض���ID���ڶ����͵�����������������guis�ϵĺ������꣬���ĸ��͵�������������䳤��
//           GuiButton�Ĺ��췽�������һ��������Ӧ�ô�����ʾ�ı�������Ϊ������ͼ����Ⱦ���Ѿ�����д���������ﴫ����ַ���
//         */
//        this.buttonList.add(new GuiButton(2233, offsetX + 210, offsetY + 8, 17, 12, "")
//        {
//            @SuppressWarnings("unused")
//			public void drawButton(Minecraft mc, int mouseX, int mouseY)
//            {
//                if (this.visible)
//                {
//                    GlStateManager.color(1.0F, 1.0F, 1.0F);
//
//                    mc.getTextureManager().bindTexture(TEXTURE);
//                    int x = mouseX - offsetX, y = mouseY - offsetY;
//
//                    if (x >= 0 && y >= 0 && x < this.width && y < this.height)
//                    {
//                        this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.width, this.height);
//                    }
//                    else
//                    {
//                        this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.width, this.height);
//                    }
//                }
//            }
//        });
//    }
    //��ť�����¼�
//	@Override
//    protected void actionPerformed(GuiButton button) throws IOException
//    {
//        switch (button.id)
//        {
//        case 2233:
//            clearItems();
//            break;
//        default:
//            super.actionPerformed(button);
//            return;
//        }
//    }
//	private void clearItems()
//	{
//		this.Slot.clear();
//		this.updateScreen();
//		
//	}
}
