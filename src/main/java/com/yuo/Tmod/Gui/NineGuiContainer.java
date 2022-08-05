package com.yuo.Tmod.Gui;

import com.yuo.Tmod.Tmod;
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
//	@Override
//    public void initGui()
//    {
//        super.initGui();
//        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
//        //添加按钮
//        /*
//         * GuiButton的构造方法的第一个参数传入的就是特定的ID，第二个和第三个参数传入其在guis上的横纵坐标，第四个和第五个参数传入其长宽
//           GuiButton的构造方法的最后一个参数本应该传入显示文本，但因为整个贴图的渲染都已经被重写，所以这里传入空字符串
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
    //按钮按下事件
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
