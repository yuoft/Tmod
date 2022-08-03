package com.yuoMod.Tmod.Entity;

import com.yuoMod.Tmod.Common.Items.ItemLoader;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;

public class RenderGoldenTNT extends RenderSnowball<EntityGoldenTNT> {
    public RenderGoldenTNT(RenderManager renderManagerIn) {
        super(renderManagerIn, ItemLoader.goldenTnt, Minecraft.getMinecraft().getRenderItem());
    }
}
