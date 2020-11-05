package com.yuoMod.Tmod.Entity.render;

import com.yuoMod.Tmod.tmod;
import com.yuoMod.Tmod.Entity.EntityGoldenChicken;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
/**
 * ʵ����Ⱦ
 * @author Lenovo
 *
 */
@SideOnly(Side.CLIENT)
public class RenderGoldenChicken extends RenderLiving<EntityGoldenChicken>
{
    private static final ResourceLocation GOLDEN_CHICKEN_TEXTURE = new ResourceLocation(tmod.MODID + ":" + "textures/entity/golden_chicken.png");

    /**
     * ��һ��������ʾ����Minecraft����Ⱦ������������������������Ϳ����ˣ����Ǹո�Ҳע�⵽�ˣ���ע���ʱ����ȷʵ��Ϊ������
     * ��Minecraft.getMinecraft().getRenderManager()�����������Ĺ��췽����������������ʵ��ʾ������Ӱ��С��������Ĭ�ϵ�0.5�Ϳ�����
     * �ڶ���������ʾ�ģ��������ʵ�������ʵ����Ⱦģ���ˣ�����������ʱ�����ü�����Ⱦģ�ʹ��棬���Ժ����ǻὲ�����ȥ�Զ������ģ�ͣ�
     * ����ʵҲ����һ���ֵ��ص㡣
     * @param renderManager
     */
    public RenderGoldenChicken(RenderManager renderManager)
    {
        super(renderManager, new ModelChicken(), 0.5F);
    }
    //�������ǿ��Լ�һЩ������Ҫ�ı任����
    @Override
    protected void preRenderCallback(EntityGoldenChicken entity, float partialTickTime)
    {
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
    }
    //�������ָ������Ⱦ�Ĳ���
    @Override
    protected ResourceLocation getEntityTexture(EntityGoldenChicken entity)
    {
        return RenderGoldenChicken.GOLDEN_CHICKEN_TEXTURE;
    }
    //ʵ��������ת���Ƕ�,�����partialTicks������tick����С������
    @Override
    public void doRender(EntityGoldenChicken entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}