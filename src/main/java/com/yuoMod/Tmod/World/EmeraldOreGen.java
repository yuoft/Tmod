package com.yuoMod.Tmod.World;

import java.util.Random;

import com.yuoMod.Tmod.Common.Blocks.BlockLoader;
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
    public EmeraldOreCreate()
    {
        // ��� true �ĺ����ǡ��ڵ��� setBlockAndNotifyAdequately ʱ����ɷ�����¡���
        // setBlockAndNotifyAdequately �� WorldGenerator ���ṩ��һ�� util method��
        // ���Ը���ʵ������� setBlockState �ṩ��ͬ�ĸ���ѡ�
        super(true);
    }
/*
    @Override
    public boolean generate(World world, Random rand, BlockPos position) {
        // ���з��÷�����߼��������﷢����

        // �������ԵĲ������Ƿ��÷���Ĳ�����
        // ͨ����Щ������ò�����Ӧ����������£��������ﲻʹ�������Чλ�����ھ����Ƿ����������£���
        // �������ʱ�������ֱ��ʹ�� this.setBlockAndNotifyAdequately��
        world.setBlockState(position, Blocks.DIAMOND_BLOCK.getDefaultState(), Constants.BlockFlags.SEND_TO_CLIENTS);

        // ���ҽ����������ɳɹ�ʱ�˷������� true��
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
