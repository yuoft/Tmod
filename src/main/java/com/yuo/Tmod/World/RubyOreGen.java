package com.yuo.Tmod.World;

import java.util.Random;

import com.yuo.Tmod.Common.Blocks.BlockLoader;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.event.terraingen.OreGenEvent;

public class RubyOreGen extends WorldGenerator {
    private final WorldGenMinable RubyOreGen = new WorldGenMinable(BlockLoader.rubyOre.getDefaultState(), 6);
    private final WorldGenMinable NetherRubyOreGen = new WorldGenMinable(BlockLoader.netherRubyOre.getDefaultState(), 8, BlockMatcher.forBlock(Blocks.NETHERRACK));

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {
        switch (world.provider.getDimension()){
            case 0: genMain(world, rand, pos);break;
            case -1: genNether(world, rand, pos);break;
            default:
        }
        return true;
    }

    public void genMain(World world, Random rand, BlockPos pos) {
        if (TerrainGen.generateOre(world, rand, this, pos, OreGenEvent.GenerateMinable.EventType.CUSTOM)) {
            for (int i = 0; i < 4; ++i) {
                int posX = pos.getX() + rand.nextInt(16);
                int posY = 16 + rand.nextInt(16);
                int posZ = pos.getZ() + rand.nextInt(16);
                BlockPos blockpos = new BlockPos(posX, posY, posZ);
                RubyOreGen.generate(world, rand, blockpos);
            }
        }
    }

    public void genNether(World world, Random rand, BlockPos pos) {
        if (TerrainGen.generateOre(world, rand, this, pos, OreGenEvent.GenerateMinable.EventType.QUARTZ)) {
            for (int i = 0; i < 6; ++i) {
                int posX = pos.getX() + rand.nextInt(16);
                int posY = 32 + rand.nextInt(32);
                int posZ = pos.getZ() + rand.nextInt(16);
                BlockPos blockpos = new BlockPos(posX, posY, posZ);
                NetherRubyOreGen.generate(world, rand, blockpos);
            }
        }
    }
}
