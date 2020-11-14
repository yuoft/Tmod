package com.yuoMod.Tmod.Entity.render;

import com.yuoMod.Tmod.tmod;
import com.yuoMod.Tmod.Entity.EntityGreenSkeleton;

import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderGreenSkeleton extends RenderLiving<EntityGreenSkeleton>
{
	private static final ResourceLocation GREEN_SKELETON_TEXTURE = new ResourceLocation(tmod.MODID + ":" + "textures/entity/green_skeleton.png");
	//mc的渲染管理器，实体模型，阴影大小
	public RenderGreenSkeleton(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelSkeleton(), 0.5f);
	}
	//这里我们可以加一些我们想要的变换操作
    @Override
    protected void preRenderCallback(EntityGreenSkeleton entity, float partialTickTime)
    {
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
    }
	//指定渲染的材质
	@Override
	protected ResourceLocation getEntityTexture(EntityGreenSkeleton entity) {
		return RenderGreenSkeleton.GREEN_SKELETON_TEXTURE;
	}
	//实体的坐标和转动角度,这里的partialTicks，就是tick数的小数部分
	@Override
    public void doRender(EntityGreenSkeleton entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
	
}
