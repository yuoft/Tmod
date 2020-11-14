package com.yuoMod.Tmod.Entity.render;

import com.yuoMod.Tmod.tmod;
import com.yuoMod.Tmod.Entity.EntityGreenEnderman;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderGreenEnderman extends RenderLiving<EntityGreenEnderman>
{
	private static final ResourceLocation GREEN_ENDERMAN_TEXTURE = new ResourceLocation(tmod.MODID + ":" + "textures/entity/green_enderman.png");
	//mc����Ⱦ��������ʵ��ģ�ͣ���Ӱ��С
	public RenderGreenEnderman(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelEnderman(0.0f), 0.5f);
	}
	//�������ǿ��Լ�һЩ������Ҫ�ı任����
    @Override
    protected void preRenderCallback(EntityGreenEnderman entity, float partialTickTime)
    {
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
    }
	//ָ����Ⱦ�Ĳ���
	@Override
	protected ResourceLocation getEntityTexture(EntityGreenEnderman entity) {
		return RenderGreenEnderman.GREEN_ENDERMAN_TEXTURE;
	}
	//ʵ��������ת���Ƕ�,�����partialTicks������tick����С������
	@Override
    public void doRender(EntityGreenEnderman entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
	
}
