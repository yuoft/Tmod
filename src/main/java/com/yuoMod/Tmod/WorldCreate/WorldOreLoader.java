package com.yuoMod.Tmod.WorldCreate;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

public class WorldOreLoader 
{
	private static WorldGenerator oreCreate = new WorldOreCreate();
	private static WorldGenerator SpaceOre = new SpaceOreSpawn();
    private BlockPos pos;
    
    public WorldOreLoader()
    {
        MinecraftForge.ORE_GEN_BUS.register(this);
        //this.oreCreate = oreCreate;
    }
    
	@SubscribeEvent
    public void onOreGenPost(OreGenEvent.Post event)
    {
        if (!event.getPos().equals(this.pos))
        {
            this.pos = event.getPos();
            oreCreate.generate(event.getWorld(), event.getRand(), event.getPos());
            SpaceOre.generate(event.getWorld(), event.getRand(), event.getPos());
        }
    }

    @SubscribeEvent
    public void onOreGenGenerateMinable(OreGenEvent.GenerateMinable event)
    {
        if (event.getType() == OreGenEvent.GenerateMinable.EventType.ANDESITE)
        {
            event.setResult(Result.DENY);
        }
    }
    /*
    @SubscribeEvent
    public static void onOreGen(OreGenEvent.Post event) {
       oreCreate.generate(event.getWorld(), event.getRand(), event.getPos());
    }
    */
}
