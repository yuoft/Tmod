package com.yuoMod.Tmod.Entity.Render;

import com.yuoMod.Tmod.Common.Items.ItemLoader;
import com.yuoMod.Tmod.Entity.EntityGoldenTNT;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;

public class RenderGoldenTNT extends RenderSnowball<EntityGoldenTNT> {
    public RenderGoldenTNT(RenderManager renderManagerIn) {
        super(renderManagerIn, ItemLoader.golden_tnt, Minecraft.getMinecraft().getRenderItem());
    }
}
