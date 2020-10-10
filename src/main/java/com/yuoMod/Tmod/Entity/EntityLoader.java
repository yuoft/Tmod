package com.yuoMod.Tmod.Entity;

import com.yuoMod.Tmod.tmod;
import com.yuoMod.Tmod.Entity.render.RenderGoldenChicken;
import com.yuoMod.Tmod.Entity.render.RenderGoldenTNT;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityLoader 
{
	private static int nextID = 0;

    public EntityLoader()
    {
    	registerEntity(EntityGoldenChicken.class, "GoldenChicken", 64, 3, true);
    	registerEntityEgg(new ResourceLocation("GoldenChicken"), 0xffff66, 0x660000);
    	registerEntitySpawn(EntityGoldenChicken.class, 8, 2, 4, EnumCreatureType.CREATURE,Biomes.PLAINS,Biomes.DESERT);
    	registerEntity(EntityGoldenTNT.class, "GoldenTNT", 64, 10, true);
    }
    
    @SideOnly(Side.CLIENT)
    public static void registerRenders()
    {
    	registerEntityRender(EntityGoldenChicken.class, RenderGoldenChicken.class);
    	registerEntityRender(EntityGoldenTNT.class, RenderGoldenTNT.class);
    }
    //ע��ʵ��
    private static void registerEntity(Class<? extends Entity> entityClass, String name, int trackingRange,
            int updateFrequency, boolean sendsVelocityUpdates)
    {
        EntityRegistry.registerModEntity(new ResourceLocation(name), entityClass, name, nextID++, tmod.instance, trackingRange, updateFrequency,
                sendsVelocityUpdates);
    }
    //ע��ˢ�ֵ�
    private static void registerEntityEgg(ResourceLocation resource, int eggPrimary, int eggSecondary)
    {
        EntityRegistry.registerEgg(resource, eggPrimary, eggSecondary);
    }
    //ע��ʵ����Ⱦ
    @SideOnly(Side.CLIENT)
    private static <T extends Entity> void registerEntityRender(Class<T> entityClass, Class<? extends Render<T>> render)
    {
        RenderingRegistry.registerEntityRenderingHandler(entityClass, new EntityRenderFactory<T>(render));
    }
    //ʵ����Ȼ����
    /**
     * ��һ��������֮ǰ����ͬ����Ҫ�������ʵ���Ӧ��classʵ��
�ڶ���������ʾ���������Ȩ�أ����ֵԽ���������ɸ�����Ļ���Խ��
������������ʾһ�������ɵ�������������
���ĸ�������ʾһ�������ɵ������������
�����������ʾ���ʵ��������������ͣ���MONSTER��ˢ�֣���CREATURE�������AMBIENT�����������WATER_CREATURE��ˮ���������
����������������Ǳ�ʾ�������ɵ�����Ⱥϵ�ˣ�BiomeGenBase�����ṩ��ԭ�����������Ⱥϵ
     * @param entityClass
     * @param spawnWeight
     * @param min
     * @param max
     * @param typeOfCreature
     */
    private static void registerEntitySpawn(Class<? extends Entity> entityClass, int spawnWeight, int min,
            int max, EnumCreatureType typeOfCreature,Biome... biomes)
    {
        if (EntityLiving.class.isAssignableFrom(entityClass))
        {
            Class<? extends EntityLiving> entityLivingClass = entityClass.asSubclass(EntityLiving.class);
            EntityRegistry.addSpawn(entityLivingClass, spawnWeight, min, max, typeOfCreature,biomes);
        }
    }
}
