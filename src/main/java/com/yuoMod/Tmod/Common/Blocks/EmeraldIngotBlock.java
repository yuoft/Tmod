package com.yuoMod.Tmod.Common.Blocks;

import com.yuoMod.Tmod.Tab.TmodGroup;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EmeraldIngotBlock extends Block {
    //绿宝石锭块
    public EmeraldIngotBlock(String name) {
		/*The setblockunbreakable method is used to set the hardness of the block to - 1, that is, it cannot be damaged.
		Sethardness method is used to set the hardness of the block, such as obsidian is 50, iron block 5, gold block 3, round stone 2, stone 1.5, pumpkin 1, soil 0.5, sugarcane 0, bedrock-1.
		Setharvestlevel method is used to set the mining level of the block. For example, the diamond pickaxe is 3, iron 2, stone 1 and wood gold 0.
		The setlightlevel method is used to set the illumination of the block. The illumination around the block is set value x15, such as magma 1.0, corresponding to 15, red stone torch 0.5, corresponding to 7.5.
		The setlightopacity method is used to set the transmittance of the square. The higher the value, the lower the transmittance. For example, the leaves and spider webs are 1, and the water and ice are 3.
		Setresistance method is used to set the explosion resistance of the block, such as the resistance of wood is 4, stone is 10, obsidian is 2000, and bedrock is 6000000.
		The setstepsound method is used to set the sound of walking on the block.
		The settickrandomly method is used to set whether the block will accept random ticks (such as crops).
		Instantiate and register this box*/
        super(Material.ROCK);//放置音效
        this.setUnlocalizedName(name);
        this.setHardness(15);//硬度
        this.setHarvestLevel("pickaxe", 3);//采集工具,等级
        this.setResistance(50);//爆炸抗性
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
