package com.yuo.Tmod.Entity.Render;

import com.yuo.Tmod.Tmod;
import com.yuo.Tmod.Entity.EntityGreenEnderman;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderGreenEnderman extends RenderLiving<EntityGreenEnderman> {
    private static final ResourceLocation GREEN_ENDERMAN_TEXTURE = new ResourceLocation(Tmod.MOD_ID + ":" + "textures/entity/green_enderman.png");

    //mc的渲染管理器，实体模型，阴影大小
    public RenderGreenEnderman(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelEnderman(0.0f), 0.5f);
        this.addLayer(new LayerGreenEndermanEyes(this));
    }

    //这里我们可以加一些我们想要的变换操作
    @Override
    protected void preRenderCallback(EntityGreenEnderman entity, float partialTickTime) {
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
    }

    //指定渲染的材质
    @Override
    protected ResourceLocation getEntityTexture(EntityGreenEnderman entity) {
        return RenderGreenEnderman.GREEN_ENDERMAN_TEXTURE;
    }

    //实体的坐标和转动角度,这里的partialTicks，就是tick数的小数部分
    @Override
    public void doRender(EntityGreenEnderman entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

}
