package com.yuo.Tmod.Common.Blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;

import com.yuo.Tmod.Common.Items.ItemLoader;
import com.yuo.Tmod.Tab.TmodGroup;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EmeraldLeaf extends BlockLeaves {
    //绿宝石树叶
    public EmeraldLeaf(String name) {
        super();
        this.setUnlocalizedName(name);
        this.setHardness(0.5f);
        this.setCreativeTab(TmodGroup.CROP_TAB);
        this.setLightOpacity(1);
        //默认状态
        this.setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, false).withProperty(DECAYABLE, true));
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return this.leavesFancy ? BlockRenderLayer.CUTOUT_MIPPED : BlockRenderLayer.SOLID;
    }

    //使用剪刀得到什么
    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        List<ItemStack> list = new ArrayList<>();
        Random random = new Random();
        ItemStack stack = new ItemStack(this);
        ItemStack stack2 = new ItemStack(ItemLoader.emeraldSapling, 1);
        if (fortune != 0) { //有时运时
            int number = random.nextInt(fortune + 1);
            ItemStack stack1 = new ItemStack(ItemLoader.emeraldSapling, number + 1);
            if (random.nextInt(10) > 4) {
                list.add(0, stack);
                list.add(1, stack1);
            } else list.add(0, stack);
        } else {
            if (random.nextInt(10) > 7) {
                list.add(0, stack);
                list.add(1, stack2);
            } else list.add(0, stack);
        }
        return list;
    }

    @Override
    public EnumType getWoodType(int meta) {
        return EnumType.BIRCH;
    }

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, CHECK_DECAY, DECAYABLE);//设置状态个数
    }

    @Nonnull
    @Override
    public IBlockState getStateFromMeta(int meta) {//判定当前状态
        return this.getDefaultState().withProperty(DECAYABLE, (meta == 4)).withProperty(CHECK_DECAY, (meta == 8));
    }

    @Override
    public int getMetaFromState(IBlockState state) {//状态对应值
        int meta = 0;
        if (state.getValue(DECAYABLE)) {
            meta = 4;
        }

        if (state.getValue(CHECK_DECAY)) {
            meta = 8;
        }

        return meta;
    }
}
