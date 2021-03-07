package com.yuoMod.Tmod.WorldCreate;

import java.util.Random;

import com.yuoMod.Tmod.Common.Blocks.blockLoader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.event.terraingen.OreGenEvent;

public class RubyOreSpawn extends WorldGenerator
{
	private final WorldGenMinable SaltOreGenerator = new WorldGenMinable(blockLoader.ruby_ore.getDefaultState(),6);
	@Override
	public boolean generate(World world, Random rand, BlockPos pos) 
	{
		switch(world.provider.getDimension())
		{
		case 0:genMain(world, rand, pos);break;
		default : ;
		}
        return true;
	}
	public void genMain(World world, Random rand, BlockPos pos)
	{
		if (TerrainGen.generateOre(world, rand, this, pos, OreGenEvent.GenerateMinable.EventType.CUSTOM))
        {
			for (int i = 0; i < 4; ++i)
            {
                int posX = pos.getX() + rand.nextInt(16);
                int posY = 32 + rand.nextInt(16);
                int posZ = pos.getZ() + rand.nextInt(16);
                BlockPos blockpos = new BlockPos(posX, posY, posZ);
                SaltOreGenerator.generate(world, rand, blockpos);
            }
        }
	}
}
