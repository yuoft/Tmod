package com.yuoMod.Tmod.Entity.Render;

import com.yuoMod.Tmod.Entity.EntityNewSnowball;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.init.Items;

public class RenderNewSnowball extends RenderSnowball<EntityNewSnowball> {
    public RenderNewSnowball(RenderManager renderManagerIn) {
        super(renderManagerIn, Items.SNOWBALL, Minecraft.getMinecraft().getRenderItem());
    }
}
