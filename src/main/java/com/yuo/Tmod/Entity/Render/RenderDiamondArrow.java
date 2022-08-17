package com.yuo.Tmod.Entity.Render;

import com.yuo.Tmod.Entity.EntityDiamondArrow;
import com.yuo.Tmod.Entity.EntityDragonArrow;
import com.yuo.Tmod.Entity.EntityNetheriteArrow;
import com.yuo.Tmod.Tmod;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderDiamondArrow extends RenderArrow<EntityDiamondArrow> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Tmod.MOD_ID + ":" + "textures/entity/arrow/diamond_arrow.png");

    public RenderDiamondArrow(RenderManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityDiamondArrow entity) {
        return TEXTURE;
    }
}
