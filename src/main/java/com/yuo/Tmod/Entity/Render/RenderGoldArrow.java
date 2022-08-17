package com.yuo.Tmod.Entity.Render;

import com.yuo.Tmod.Entity.EntityDiamondArrow;
import com.yuo.Tmod.Entity.EntityGoldArrow;
import com.yuo.Tmod.Tmod;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderGoldArrow extends RenderArrow<EntityGoldArrow> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Tmod.MOD_ID + ":" + "textures/entity/arrow/gold_arrow.png");

    public RenderGoldArrow(RenderManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityGoldArrow entity) {
        return TEXTURE;
    }
}
