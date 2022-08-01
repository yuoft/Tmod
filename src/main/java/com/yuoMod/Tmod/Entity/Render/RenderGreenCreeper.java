package com.yuoMod.Tmod.Entity.Render;

import com.yuoMod.Tmod.Tmod;
import com.yuoMod.Tmod.Entity.EntityGreenCreeper;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderGreenCreeper extends RenderLiving<EntityGreenCreeper> {
    private static final ResourceLocation GREEN_CREEPER_TEXTURE = new ResourceLocation(Tmod.MOD_ID , "textures/entity/green_creeper.png");

    //mc����Ⱦ��������ʵ��ģ�ͣ���Ӱ��С
    public RenderGreenCreeper(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelCreeper(), 0.5f);
    }

    //�������ǿ��Լ�һЩ������Ҫ�ı任����
    @Override
    protected void preRenderCallback(EntityGreenCreeper entity, float partialTickTime) {
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
    }

    //ָ����Ⱦ�Ĳ���
    @Override
    protected ResourceLocation getEntityTexture(EntityGreenCreeper entity) {
        return RenderGreenCreeper.GREEN_CREEPER_TEXTURE;
    }

    //ʵ��������ת���Ƕ�,�����partialTicks������tick����С������
    @Override
    public void doRender(EntityGreenCreeper entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

}
