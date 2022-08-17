package com.yuo.Tmod.Entity.Render;

import com.yuo.Tmod.Entity.EntityDragonArrow;
import com.yuo.Tmod.Entity.EntityNetheriteArrow;
import com.yuo.Tmod.Tmod;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderNetheriteArrow extends RenderArrow<EntityNetheriteArrow> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Tmod.MOD_ID + ":" + "textures/entity/arrow/netherite_arrow.png");

    public RenderNetheriteArrow(RenderManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityNetheriteArrow entity) {
        return TEXTURE;
    }
}
