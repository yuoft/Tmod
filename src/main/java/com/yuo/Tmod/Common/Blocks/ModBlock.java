package com.yuo.Tmod.Common.Blocks;

import com.yuo.Tmod.Tab.TmodGroup;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ModBlock extends Block {
    //绿宝石锭块
    public ModBlock(String name, int hardness, int level) {
        super(Material.IRON);//放置音效
        this.setUnlocalizedName(name);
        this.setHardness(hardness);//硬度
        this.setHarvestLevel("pickaxe", level);//采集工具,等级
        this.setResistance(hardness * 2);//爆炸抗性
        this.setCreativeTab(TmodGroup.TMOD);
        this.setSoundType(SoundType.STONE);//破坏音效
//        new ItemBlock(this).setRegistryName(this.getRegistryName());
    }

    //右击方块 事件
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return false;
    }
}
