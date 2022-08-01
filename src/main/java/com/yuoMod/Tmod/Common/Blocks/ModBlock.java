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

public class ModBlock extends Block {
    //�̱�ʯ����
    public ModBlock(String name, int hardness, int level) {
        super(Material.IRON);//������Ч
        this.setUnlocalizedName(name);
        this.setHardness(hardness);//Ӳ��
        this.setHarvestLevel("pickaxe", level);//�ɼ�����,�ȼ�
        this.setResistance(hardness * 2);//��ը����
        this.setCreativeTab(TmodGroup.TMOD);
        this.setSoundType(SoundType.STONE);//�ƻ���Ч
//        new ItemBlock(this).setRegistryName(this.getRegistryName());
    }

    //�һ����� �¼�
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return false;
    }
}
