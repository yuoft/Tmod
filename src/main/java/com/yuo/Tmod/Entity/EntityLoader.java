package com.yuo.Tmod.Entity;

import com.yuo.Tmod.Entity.Render.*;
import com.yuo.Tmod.Entity.Render.RenderGoldenTNT;
import com.yuo.Tmod.Tmod;

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

public class EntityLoader {
    private static int nextID = 0;

    public EntityLoader() {
        //投掷物，黄金tnt
        registerEntity(EntityGoldenTNT.class, "GoldenTNT", 64, 10, true);
        registerEntity(EntityLightningDiamond.class, "LightningDiamond", 64, 10, true);//闪电钻石
        registerEntity(EntityNewSnowball.class, "NewSnowball", 64, 10, true);
        registerEntity(EntityNewFireball.class, "NewFireball", 64, 10, true);
        registerEntity(EntitySpaceArrow.class, "SpaceArrow", 64, 10, true);
        registerEntity(EntityDragonArrow.class, "DragonArrow", 64, 10, true);
        //新的僵尸:绿僵
        registerEntity(EntityGreenZombies.class, "GreenZombies", 80, 3, true);
        registerEntityEgg(new ResourceLocation("GreenZombies"), 0x006400, 0x00FF00);
        registerEntitySpawn(EntityGreenZombies.class, 60, 1, 3, EnumCreatureType.MONSTER, Biomes.PLAINS, Biomes.DESERT, Biomes.FOREST
                , Biomes.TAIGA, Biomes.SWAMPLAND, Biomes.RIVER, Biomes.JUNGLE);
        //红僵
        registerEntity(EntityRedZombies.class, "RedZombies", 80, 3, true);
        registerEntityEgg(new ResourceLocation("RedZombies"), 0x006400, 0xFF0000);
        registerEntitySpawn(EntityRedZombies.class, 40, 1, 2, EnumCreatureType.MONSTER, Biomes.PLAINS, Biomes.DESERT, Biomes.FOREST
                , Biomes.TAIGA, Biomes.SWAMPLAND, Biomes.RIVER, Biomes.JUNGLE);
        //绿骷髅
        registerEntity(EntityGreenSkeleton.class, "GreenSkeleton", 80, 3, true);
        registerEntityEgg(new ResourceLocation("GreenSkeleton"), 0x006400, 0x00FF00);
        registerEntitySpawn(EntityGreenSkeleton.class, 60, 1, 3, EnumCreatureType.MONSTER, Biomes.PLAINS, Biomes.DESERT, Biomes.FOREST
                , Biomes.TAIGA, Biomes.SWAMPLAND, Biomes.RIVER, Biomes.JUNGLE);
        //红骷髅
        registerEntity(EntityRedSkeleton.class, "RedSkeleton", 80, 3, true);
        registerEntityEgg(new ResourceLocation("RedSkeleton"), 0x006400, 0xFF0000);
        registerEntitySpawn(EntityRedSkeleton.class, 40, 1, 2, EnumCreatureType.MONSTER, Biomes.PLAINS, Biomes.DESERT, Biomes.FOREST
                , Biomes.TAIGA, Biomes.SWAMPLAND, Biomes.RIVER, Biomes.JUNGLE);
        //绿苦力怕
        registerEntity(EntityGreenCreeper.class, "GreenCreeper", 80, 3, true);
        registerEntityEgg(new ResourceLocation("GreenCreeper"), 0x006400, 0x00FF00);
        registerEntitySpawn(EntityGreenCreeper.class, 40, 1, 3, EnumCreatureType.MONSTER, Biomes.PLAINS, Biomes.DESERT, Biomes.FOREST
                , Biomes.TAIGA, Biomes.SWAMPLAND, Biomes.RIVER, Biomes.JUNGLE);
        //红苦力怕
        registerEntity(EntityRedCreeper.class, "RedCreeper", 80, 3, true);
        registerEntityEgg(new ResourceLocation("RedCreeper"), 0x006400, 0xFF0000);
        registerEntitySpawn(EntityRedCreeper.class, 2, 1, 2, EnumCreatureType.MONSTER, Biomes.PLAINS, Biomes.DESERT, Biomes.FOREST
                , Biomes.TAIGA, Biomes.SWAMPLAND, Biomes.RIVER, Biomes.JUNGLE);
        //绿蜘蛛
        registerEntity(EntityGreenSpider.class, "GreenSpider", 80, 3, true);
        registerEntityEgg(new ResourceLocation("GreenSpider"), 0x006400, 0x00FF00);
        registerEntitySpawn(EntityGreenSpider.class, 60, 1, 3, EnumCreatureType.MONSTER, Biomes.PLAINS, Biomes.DESERT, Biomes.FOREST
                , Biomes.TAIGA, Biomes.SWAMPLAND, Biomes.RIVER, Biomes.JUNGLE);
        //红蜘蛛
        registerEntity(EntityRedSpider.class, "RedSpider", 80, 3, true);
        registerEntityEgg(new ResourceLocation("RedSpider"), 0x006400, 0xFF0000);
        registerEntitySpawn(EntityRedSpider.class, 40, 1, 2, EnumCreatureType.MONSTER, Biomes.PLAINS, Biomes.DESERT, Biomes.FOREST
                , Biomes.TAIGA, Biomes.SWAMPLAND, Biomes.RIVER, Biomes.JUNGLE);
        //绿末影人
        registerEntity(EntityGreenEnderman.class, "GreenEnderman", 80, 3, true);
        registerEntityEgg(new ResourceLocation("GreenEnderman"), 0x006400, 0x00FF00);
        registerEntitySpawn(EntityGreenEnderman.class, 60, 1, 3, EnumCreatureType.MONSTER, Biomes.PLAINS, Biomes.DESERT, Biomes.FOREST
                , Biomes.TAIGA, Biomes.SWAMPLAND, Biomes.RIVER, Biomes.JUNGLE);
        //红末影人
        registerEntity(EntityRedEnderman.class, "RedEnderman", 80, 3, true);
        registerEntityEgg(new ResourceLocation("RedEnderman"), 0x006400, 0xFF0000);
        registerEntitySpawn(EntityRedEnderman.class, 40, 1, 2, EnumCreatureType.MONSTER, Biomes.PLAINS, Biomes.DESERT, Biomes.FOREST
                , Biomes.TAIGA, Biomes.SWAMPLAND, Biomes.RIVER, Biomes.JUNGLE);
        //艾利克斯
        registerEntity(EntityNewAlex.class, "NewAlex", 80, 3, true);
        registerEntityEgg(new ResourceLocation("NewAlex"), 0x006400, 0x00FF00);
        registerEntitySpawn(EntityNewAlex.class, 10, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS, Biomes.DESERT, Biomes.FOREST
                , Biomes.TAIGA, Biomes.SWAMPLAND, Biomes.RIVER, Biomes.JUNGLE);
        //史蒂夫
        registerEntity(EntityNewSteve.class, "NewSteve", 80, 3, true);
        registerEntityEgg(new ResourceLocation("NewSteve"), 0x006400, 0xFF0000);
        registerEntitySpawn(EntityNewSteve.class, 10, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS, Biomes.DESERT, Biomes.FOREST
                , Biomes.TAIGA, Biomes.SWAMPLAND, Biomes.RIVER, Biomes.JUNGLE);
        //琪亚娜--白练
        registerEntity(EntityKiana.class, "Kiana", 128, 2, true);
        registerEntityEgg(new ResourceLocation("Kiana"), 0x000000, 0xffffff);

    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders() {
        registerEntityRender(EntityGoldenTNT.class, RenderGoldenTNT.class);
        registerEntityRender(EntityLightningDiamond.class, RenderLightningDiamond.class);
        registerEntityRender(EntityNewSnowball.class, RenderNewSnowball.class);
        registerEntityRender(EntityNewFireball.class, RenderNewFireball.class);
        registerEntityRender(EntitySpaceArrow.class, RenderSpaceArrow.class);
        registerEntityRender(EntityDragonArrow.class, RenderDragonArrow.class);

        registerEntityRender(EntityGreenZombies.class, RenderGreenZombies.class);
        registerEntityRender(EntityRedZombies.class, RenderRedZombies.class);

        registerEntityRender(EntityGreenSkeleton.class, RenderGreenSkeleton.class);
        registerEntityRender(EntityRedSkeleton.class, RenderRedSkeleton.class);

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

    //注册实体
    private static void registerEntity(Class<? extends Entity> entityClass, String name, int trackingRange,
                                       int updateFrequency, boolean sendsVelocityUpdates) {//实体对应实例，实体名称，实体id，mod实例，跟踪半径，更新频率，是否同步更新速度
        EntityRegistry.registerModEntity(new ResourceLocation(name), entityClass, name, nextID++, Tmod.instance, trackingRange, updateFrequency,
                sendsVelocityUpdates);
    }

    //注册刷怪蛋
    private static void registerEntityEgg(ResourceLocation resource, int eggPrimary, int eggSecondary) {//主色，副色
        EntityRegistry.registerEgg(resource, eggPrimary, eggSecondary);
    }

    //注册实体渲染
    @SideOnly(Side.CLIENT)
    private static <T extends Entity> void registerEntityRender(Class<T> entityClass, Class<? extends Render<T>> render) {
        RenderingRegistry.registerEntityRenderingHandler(entityClass, new EntityRenderFactory<T>(render));
    }
    //实体自然生成

    /**
     * 第一个参数和之前的相同，需要传入这个实体对应的class实例
     * 第二个参数表示生物的生成权重，这个值越大，优先生成该生物的机率越高
     * 第三个参数表示一次性生成的最少生物数量
     * 第四个参数表示一次性生成的最多生物数量
     * 第五个参数表示这个实体生物的生成类型，有MONSTER（刷怪），CREATURE（动物），AMBIENT（飞行生物）和WATER_CREATURE（水生动物）四种
     * 第六个参数往后就是表示可以生成的生物群系了，BiomeGenBase类中提供了原版的所有生物群系
     *
     * @param entityClass
     * @param spawnWeight
     * @param min
     * @param max
     * @param typeOfCreature
     */
    private static void registerEntitySpawn(Class<? extends Entity> entityClass, int spawnWeight, int min,
                                            int max, EnumCreatureType typeOfCreature, Biome... biomes) {
        if (EntityLiving.class.isAssignableFrom(entityClass)) {
            Class<? extends EntityLiving> entityLivingClass = entityClass.asSubclass(EntityLiving.class);
            EntityRegistry.addSpawn(entityLivingClass, spawnWeight, min, max, typeOfCreature, biomes);
        }
    }
}
