package com.yuo.Tmod.Entity.Render;

import com.yuo.Tmod.Tmod;
import com.yuo.Tmod.Entity.EntityRedZombies;

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
public class RenderRedZombies extends RenderLiving<EntityRedZombies> {
    private static final ResourceLocation RED_ZOMBIES_TEXTURE = new ResourceLocation(Tmod.MOD_ID + ":" + "textures/entity/red_zombie.png");

    public RenderRedZombies(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelZombie(), 0.5f);
        this.addLayer(new LayerBipedArmor(this));
        this.addLayer(new LayerHeldItem(this));
    }

    @Override
    protected void preRenderCallback(EntityRedZombies entity, float partialTickTime) {
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityRedZombies entity) {
        return RED_ZOMBIES_TEXTURE;
    }

    @Override
    public void doRender(EntityRedZombies entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

}
