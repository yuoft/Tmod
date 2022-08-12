package com.yuo.Tmod.World;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

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
        oreGen(event.getPos(), event.getWorld(), event.getRand());
    }

    private void oreGen(BlockPos pos, World world, Random rand) {
        if (!pos.equals(this.pos)) {
            this.pos = pos;
            emeraldIngotOre.generate(world, rand, pos);
            SpaceOre.generate(world, rand, pos);
            SaltOre.generate(world, rand, pos);
            RubyOre.generate(world, rand, pos);
            NetheriteOre.generate(world, rand, pos);
        }
    }

    @SubscribeEvent
    public void onOreGenGenerateMinable(OreGenEvent.GenerateMinable event) {
        oreGen(event.getPos(), event.getWorld(), event.getRand());
    }
}
