package com.yuoMod.Tmod.Common.Blocks;

import com.yuoMod.Tmod.tmod;
import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;
import com.yuoMod.Tmod.Gui.guiLoader;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class power_extractor extends Block
{

	public power_extractor(String name) 
	{
		super(Material.ROCK);
		this.setUnlocalizedName(name);
		this.setHardness(5);
		this.setResistance(50);
		this.setCreativeTab(CreativeTabsLoader.TMOD);
		this.setSoundType(SoundType.STONE);
	}

	//������ȾĬ�Ϸ���3
	public int getRenderType() {
		return 3;
	}
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
//		int id = guiLoader.GUI_DEMO;
//		// playerIn�Ǹ�EntityPlayer
//		// 5����arbitrary number��������IGuiHandler�е�id�����庬����ʵ�־���
//		// worldIn�Ǹ�World
//		// ��������xyzû��ǿ��Ҫ�������꣬�����ڴ���������
//		playerIn.openGui(tmod.instance, id, worldIn, pos.getX(), pos.getY(), pos.getZ());
//		return true;
		if (!worldIn.isRemote)
        {
            int id = guiLoader.GUI_DEMO;
            playerIn.openGui(tmod.instance, id, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

}
