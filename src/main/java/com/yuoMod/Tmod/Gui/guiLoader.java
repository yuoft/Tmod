package com.yuoMod.Tmod.Gui;

import com.yuoMod.Tmod.tmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class guiLoader implements IGuiHandler
{
	public static final int GUI_DEMO = 1;
	public guiLoader()
	{
		//第一个参数传入一个Mod的实例，第二个参数传入这个IGuiHandler。
		NetworkRegistry.INSTANCE.registerGuiHandler(tmod.instance, this);
	}
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		// TODO 自动生成的方法存根
		return null;
	}
	
}
