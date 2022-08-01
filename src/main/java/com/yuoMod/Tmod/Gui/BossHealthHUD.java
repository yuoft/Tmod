package com.yuoMod.Tmod.Gui;

import com.yuoMod.Tmod.Tmod;
import com.yuoMod.Tmod.Entity.EntityKiana;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//�ͻ���ע�� GUI����
@SideOnly(Side.CLIENT)
public class BossHealthHUD extends GuiScreen {
    private static final String TEXTURE_PATH = Tmod.MOD_ID + ":" + "textures/gui/boss_health.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);

    //	protected EntityPlayer player;
    private final EntityKiana kiana;
    private final Minecraft mc;
    private final int xSize = 173;
    private final int ySize = 27;
    protected FontRenderer fontRenderer;

    public BossHealthHUD(EntityKiana kiana) {
        super();
        this.mc = Minecraft.getMinecraft();
//        this.player=mc.player;
        this.kiana = kiana;
    }

    public void drawHud() {
        //ȷ�����Ƴ�������ɫ��������
        GlStateManager.color(1.0F, 1.0F, 1.0F);

        //ʹguiλ�ڽ����м�
        this.mc.getTextureManager().bindTexture(TEXTURE);
        ScaledResolution r = new ScaledResolution(this.mc);
        int offsetX = (r.getScaledWidth() - this.xSize) / 2;
//        this.drawTexturedModalRect(offsetX, 0, 0, 0, this.xSize, this.ySize);
        Gui.drawModalRectWithCustomSizedTexture(offsetX, 0, 0, 0, xSize, ySize, 256, 256);
        //���ƽ����� 
        float health = this.kiana.getHealth();
        int textureWidth = (int) Math.ceil((health / this.kiana.getMaxHealth()) * 165);
//        this.drawTexturedModalRect(offsetX, 0, 4, 19, textureWidth, 8);
        Gui.drawModalRectWithCustomSizedTexture(offsetX + 4, 15, 4, 30, textureWidth, 8, 256, 256);
        //info
//        String text = "Kiana:"+this.kiana.getHealth()+"/"+this.kiana.getMaxHealth();
//        fontRenderer.drawString(text, 4, 4, 0x000000);
    }
}
