package com.yuoMod.Tmod.Gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerDemo extends Container
{
	public ContainerDemo()
    {
        super();
    }
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		// TODO �Զ����ɵķ������
		return true;
	}
	
}
