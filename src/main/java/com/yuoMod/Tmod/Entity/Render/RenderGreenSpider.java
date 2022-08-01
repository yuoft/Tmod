package com.yuoMod.Tmod.Entity.Render;

import com.yuoMod.Tmod.Tmod;
import com.yuoMod.Tmod.Entity.EntityGreenSpider;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderGreenSpider extends RenderLiving<EntityGreenSpider> {
    private static final ResourceLocation GREEN_SPIDER_TEXTURE = new ResourceLocation(Tmod.MOD_ID + ":" + "textures/entity/green_spider.png");

    //mc的渲染管理器，实体模型，阴影大小
    public RenderGreenSpider(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelSpider(), 0.5f);
        this.addLayer(new LayerGreenSpiderEyes(this));
    }

    //这里我们可以加一些我们想要的变换操作
    @Override
    protected void preRenderCallback(EntityGreenSpider entity, float partialTickTime) {
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
    }

    //指定渲染的材质
    @Override
    protected ResourceLocation getEntityTexture(EntityGreenSpider entity) {
        return RenderGreenSpider.GREEN_SPIDER_TEXTURE;
    }

    //实体的坐标和转动角度,这里的partialTicks，就是tick数的小数部分
    @Override
    public void doRender(EntityGreenSpider entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

}
