package com.yuo.Tmod.World;

import java.util.Random;

import com.yuo.Tmod.Common.Blocks.BlockLoader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.event.terraingen.OreGenEvent;

public class SaltOreGen extends WorldGenerator {
    private final WorldGenMinable SaltOreGenerator = new WorldGenMinable(BlockLoader.saltOre.getDefaultState(), 6);

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {
        if (world.provider.getDimension() == 0) {
            genMain(world, rand, pos);
        }
        return true;
    }

    public void genMain(World world, Random rand, BlockPos pos) {
        if (TerrainGen.generateOre(world, rand, this, pos, OreGenEvent.GenerateMinable.EventType.CUSTOM)) {
            for (int i = 0; i < 3; ++i) {
                int posX = pos.getX() + rand.nextInt(16);
                int posY = 64 + rand.nextInt(32);
                int posZ = pos.getZ() + rand.nextInt(16);
                BlockPos blockpos = new BlockPos(posX, posY, posZ);
                SaltOreGenerator.generate(world, rand, blockpos);
            }
        }
    }
}
