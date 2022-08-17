package com.yuo.Tmod.Entity.Render;

import com.yuo.Tmod.Entity.EntitySpaceArrow;
import com.yuo.Tmod.Tmod;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderSpaceArrow extends RenderArrow<EntitySpaceArrow> {

    private static final ResourceLocation SPACE_ARROW_TEXTURE = new ResourceLocation(Tmod.MOD_ID + ":" + "textures/entity/arrow/space_arrow.png");

    public RenderSpaceArrow(RenderManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntitySpaceArrow entity) {
        return SPACE_ARROW_TEXTURE;
    }
}
