package com.yuoMod.Tmod.Entity.Render;

import com.yuoMod.Tmod.tmod;
import com.yuoMod.Tmod.Entity.EntityRedSkeleton;

import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderRedSkeleton extends RenderLiving<EntityRedSkeleton>
{
	private static final ResourceLocation RED_SKELETON_TEXTURE = new ResourceLocation(tmod.MODID + ":" + "textures/entity/red_skeleton.png");
	//mc����Ⱦ��������ʵ��ģ�ͣ���Ӱ��С
	public RenderRedSkeleton(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelSkeleton(), 0.5f);
	}
	//�������ǿ��Լ�һЩ������Ҫ�ı任����
    @Override
    protected void preRenderCallback(EntityRedSkeleton entity, float partialTickTime)
    {
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
    }
	//ָ����Ⱦ�Ĳ���
	@Override
	protected ResourceLocation getEntityTexture(EntityRedSkeleton entity) {
		return RenderRedSkeleton.RED_SKELETON_TEXTURE;
	}
	//ʵ��������ת���Ƕ�,�����partialTicks������tick����С������
	@Override
    public void doRender(EntityRedSkeleton entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
	
}
