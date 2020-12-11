package com.yuoMod.Tmod.Entity.Render;

import com.yuoMod.Tmod.tmod;
import com.yuoMod.Tmod.Entity.EntityNewAlex;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderNewAlex extends RenderLiving<EntityNewAlex>
{
	private static final ResourceLocation NEW_ALEX_TEXTURE = new ResourceLocation(tmod.MODID + ":" + "textures/entity/new_alex.png");
	//mc����Ⱦ��������ʵ��ģ�ͣ���Ӱ��С
	public RenderNewAlex(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelZombie(), 0.5f);
	}
	//�������ǿ��Լ�һЩ������Ҫ�ı任����
    @Override
    protected void preRenderCallback(EntityNewAlex entity, float partialTickTime)
    {
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
    }
	//ָ����Ⱦ�Ĳ���
	@Override
	protected ResourceLocation getEntityTexture(EntityNewAlex entity) {
		return RenderNewAlex.NEW_ALEX_TEXTURE;
	}
	//ʵ��������ת���Ƕ�,�����partialTicks������tick����С������
	@Override
    public void doRender(EntityNewAlex entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
	
}
