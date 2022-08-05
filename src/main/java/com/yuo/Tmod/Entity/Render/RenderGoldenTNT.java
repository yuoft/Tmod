package com.yuo.Tmod.Entity.Render;

import com.yuo.Tmod.Common.Items.ItemLoader;
import com.yuo.Tmod.Entity.EntityGoldenTNT;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;

public class RenderGoldenTNT extends RenderSnowball<EntityGoldenTNT> {
    public RenderGoldenTNT(RenderManager renderManagerIn) {
        super(renderManagerIn, ItemLoader.goldenTnt, Minecraft.getMinecraft().getRenderItem());
    }
}
