package com.yuoMod.Tmod.Entity.render;

import com.yuoMod.Tmod.tmod;
import com.yuoMod.Tmod.Entity.EntityRedEnderman;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderRedEnderman extends RenderLiving<EntityRedEnderman>
{
	private static final ResourceLocation RED_ENDERMAN_TEXTURE = new ResourceLocation(tmod.MODID + ":" + "textures/entity/red_enderman.png");
	public RenderRedEnderman(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelEnderman(0.0f), 0.5f);
	}
    @Override
    protected void preRenderCallback(EntityRedEnderman entity, float partialTickTime)
    {
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
    }
	@Override
	protected ResourceLocation getEntityTexture(EntityRedEnderman entity) {
		return RenderRedEnderman.RED_ENDERMAN_TEXTURE;
	}
	@Override
    public void doRender(EntityRedEnderman entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
	
}
