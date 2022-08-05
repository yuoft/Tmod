package com.yuo.Tmod.Entity.Render;

import com.yuo.Tmod.Tmod;
import com.yuo.Tmod.Entity.EntityNewSteve;

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
public class RenderNewSteve extends RenderLiving<EntityNewSteve> {
    private static final ResourceLocation NEW_STEVE_TEXTURE = new ResourceLocation(Tmod.MOD_ID , "textures/entity/new_steve.png");

    //mc的渲染管理器，实体模型，阴影大小
    public RenderNewSteve(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelPlayer(0.0f, false), 0.5f);
        this.addLayer(new LayerBipedArmor(this));
        this.addLayer(new LayerHeldItem(this));
    }

    //这里我们可以加一些我们想要的变换操作
    @Override
    protected void preRenderCallback(EntityNewSteve entity, float partialTickTime) {
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
    }

    //指定渲染的材质
    @Override
    protected ResourceLocation getEntityTexture(EntityNewSteve entity) {
        return RenderNewSteve.NEW_STEVE_TEXTURE;
    }

    //实体的坐标和转动角度,这里的partialTicks，就是tick数的小数部分
    @Override
    public void doRender(EntityNewSteve entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

}
