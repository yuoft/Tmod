package com.yuoMod.Tmod.Entity.render;

import com.yuoMod.Tmod.tmod;
import com.yuoMod.Tmod.Entity.EntityRedZombies;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderRedZombies extends RenderLiving<EntityRedZombies>
{
	private static final ResourceLocation RED_ZOMBIES_TEXTURE = new ResourceLocation(tmod.MODID + ":" + "textures/entity/red_zombies.png");
	public RenderRedZombies(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelZombie(), 0.5f);
	}
    @Override
    protected void preRenderCallback(EntityRedZombies entity, float partialTickTime)
    {
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
    }
	@Override
	protected ResourceLocation getEntityTexture(EntityRedZombies entity) {
		return RenderRedZombies.RED_ZOMBIES_TEXTURE;
	}
	@Override
    public void doRender(EntityRedZombies entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
	
}
