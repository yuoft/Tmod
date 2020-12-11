package com.yuoMod.Tmod.Entity;

import com.yuoMod.Tmod.Common.Items.itemLoader;
import com.yuoMod.Tmod.Entity.EntityGoldenTNT;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;

public class RenderGoldenTNT extends RenderSnowball<EntityGoldenTNT>
{
	public RenderGoldenTNT(RenderManager renderManagerIn)
    {
        super(renderManagerIn,itemLoader.golden_tnt , Minecraft.getMinecraft().getRenderItem());
    }
}
