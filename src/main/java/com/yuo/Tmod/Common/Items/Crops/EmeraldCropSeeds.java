package com.yuo.Tmod.Common.Items.Crops;

import com.yuo.Tmod.Common.Blocks.BlockLoader;
import com.yuo.Tmod.Tab.TmodGroup;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class EmeraldCropSeeds extends Item implements IPlantable {
    public EmeraldCropSeeds(String name) {
        super();
        this.setUnlocalizedName(name);//绿宝石作物种子
        this.setCreativeTab(TmodGroup.CROP_TAB);
        this.setMaxStackSize(64);
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Crop;
    }

    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
        return BlockLoader.emeraldCrops.getDefaultState();
    }

    //种植
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItem(hand);
        IBlockState state = worldIn.getBlockState(pos);
        if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, stack) && state.getBlock().canSustainPlant(state, worldIn, pos, EnumFacing.UP, this) && worldIn.isAirBlock(pos.up())) {
            worldIn.setBlockState(pos.up(), BlockLoader.emeraldCrops.getDefaultState());
            if (!player.capabilities.isCreativeMode && !worldIn.isRemote) {
                stack.shrink(1);
            }
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.FAIL;
    }
}
