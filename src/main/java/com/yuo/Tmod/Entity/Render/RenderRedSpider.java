package com.yuo.Tmod.Entity.Render;

import com.yuo.Tmod.Tmod;
import com.yuo.Tmod.Entity.EntityRedSpider;

import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderRedSpider extends RenderLiving<EntityRedSpider> {
    private static final ResourceLocation RED_SPIDER_TEXTURE = new ResourceLocation(Tmod.MOD_ID + ":" + "textures/entity/red_spider.png");

    public RenderRedSpider(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelSpider(), 0.5f);
        this.addLayer(new LayerRedSpiderEyes(this));
    }

    @Override
    protected void preRenderCallback(EntityRedSpider entity, float partialTickTime) {
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityRedSpider entity) {
        return RenderRedSpider.RED_SPIDER_TEXTURE;
    }

    @Override
    public void doRender(EntityRedSpider entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

}
