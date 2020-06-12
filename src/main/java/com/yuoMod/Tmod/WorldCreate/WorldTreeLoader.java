package com.yuoMod.Tmod.WorldCreate;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldTreeLoader implements IWorldGenerator
{
	public static final WorldGenerator em_tree=new WorldTreeCreate();
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		switch(world.provider.getDimension())
		{
		case 0:runGenerator(em_tree, world, random, chunkX, chunkZ);break;
		case -1:break;
		case 1:break;
		}
	}
	//绿宝石树木 世界生成
	private void runGenerator(WorldGenerator generator,World world,Random random,int chunkX,int chunkZ)
	{
		for(int i=0;i<10;i++)
		{
			int x=chunkX*16+random.nextInt(16);
			int z=chunkZ*16+random.nextInt(16);
			int y=64+random.nextInt(16);
			BlockPos pos=new BlockPos(x, y, z);
			pos=world.getHeight(pos);
			Biome biome=world.getBiome(pos);
			if(BiomeDictionary.hasType(biome, net.minecraftforge.common.BiomeDictionary.Type.FOREST))
			{
				generator.generate(world, random, pos);
			}
		}
	}
}
