package com.yuoMod.Tmod.Client;

import org.lwjgl.input.Keyboard;

import com.yuoMod.Tmod.Common.Blocks.Elevator;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
/*
 * 热键绑定
 */
public class KeyLoader 
{
	public static final KeyBinding key_UP=new KeyBinding("key.tmod.key_UP", Keyboard.KEY_UP, "key.categories.tmod");
	public static final KeyBinding key_DOWN=new KeyBinding("key.tmod.key_DOWN", Keyboard.KEY_DOWN, "key.categories.tmod"); 

    public KeyLoader()
    {
        ClientRegistry.registerKeyBinding(KeyLoader.key_UP);
        ClientRegistry.registerKeyBinding(KeyLoader.key_DOWN);
        MinecraftForge.EVENT_BUS.register(this);
    }
    //快捷键
  	@SideOnly(Side.CLIENT)
  	@SubscribeEvent
  	public void onKeyInput(InputEvent.KeyInputEvent event) 
  	{
  		Minecraft mc = Minecraft.getMinecraft();
  		EntityPlayerSP player = mc.player;
//  		World world = mc.world;
  		if(player !=null)
  		{
  			if(KeyLoader.key_UP.isPressed())
  			{
//  				player.sendMessage(new TextComponentTranslation("上键"));
//  				elevator(player, world, true);
  			}
  			if(KeyLoader.key_DOWN.isPressed())
  			{
//  				player.sendMessage(new TextComponentTranslation("下键"));
  			}
  		}
  		else Minecraft.getMinecraft().player.sendMessage(new TextComponentTranslation("0000"));
  	}
  	public static void elevator(EntityPlayer player, World world, boolean bool) {
  		BlockPos pos2=player.getPosition();
  		BlockPos pos = new BlockPos(pos2.getX(), pos2.getY() - 1, pos2.getZ());
		IBlockState state = world.getBlockState(pos);
		player.sendMessage(new TextComponentTranslation("方块1:" + pos.toString() + ":--:" + state.getBlock().getUnlocalizedName()));
		if(state.getBlock() instanceof Elevator) { //玩家脚下是电梯方块
			for(int i = 2; i < 10; i++) {  //在其上方2-10格内寻找电梯方块
				BlockPos posUp = new BlockPos(pos.getX(), bool ? (pos.getY() + i) : (pos.getY() - i), pos.getZ());
				IBlockState state2 = world.getBlockState(posUp);
				if(state2.getBlock() instanceof Elevator) { //找到后传送玩家
					player.sendMessage(new TextComponentTranslation("方块2:" + posUp.toString() + ":--:" + state2.getBlock().getUnlocalizedName()));
//					if(player.attemptTeleport(posUp.getX(), posUp.getY() + 1, posUp.getZ())) { //传送成功
						player.setPositionAndUpdate(pos.getX(), pos2.getY() + i + 1, pos.getZ());
//						world.playSound((EntityPlayer)null, posUp.getX(), posUp.getY() + 1, posUp.getZ(), SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
//						player.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
//						world.spawnParticle(EnumParticleTypes.END_ROD, posUp.getX(), posUp.getY() + 1, posUp.getZ(), 0.0d, 0.0d, 0.0d);
						break; //退出
//					}
				}
			}
		}
  	}
}
