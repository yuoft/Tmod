package com.yuoMod.Tmod.Entity;

import com.yuoMod.Tmod.tmod;
import com.yuoMod.Tmod.Entity.Render.RenderGoldenTNT;
import com.yuoMod.Tmod.Entity.Render.RenderGreenCreeper;
import com.yuoMod.Tmod.Entity.Render.RenderGreenEnderman;
import com.yuoMod.Tmod.Entity.Render.RenderGreenSpider;
import com.yuoMod.Tmod.Entity.Render.RenderKiana;
import com.yuoMod.Tmod.Entity.Render.RenderLightningDiamond;
import com.yuoMod.Tmod.Entity.Render.RenderNewAlex;
import com.yuoMod.Tmod.Entity.Render.RenderNewSteve;
import com.yuoMod.Tmod.Entity.Render.RenderRedCreeper;
import com.yuoMod.Tmod.Entity.Render.RenderRedEnderman;
import com.yuoMod.Tmod.Entity.Render.RenderRedSpider;

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
    	//Ͷ����ƽ�tnt
    	registerEntity(EntityGoldenTNT.class, "GoldenTNT", 64, 10, true);
    	registerEntity(EntityLightningDiamond.class, "LightningDiamond", 64, 10, true);//������ʯ
    	//�µĽ�ʬ:�̽�
    	registerEntity(EntityGreenZombies.class, "GreenZombies", 80, 3, true);
    	registerEntityEgg(new ResourceLocation("GreenZombies"), 0x006400, 0x00FF00);
    	registerEntitySpawn(EntityGreenZombies.class, 60, 1, 3, EnumCreatureType.MONSTER, Biomes.PLAINS,Biomes.DESERT,Biomes.FOREST
    			,Biomes.TAIGA,Biomes.SWAMPLAND,Biomes.RIVER,Biomes.JUNGLE);
    	//�콩
    	registerEntity(EntityRedZombies.class, "RedZombies", 80, 3, true);
    	registerEntityEgg(new ResourceLocation("RedZombies"), 0x006400, 0xFF0000);
    	registerEntitySpawn(EntityRedZombies.class, 40, 1, 2, EnumCreatureType.MONSTER, Biomes.PLAINS,Biomes.DESERT,Biomes.FOREST
    			,Biomes.TAIGA,Biomes.SWAMPLAND,Biomes.RIVER,Biomes.JUNGLE);
    	//������
    	registerEntity(EntityGreenSkeleton.class, "GreenSkeleton", 80, 3, true);
    	registerEntityEgg(new ResourceLocation("GreenSkeleton"), 0x006400, 0x00FF00);
    	registerEntitySpawn(EntityGreenSkeleton.class, 60, 1, 3, EnumCreatureType.MONSTER, Biomes.PLAINS,Biomes.DESERT,Biomes.FOREST
    			,Biomes.TAIGA,Biomes.SWAMPLAND,Biomes.RIVER,Biomes.JUNGLE);
    	//������
    	registerEntity(EntityRedSkeleton.class, "RedSkeleton", 80, 3, true);
    	registerEntityEgg(new ResourceLocation("RedSkeleton"), 0x006400, 0xFF0000);
    	registerEntitySpawn(EntityRedSkeleton.class, 40, 1, 2, EnumCreatureType.MONSTER, Biomes.PLAINS,Biomes.DESERT,Biomes.FOREST
    			,Biomes.TAIGA,Biomes.SWAMPLAND,Biomes.RIVER,Biomes.JUNGLE);
    	//�̿�����
    	registerEntity(EntityGreenCreeper.class, "GreenCreeper", 80, 3, true);
    	registerEntityEgg(new ResourceLocation("GreenCreeper"), 0x006400, 0x00FF00);
    	registerEntitySpawn(EntityGreenCreeper.class, 40, 1, 3, EnumCreatureType.MONSTER, Biomes.PLAINS,Biomes.DESERT,Biomes.FOREST
    			,Biomes.TAIGA,Biomes.SWAMPLAND,Biomes.RIVER,Biomes.JUNGLE);
    	//�������
    	registerEntity(EntityRedCreeper.class, "RedCreeper", 80, 3, true);
    	registerEntityEgg(new ResourceLocation("RedCreeper"), 0x006400, 0xFF0000);
    	registerEntitySpawn(EntityRedCreeper.class, 2, 1, 2, EnumCreatureType.MONSTER, Biomes.PLAINS,Biomes.DESERT,Biomes.FOREST
    			,Biomes.TAIGA,Biomes.SWAMPLAND,Biomes.RIVER,Biomes.JUNGLE);
    	//��֩��
    	registerEntity(EntityGreenSpider.class, "GreenSpider", 80, 3, true);
    	registerEntityEgg(new ResourceLocation("GreenSpider"), 0x006400, 0x00FF00);
    	registerEntitySpawn(EntityGreenSpider.class, 60, 1, 3, EnumCreatureType.MONSTER, Biomes.PLAINS,Biomes.DESERT,Biomes.FOREST
    			,Biomes.TAIGA,Biomes.SWAMPLAND,Biomes.RIVER,Biomes.JUNGLE);
    	//��֩��
    	registerEntity(EntityRedSpider.class, "RedSpider", 80, 3, true);
    	registerEntityEgg(new ResourceLocation("RedSpider"), 0x006400, 0xFF0000);
    	registerEntitySpawn(EntityRedSpider.class, 40, 1, 2, EnumCreatureType.MONSTER, Biomes.PLAINS,Biomes.DESERT,Biomes.FOREST
    			,Biomes.TAIGA,Biomes.SWAMPLAND,Biomes.RIVER,Biomes.JUNGLE);
    	//��ĩӰ��
    	registerEntity(EntityGreenEnderman.class, "GreenEnderman", 80, 3, true);
    	registerEntityEgg(new ResourceLocation("GreenEnderman"), 0x006400, 0x00FF00);
    	registerEntitySpawn(EntityGreenEnderman.class, 60, 1, 3, EnumCreatureType.MONSTER, Biomes.PLAINS,Biomes.DESERT,Biomes.FOREST
    			,Biomes.TAIGA,Biomes.SWAMPLAND,Biomes.RIVER,Biomes.JUNGLE);
    	//��ĩӰ��
    	registerEntity(EntityRedEnderman.class, "RedEnderman", 80, 3, true);
    	registerEntityEgg(new ResourceLocation("RedEnderman"), 0x006400, 0xFF0000);
    	registerEntitySpawn(EntityRedEnderman.class, 40, 1, 2, EnumCreatureType.MONSTER, Biomes.PLAINS,Biomes.DESERT,Biomes.FOREST
    			,Biomes.TAIGA,Biomes.SWAMPLAND,Biomes.RIVER,Biomes.JUNGLE);
    	//������˹
    	registerEntity(EntityNewAlex.class, "NewAlex", 80, 3, true);
    	registerEntityEgg(new ResourceLocation("NewAlex"), 0x006400, 0x00FF00);
    	registerEntitySpawn(EntityNewAlex.class, 10, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS,Biomes.DESERT,Biomes.FOREST
    			,Biomes.TAIGA,Biomes.SWAMPLAND,Biomes.RIVER,Biomes.JUNGLE);
    	//ʷ�ٷ�
    	registerEntity(EntityNewSteve.class, "NewSteve", 80, 3, true);
    	registerEntityEgg(new ResourceLocation("NewSteve"), 0x006400, 0xFF0000);
    	registerEntitySpawn(EntityNewSteve.class, 10, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS,Biomes.DESERT,Biomes.FOREST
    			,Biomes.TAIGA,Biomes.SWAMPLAND,Biomes.RIVER,Biomes.JUNGLE);
    	//������--����
    	registerEntity(EntityKiana.class, "Kiana", 64, 3, true);
    	registerEntityEgg(new ResourceLocation("Kiana"), 0x000000, 0xffffff);
    }
    
    @SideOnly(Side.CLIENT)
    public static void registerRenders()
    {
    	registerEntityRender(EntityGoldenTNT.class, RenderGoldenTNT.class);
    	registerEntityRender(EntityLightningDiamond.class, RenderLightningDiamond.class);
    	
//    	registerEntityRender(EntityGreenZombies.class, RenderGreenZombies.class);
//    	registerEntityRender(EntityRedZombies.class, RenderRedZombies.class);
//    	
//    	registerEntityRender(EntityGreenSkeleton.class, RenderGreenSkeleton.class);
//    	registerEntityRender(EntityRedSkeleton.class, RenderRedSkeleton.class);
    	
    	registerEntityRender(EntityGreenCreeper.class, RenderGreenCreeper.class);
    	registerEntityRender(EntityRedCreeper.class, RenderRedCreeper.class);
    	
    	registerEntityRender(EntityGreenSpider.class, RenderGreenSpider.class);
    	registerEntityRender(EntityRedSpider.class, RenderRedSpider.class);
    	
    	registerEntityRender(EntityGreenEnderman.class, RenderGreenEnderman.class);
    	registerEntityRender(EntityRedEnderman.class, RenderRedEnderman.class);
    	
    	registerEntityRender(EntityNewAlex.class, RenderNewAlex.class);
    	registerEntityRender(EntityNewSteve.class, RenderNewSteve.class);
    	
    	registerEntityRender(EntityKiana.class, RenderKiana.class);
    }
    //ע��ʵ��
    private static void registerEntity(Class<? extends Entity> entityClass, String name, int trackingRange,
            int updateFrequency, boolean sendsVelocityUpdates)
    {//ʵ���Ӧʵ����ʵ�����ƣ�ʵ��id��modʵ�������ٰ뾶������Ƶ�ʣ��Ƿ�ͬ�������ٶ�
        EntityRegistry.registerModEntity(new ResourceLocation(name), entityClass, name, nextID++, tmod.instance, trackingRange, updateFrequency,
                sendsVelocityUpdates);
    }
    //ע��ˢ�ֵ�
    private static void registerEntityEgg(ResourceLocation resource, int eggPrimary, int eggSecondary)
    {//��ɫ����ɫ
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
