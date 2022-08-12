package com.yuo.Tmod.World;

import java.util.Random;

import com.yuo.Tmod.Common.Blocks.BlockLoader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.event.terraingen.OreGenEvent;

public class EmeraldOreGen extends WorldGenerator {
    private final WorldGenMinable EIOreGenerator = new WorldGenMinable(BlockLoader.emeraldIngotOre.getDefaultState(), 5);

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {
        if (world.provider.getDimension() == 0) {
            genMain(world, rand, pos);
        }
        return true;
    }

    /*
    @Override
    public boolean generate(World world, Random rand, BlockPos position) {
        // 所有放置方块的逻辑均在这里发生。

        // 生成特性的操作即是放置方块的操作。
        // 通常这些方块放置操作不应引发方块更新，所以这里不使用最低有效位（用于决定是否产生方块更新）。
        // 绝大多数时候你可以直接使用 this.setBlockAndNotifyAdequately。
        world.setBlockState(position, Blocks.DIAMOND_BLOCK.getDefaultState(), Constants.BlockFlags.SEND_TO_CLIENTS);

        // 当且仅当特性生成成功时此方法返回 true。
        return false;
    }
    */
    public void genMain(World world, Random rand, BlockPos pos) {
        if (TerrainGen.generateOre(world, rand, this, pos, OreGenEvent.GenerateMinable.EventType.CUSTOM)) {
            for (int i = 0; i < 4; ++i) {
                int posX = pos.getX() + rand.nextInt(16);
                int posY = 32 + rand.nextInt(32);
                int posZ = pos.getZ() + rand.nextInt(16);
                BlockPos blockpos = new BlockPos(posX, posY, posZ);
                EIOreGenerator.generate(world, rand, blockpos);
            }
        }
    }


}
