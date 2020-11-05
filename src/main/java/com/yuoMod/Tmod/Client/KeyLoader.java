package com.yuoMod.Tmod.Client;

import org.lwjgl.input.Keyboard;

import com.yuoMod.Tmod.tmod;
import com.yuoMod.Tmod.Gui.guiLoader;

import net.minecraft.client.Minecraft;
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
	public static final KeyBinding key_X=new KeyBinding("key.tmod.key_X", Keyboard.KEY_X, "key.categories.tmod");
	public static final KeyBinding key_Z=new KeyBinding("key.tmod.key_Z", Keyboard.KEY_Z, "key.categories.tmod"); 

    public KeyLoader()
    {
        ClientRegistry.registerKeyBinding(KeyLoader.key_X);
        ClientRegistry.registerKeyBinding(KeyLoader.key_Z);
        MinecraftForge.EVENT_BUS.register(this);
    }
  //快捷键
  	@SideOnly(Side.CLIENT)
  	@SubscribeEvent
  	public void onKeyInput(InputEvent.KeyInputEvent event) 
  	{
  		EntityPlayer player=Minecraft.getMinecraft().player;
  		World world=Minecraft.getMinecraft().world;
  		if(player !=null)
  		{
  			if(KeyLoader.key_X.isPressed())
  			{
  				player.sendMessage(new TextComponentTranslation("你按下了键："+key_X.getKeyDescription()));
  				BlockPos pos=player.getPosition();
  				player.openGui(tmod.instance, guiLoader.NINE_GUI, world, pos.getX(), pos.getY(), pos.getZ());
  			}
  			if(KeyLoader.key_Z.isPressed())
  			{
  				player.sendMessage(new TextComponentTranslation("你按下了键："+key_Z.getKeyDescription()));
  			}
  		}
  		else Minecraft.getMinecraft().player.sendMessage(new TextComponentTranslation("0000"));
  	}
}
