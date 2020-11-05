package com.yuoMod.Tmod.WorldCreate;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldTreeLoader implements IWorldGenerator
{
	public static final WorldGenerator em_tree=new WorldTreeCreate();
	
	//绿宝石树木 世界生成
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		if(random.nextInt(20) > 15)
		{
			int x=chunkX*16+8+random.nextInt(16);
			int z=chunkZ*16+8+random.nextInt(16);
			BlockPos pos=new BlockPos(x, 0, z);
			pos=world.getHeight(pos);
			Biome biome=world.getBiome(pos);
			if(BiomeDictionary.hasType(biome, Type.FOREST) || BiomeDictionary.hasType(biome, Type.PLAINS) ||
					BiomeDictionary.hasType(biome, Type.SWAMP) || BiomeDictionary.hasType(biome, Type.JUNGLE))
			{
				em_tree.generate(world, random, pos);
			}
		}
	}
}
