package com.yuo.Tmod.Entity.Render;

import com.yuo.Tmod.Entity.EntityDragonArrow;
import com.yuo.Tmod.Tmod;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderDragonArrow extends RenderArrow<EntityDragonArrow> {

    private static final ResourceLocation SPACE_ARROW_TEXTURE = new ResourceLocation(Tmod.MOD_ID + ":" + "textures/entity/arrow.png");

    public RenderDragonArrow(RenderManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityDragonArrow entity) {
        return SPACE_ARROW_TEXTURE;
    }
}
