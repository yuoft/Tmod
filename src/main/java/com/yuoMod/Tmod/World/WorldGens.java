package com.yuoMod.Tmod.World;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldGens {
    private static final WorldGenerator emeraldIngotOre = new EmeraldOreGen();
    private static final WorldGenerator SpaceOre = new SpaceOreGen();
    private static final WorldGenerator SaltOre = new SaltOreGen();
    private static final WorldGenerator RubyOre = new RubyOreGen();
    private static final WorldGenerator NetheriteOre = new NetheriteOreGen();
    private BlockPos pos;

    public WorldGens() {
        MinecraftForge.ORE_GEN_BUS.register(this);
    }

    @SubscribeEvent
    public void onOreGenPost(OreGenEvent.Post event) {
        if (!event.getPos().equals(this.pos)) {
            this.pos = event.getPos();
            emeraldIngotOre.generate(event.getWorld(), event.getRand(), event.getPos());
            SpaceOre.generate(event.getWorld(), event.getRand(), event.getPos());
            SaltOre.generate(event.getWorld(), event.getRand(), event.getPos());
            RubyOre.generate(event.getWorld(), event.getRand(), event.getPos());
            NetheriteOre.generate(event.getWorld(), event.getRand(), event.getPos());
        }
    }

    @SubscribeEvent
    public void onOreGenGenerateMinable(OreGenEvent.GenerateMinable event) {
//    	if (event.getType() == OreGenEvent.GenerateMinable.EventType.QUARTZ)
//        {
//    		event.setResult(Result.DENY);
//            oreCreate.generate(event.getWorld(), event.getRand(), event.getPos());
//        }
//        if (!event.getPos().equals(this.pos)) {
//            this.pos = event.getPos();
//            emeraldIngotOre.generate(event.getWorld(), event.getRand(), event.getPos());
//            SpaceOre.generate(event.getWorld(), event.getRand(), event.getPos());
//            SaltOre.generate(event.getWorld(), event.getRand(), event.getPos());
//            RubyOre.generate(event.getWorld(), event.getRand(), event.getPos());
//            NetheriteOre.generate(event.getWorld(), event.getRand(), event.getPos());
//        }
    }
}
