package com.yuoMod.Tmod.Common.Items;

import com.yuoMod.Tmod.Common.Blocks.Mine;
import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

//拆弹器
public class DombRemover extends Item{

	public DombRemover(String name) {
		super();
		this.setUnlocalizedName(name);
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabsLoader.TMOD);
	}
	//物品使用
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		IBlockState state = worldIn.getBlockState(pos);
		if(state.getBlock() instanceof Mine) {
			if(player.isSneaking()) {
				EntityItem entityItem = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(itemLoader.mine));
				if(!worldIn.isRemote) {
					worldIn.spawnEntity(entityItem);
				}
				worldIn.setBlockToAir(pos);
			}
		}
		return EnumActionResult.PASS;
    }
}
