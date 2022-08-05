package com.yuo.Tmod.Entity.Render;

import com.yuo.Tmod.Entity.EntityLightningDiamond;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.init.Items;

public class RenderLightningDiamond extends RenderSnowball<EntityLightningDiamond> {
    public RenderLightningDiamond(RenderManager renderManagerIn) {
        super(renderManagerIn, Items.DIAMOND, Minecraft.getMinecraft().getRenderItem());
    }
}
