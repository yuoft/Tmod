package com.yuoMod.Tmod.Gui;

import com.yuoMod.Tmod.tmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class guiLoader implements IGuiHandler
{
	public static final int GUI_DEMO = 1;
	public static final int NINE_GUI = 2;
	public guiLoader()
	{
		//第一个参数传入一个Mod的实例，第二个参数传入这个IGuiHandler。
		NetworkRegistry.INSTANCE.registerGuiHandler(tmod.instance, this);
	}
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case GUI_DEMO:
			return new ContainerDemo(player,world.getTileEntity(new BlockPos(x, y, z)));
		case NINE_GUI:
            return new NineContainer(player,world);
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case GUI_DEMO:
			return new GuiContainerDemo(new ContainerDemo(player,world.getTileEntity(new BlockPos(x, y, z))));
		case NINE_GUI:
            return new NineGuiContainer(new NineContainer(player,world));
		default:
			return null;
		}
	}
	
}
