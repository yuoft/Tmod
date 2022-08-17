package com.yuo.Tmod.Entity.Render;

import com.yuo.Tmod.Entity.EntityDiamondArrow;
import com.yuo.Tmod.Entity.EntityIronArrow;
import com.yuo.Tmod.Tmod;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderIronArrow extends RenderArrow<EntityIronArrow> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Tmod.MOD_ID + ":" + "textures/entity/arrow/iron_arrow.png");

    public RenderIronArrow(RenderManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityIronArrow entity) {
        return TEXTURE;
    }
}
