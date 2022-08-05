package com.yuo.Tmod.Entity.Render;

import com.yuo.Tmod.Entity.EntityNewFireball;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.init.Items;

public class RenderNewFireball extends RenderSnowball<EntityNewFireball> {
    public RenderNewFireball(RenderManager renderManagerIn) {
        super(renderManagerIn, Items.FIRE_CHARGE, Minecraft.getMinecraft().getRenderItem());
    }
}
