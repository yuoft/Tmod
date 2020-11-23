package com.yuoMod.Tmod.Common.Items;

import com.yuoMod.Tmod.tmod;
import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;
import com.yuoMod.Tmod.Gui.guiLoader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StorageRing extends Item
{
	public StorageRing(String name) 
	{
		super();
		this.setUnlocalizedName(name);
		this.setCreativeTab(CreativeTabsLoader.TMOD);
		this.setMaxStackSize(1);
	}
	//右键打开gui
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack=playerIn.getHeldItemMainhand();
		if (!worldIn.isRemote) {//判断是否是service
			if(stack.getItem().equals(itemLoader.storage_ring_big))
			{
				BlockPos pos=playerIn.getPosition();
				playerIn.openGui(tmod.instance, guiLoader.NINE_GUI, worldIn, pos.getX(), pos.getY(), pos.getZ());
			}
			if(stack.getItem().equals(itemLoader.storage_ring_in))
			{
				BlockPos pos=playerIn.getPosition();
				playerIn.openGui(tmod.instance, guiLoader.SIX_GUI, worldIn, pos.getX(), pos.getY(), pos.getZ());
			}
			if(stack.getItem().equals(itemLoader.storage_ring_small))
			{
				BlockPos pos=playerIn.getPosition();
				playerIn.openGui(tmod.instance, guiLoader.THREE_GUI, worldIn, pos.getX(), pos.getY(), pos.getZ());
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
	}
}
