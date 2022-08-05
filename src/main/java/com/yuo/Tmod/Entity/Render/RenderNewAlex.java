package com.yuo.Tmod.Entity.Render;

import com.yuo.Tmod.Tmod;
import com.yuo.Tmod.Entity.EntityNewAlex;

import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderNewAlex extends RenderLiving<EntityNewAlex> {
    private static final ResourceLocation NEW_ALEX_TEXTURE = new ResourceLocation(Tmod.MOD_ID + ":" + "textures/entity/new_alex.png");

    //mc的渲染管理器，实体模型，阴影大小
    public RenderNewAlex(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelPlayer(0.0f, true), 0.5f);
        this.addLayer(new LayerBipedArmor(this));
        this.addLayer(new LayerHeldItem(this));
    }

    //这里我们可以加一些我们想要的变换操作
    @Override
    protected void preRenderCallback(EntityNewAlex entity, float partialTickTime) {
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
    }

    //指定渲染的材质
    @Override
    protected ResourceLocation getEntityTexture(EntityNewAlex entity) {
        return RenderNewAlex.NEW_ALEX_TEXTURE;
    }
    //实体的坐标和转动角度,这里的partialTicks，就是tick数的小数部分
//	@Override
//    public void doRender(EntityNewAlex entity, double x, double y, double z, float entityYaw, float partialTicks)
//    {
//        super.doRender(entity, x, y, z, entityYaw, partialTicks);
//    }

}
