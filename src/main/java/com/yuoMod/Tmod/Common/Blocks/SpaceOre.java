package com.yuoMod.Tmod.Common.Blocks;

import java.util.Random;

import javax.annotation.Nonnull;

import com.yuoMod.Tmod.Common.Items.ItemLoader;
import com.yuoMod.Tmod.Tab.TmodGroup;
import net.minecraft.block.BlockOre;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class SpaceOre extends BlockOre {
    public SpaceOre(String name) {
        super(MapColor.STONE);
        this.setUnlocalizedName(name);
        this.setSoundType(SoundType.STONE);
        this.setHardness(10);
        this.setResistance(500);
        this.setCreativeTab(TmodGroup.TMOD);
        this.setHarvestLevel("pickaxe", 4);
    }

    // SRG func_180660_a�����ھ����������Ʒ����
    @Nonnull
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ItemLoader.space_patch;
    }

    // SRG func_149745_a�����ھ����������Ʒ����
    @Override
    public int quantityDropped(Random random) {
        return random.nextInt(5) + 1;
    }

    // SRG func_149679_a�����ھ�����ʱ��Ӱ��ʱ�������Ʒ����
    @Override
    public int quantityDroppedWithBonus(int fortune, Random random) {
        if (fortune > 0) {
            return this.quantityDropped(random) + (fortune + random.nextInt(fortune));
        } else {
            return this.quantityDropped(random);
        }
    }

    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
        Random random = world instanceof World ? ((World) world).rand : new Random();
        return MathHelper.getInt(random, 30, 70);
    }

    // Forge �� patch��ȡ�� getItem (func_185473_a)�����ڴ���ģʽ������м�ѡȡ����Ĺ��ܡ�
    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(this);
    }
}
