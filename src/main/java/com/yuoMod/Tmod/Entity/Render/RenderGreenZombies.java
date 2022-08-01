package com.yuoMod.Tmod.Entity.Render;

import com.yuoMod.Tmod.Tmod;
import com.yuoMod.Tmod.Entity.EntityGreenZombies;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderGreenZombies extends RenderLiving<EntityGreenZombies> {
    private static final ResourceLocation GREEN_ZOMBIES_TEXTURE = new ResourceLocation(Tmod.MOD_ID + ":" + "textures/entity/green_zombie.png");

    //mc����Ⱦ��������ʵ��ģ�ͣ���Ӱ��С
    public RenderGreenZombies(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelZombie(), 0.5f);
        this.addLayer(new LayerBipedArmor(this));
        this.addLayer(new LayerHeldItem(this));
    }

    //�������ǿ��Լ�һЩ������Ҫ�ı任����
    @Override
    protected void preRenderCallback(EntityGreenZombies entity, float partialTickTime) {
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
    }

    //ָ����Ⱦ�Ĳ���
    @Override
    protected ResourceLocation getEntityTexture(EntityGreenZombies entity) {
        return GREEN_ZOMBIES_TEXTURE;
    }

    //ʵ��������ת���Ƕ�,�����partialTicks������tick����С������
    @Override
    public void doRender(EntityGreenZombies entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
        this.bindEntityTexture(entity);//�л���ǰ�����������ĵ���������ϡ�
    }

}
