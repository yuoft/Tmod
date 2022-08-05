package com.yuo.Tmod.Common.Blocks.Crops;

import java.util.Random;

import com.yuo.Tmod.Common.Blocks.BlockLoader;
import com.yuo.Tmod.Common.Items.ItemLoader;
import com.yuo.Tmod.Tab.TmodGroup;

import net.minecraft.block.BlockReed;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AllReeds extends BlockReed {
    public AllReeds(String name) {
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TMOD);
        this.setSoundType(SoundType.PLANT);
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(ItemLoader.appleReeds);
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Items.APPLE;
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        //检测下面一个是不是此作物，或是不是能种植块
        if (worldIn.getBlockState(pos.down()).getBlock() == BlockLoader.appleReeds || this.checkForDrop(worldIn, pos, state)) {
            if (worldIn.isAirBlock(pos.up())) {
                int i;

                for (i = 1; worldIn.getBlockState(pos.down(i)).getBlock() == this; ++i) {
                }

                if (i < 15) {
                    int j = state.getValue(AGE);

                    if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, true)) {
                        if (j == 15) {
                            worldIn.setBlockState(pos.up(), this.getDefaultState());
                            worldIn.setBlockState(pos, state.withProperty(AGE, 0), 4);
                        } else {
                            worldIn.setBlockState(pos, state.withProperty(AGE, j + 1), 4);
                        }
                        net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
                    }
                }
            }
        }
    }
}
