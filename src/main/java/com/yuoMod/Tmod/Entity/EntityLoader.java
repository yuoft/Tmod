package com.yuoMod.Tmod.Entity;

import java.util.List;

import com.yuoMod.Tmod.tmod;
import com.yuoMod.Tmod.Entity.render.RenderGoldenChicken;
import com.yuoMod.Tmod.Entity.render.RenderGoldenTNT;
import com.yuoMod.Tmod.Entity.render.RenderNewZombies;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeBeach;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityLoader 
{
	private static int nextID = 0;

    public EntityLoader()
    {
    	//实体黄金鸡
    	registerEntity(EntityGoldenChicken.class, "GoldenChicken", 64, 3, true);
    	registerEntityEgg(new ResourceLocation("GoldenChicken"), 0xffff66, 0x660000);
    	registerEntitySpawn(EntityGoldenChicken.class, 8, 2, 4, EnumCreatureType.CREATURE,Biomes.PLAINS,Biomes.DESERT);
    	//投掷物，黄金tnt
    	registerEntity(EntityGoldenTNT.class, "GoldenTNT", 64, 10, true);
    	//新的僵尸:绿僵
    	registerEntity(EntityNewZombies.class, "NewZombies", 80, 3, true);
    	registerEntityEgg(new ResourceLocation("NewZombies"), 0x006400, 0x00FF00);
    	registerEntitySpawn(EntityNewZombies.class, 100, 1, 3, EnumCreatureType.MONSTER, Biomes.PLAINS,Biomes.DESERT,Biomes.FOREST
    			,Biomes.TAIGA,Biomes.SWAMPLAND,Biomes.RIVER,Biomes.JUNGLE);
    }
    
    @SideOnly(Side.CLIENT)
    public static void registerRenders()
    {
    	registerEntityRender(EntityGoldenChicken.class, RenderGoldenChicken.class);
    	registerEntityRender(EntityGoldenTNT.class, RenderGoldenTNT.class);
    	registerEntityRender(EntityNewZombies.class, RenderNewZombies.class);
    }
    //注册实体
    private static void registerEntity(Class<? extends Entity> entityClass, String name, int trackingRange,
            int updateFrequency, boolean sendsVelocityUpdates)
    {//实体对应实例，实体名称，实体id，mod实例，跟踪半径，更新频率，是否同步更新速度
        EntityRegistry.registerModEntity(new ResourceLocation(name), entityClass, name, nextID++, tmod.instance, trackingRange, updateFrequency,
                sendsVelocityUpdates);
    }
    //注册刷怪蛋
    private static void registerEntityEgg(ResourceLocation resource, int eggPrimary, int eggSecondary)
    {//主色，副色
        EntityRegistry.registerEgg(resource, eggPrimary, eggSecondary);
    }
    //注册实体渲染
    @SideOnly(Side.CLIENT)
    private static <T extends Entity> void registerEntityRender(Class<T> entityClass, Class<? extends Render<T>> render)
    {
        RenderingRegistry.registerEntityRenderingHandler(entityClass, new EntityRenderFactory<T>(render));
    }
    //实体自然生成
    /**
     * 第一个参数和之前的相同，需要传入这个实体对应的class实例
第二个参数表示生物的生成权重，这个值越大，优先生成该生物的机率越高
第三个参数表示一次性生成的最少生物数量
第四个参数表示一次性生成的最多生物数量
第五个参数表示这个实体生物的生成类型，有MONSTER（刷怪），CREATURE（动物），AMBIENT（飞行生物）和WATER_CREATURE（水生动物）四种
第六个参数往后就是表示可以生成的生物群系了，BiomeGenBase类中提供了原版的所有生物群系
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
