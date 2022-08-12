package com.yuo.Tmod.World;

import java.util.Random;

import com.yuo.Tmod.Common.Blocks.BlockLoader;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;

public class SpaceOreGen extends WorldGenerator {
    private final WorldGenMinable SpaceGenerator = new WorldGenMinable(BlockLoader.spaceOre.getDefaultState(), 3);
    private final WorldGenMinable EndSpaceGenerator = new WorldGenMinable(BlockLoader.endSpaceOre.getDefaultState(), 5, BlockMatcher.forBlock(Blocks.END_STONE));

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {
        switch (world.provider.getDimension()) {
            case 0:
                genMain(world, rand, pos);
                break;
            case 1:
                genEnd(world, rand, pos);
                break;
            default:
        }
        return true;
    }

    public void genMain(World world, Random rand, BlockPos pos) {
        if (TerrainGen.generateOre(world, rand, this, pos, OreGenEvent.GenerateMinable.EventType.CUSTOM)) {
            for (int i = 0; i < 5; ++i) {
                int posX = pos.getX() + rand.nextInt(16);
                int posY = 2 + rand.nextInt(8);//生成高度区间2~（2+8）
                int posZ = pos.getZ() + rand.nextInt(16);
                BlockPos blockpos = new BlockPos(posX, posY, posZ);
                SpaceGenerator.generate(world, rand, blockpos);
            }
        }
    }

    public void genEnd(World world, Random rand, BlockPos pos) {
        if (TerrainGen.generateOre(world, rand, this, pos, OreGenEvent.GenerateMinable.EventType.CUSTOM)) {
            for (int i = 0; i < 8; ++i) {
                int posX = pos.getX() + rand.nextInt(16);
                int posY = 10 + rand.nextInt(22);
                int posZ = pos.getZ() + rand.nextInt(16);
                BlockPos blockpos = new BlockPos(posX, posY, posZ);
                EndSpaceGenerator.generate(world, rand, blockpos);
            }
        }
    }
}
