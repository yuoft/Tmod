package com.yuo.Tmod.Potion;

import com.yuo.Tmod.Tmod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class Frozen extends Potion {
    private static final ResourceLocation potionfall = new ResourceLocation(Tmod.MOD_ID + ":" + "textures/gui/effect.png");

    public Frozen() {
        super(true, 0xffffff);
        this.setPotionName("frozen");
    }

    @Override
    public boolean hasStatusIcon() {
        return false;
    }

    @Override
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
        // 绘制逻辑，可直接交给 renderHUDEffect，此时 alpha = 1F
        mc.getTextureManager().bindTexture(potionfall);
        mc.currentScreen.drawTexturedModalRect(x + 7, y + 7, 0, 0, 64, 64);
        this.renderHUDEffect(0, 0, effect, mc, 1F);
    }

    @Override
    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
        // 绘制逻辑
        mc.getTextureManager().bindTexture(potionfall);
        // func_146110_a
        // x, y 为绘制的起点，u, v 为纹理的起点，w, h 为选取的纹理的宽和高，texW 和 texH 代表整张纹理的宽和高
        Gui.drawModalRectWithCustomSizedTexture(x + 18, y + 18, 0, 0, 64, 64, 256, 256);
    }
}
