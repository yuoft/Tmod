package com.yuoMod.Tmod.Entity.Render;

import com.yuoMod.Tmod.tmod;
import com.yuoMod.Tmod.Entity.EntityKiana;
import com.yuoMod.Tmod.Entity.Model.ModelEmeraldBoss;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderKiana extends RenderLiving<EntityKiana>
{
	private final ResourceLocation KIANA_TEXTURE=new ResourceLocation(tmod.MODID+ ":"+ "textures/entity/kiana.png");
	public RenderKiana(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelEmeraldBoss(), 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityKiana entity) {
		return this.KIANA_TEXTURE;
	}
	
}
