package com.yuoMod.Tmod.Entity.Render;

import com.yuoMod.Tmod.Tmod;
import com.yuoMod.Tmod.Entity.EntityGreenZombies;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderGreenZombies extends RenderLiving<EntityGreenZombies> {
    private static final ResourceLocation GREEN_ZOMBIES_TEXTURE = new ResourceLocation(Tmod.MOD_ID + ":" + "textures/entity/green_zombie.png");

    //mc的渲染管理器，实体模型，阴影大小
    public RenderGreenZombies(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelZombie(), 0.5f);
        this.addLayer(new LayerBipedArmor(this));
        this.addLayer(new LayerHeldItem(this));
    }

    //这里我们可以加一些我们想要的变换操作
    @Override
    protected void preRenderCallback(EntityGreenZombies entity, float partialTickTime) {
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
    }

    //指定渲染的材质
    @Override
    protected ResourceLocation getEntityTexture(EntityGreenZombies entity) {
        return GREEN_ZOMBIES_TEXTURE;
    }

    //实体的坐标和转动角度,这里的partialTicks，就是tick数的小数部分
    @Override
    public void doRender(EntityGreenZombies entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
        this.bindEntityTexture(entity);//切换当前的纹理上下文到你的纹理上。
    }

}
