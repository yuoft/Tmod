package com.yuo.Tmod.World;

import com.yuo.Tmod.Common.Blocks.BlockLoader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

import java.util.Random;

public class FragileBedrockGen extends WorldGenerator {

    private final WorldGenMinable FBGen = new WorldGenMinable(BlockLoader.fragileBedrock.getDefaultState(), 3);

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {
        if (world.provider.getDimension() == 0) {
            genMain(world, rand, pos);
        }
        return true;
    }

    public void genMain(World world, Random rand, BlockPos pos) {
        if (TerrainGen.generateOre(world, rand, this, pos, OreGenEvent.GenerateMinable.EventType.CUSTOM)) {
            for (int i = 0; i < 10; ++i) {
                int posX = pos.getX() + rand.nextInt(16);
                int posY = 2 + rand.nextInt(4);
                int posZ = pos.getZ() + rand.nextInt(16);
                BlockPos blockpos = new BlockPos(posX, posY, posZ);
                FBGen.generate(world, rand, blockpos);
            }
        }
    }
}
