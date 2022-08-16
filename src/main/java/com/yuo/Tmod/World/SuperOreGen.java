package com.yuo.Tmod.World;

import com.yuo.Tmod.Common.Blocks.BlockLoader;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

import java.util.Random;

public class SuperOreGen extends WorldGenerator {

    private final WorldGenMinable SuperGen = new WorldGenMinable(BlockLoader.superOre.getDefaultState(), 6, BlockMatcher.forBlock(Blocks.END_STONE));

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {
        if (world.provider.getDimension() == 1) {
            genMain(world, rand, pos);
        }
        return true;
    }

    public void genMain(World world, Random rand, BlockPos pos) {
        if (TerrainGen.generateOre(world, rand, this, pos, OreGenEvent.GenerateMinable.EventType.CUSTOM)) {
            for (int i = 0; i < 5; ++i) {
                int posX = pos.getX() + rand.nextInt(16);
                int posY = 16 + rand.nextInt(32);
                int posZ = pos.getZ() + rand.nextInt(16);
                BlockPos blockpos = new BlockPos(posX, posY, posZ);
                SuperGen.generate(world, rand, blockpos);
            }
        }
    }
}
