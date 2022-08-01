package com.yuoMod.Tmod.Entity.Render;

import com.yuoMod.Tmod.Tmod;
import com.yuoMod.Tmod.Entity.EntityKiana;

import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderKiana extends RenderLiving<EntityKiana> {
    private final ResourceLocation KIANA_TEXTURE = new ResourceLocation(Tmod.MOD_ID + ":" + "textures/entity/new_kiana.png");

    public RenderKiana(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelPlayer(0.0f, true), 0.5f);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityKiana entity) {
        return this.KIANA_TEXTURE;
    }

}