package com.yuoMod.Tmod.Potion;

import com.yuoMod.Tmod.tmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class potionFall extends Potion
{
	private static final ResourceLocation potionfall = new ResourceLocation(tmod.MODID + ":" + "textures/gui/potionfall.png");

    public potionFall(String name,boolean isBadEffectIn,int liquidColorIn)
    {
        super(false, 0x7F0000);
        this.setPotionName(name);
//        this.setIconIndex(2, 1);//ԭ��ͼ��λ��
    }
    // func_76400_d
    // ���� false ʱ���� Forge patch ����߼��������� renderInventoryEffect �� renderHUDEffect
    @Override
    public boolean hasStatusIcon() {
        return false;
    }

    @Override
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
        // �����߼�����ֱ�ӽ��� renderHUDEffect����ʱ alpha = 1F
    	mc.getTextureManager().bindTexture(potionfall);
        mc.currentScreen.drawTexturedModalRect(x+7, y+7, 0, 0, 18, 18);
        this.renderHUDEffect(0, 0, effect, mc, 1F);
    }

    @Override
    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
        // �����߼�
        mc.getTextureManager().bindTexture(potionfall);
        // func_146110_a
        // x, y Ϊ���Ƶ���㣬u, v Ϊ�������㣬w, h Ϊѡȡ������Ŀ�͸ߣ�texW �� texH ������������Ŀ�͸�
        Gui.drawModalRectWithCustomSizedTexture(x+3, y+3, 0, 0, 18, 18, 256, 256);
    }
}
