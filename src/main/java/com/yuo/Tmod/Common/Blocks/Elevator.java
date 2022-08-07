package com.yuo.Tmod.Common.Blocks;

import com.yuo.Tmod.Client.KeyLoader;
import com.yuo.Tmod.Tab.TmodGroup;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

//电梯
public class Elevator extends Block {

    private static final AxisAlignedBB ELEVATOR_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);

    public Elevator(String name) {
        super(Material.ROCK);
        this.setUnlocalizedName(name);
        this.setHarvestLevel("pickaxe", 1);
        this.setHardness(10);
        this.setResistance(20);
        this.setCreativeTab(TmodGroup.TMOD);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return ELEVATOR_AABB;
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityIn;
//			if(!worldIn.isRemote){
            if (KeyLoader.key_UP.isKeyDown()) {
                IBlockState state = worldIn.getBlockState(pos);
                if (state.getBlock() instanceof Elevator) { // 玩家脚下是电梯方块
                    for (int i = 2; i <= 10; i++) { // 在其上方2-10格内寻找电梯方块
                        BlockPos posUp = new BlockPos(pos.getX(), (pos.getY() + i), pos.getZ());
                        IBlockState state2 = worldIn.getBlockState(posUp);
                        if (state2.getBlock() instanceof Elevator) { // 找到后传送玩家
                            player.setPositionAndUpdate(pos.getX() + 0.5, pos.getY() + i + 1, pos.getZ() + 0.5);
                            worldIn.playSound(null, pos.getX() + 0.5, pos.getY() + i + 1, pos.getZ() + 0.5, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                            player.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
                            for (int j = 0; j < 20; j++)
                                worldIn.spawnParticle(EnumParticleTypes.PORTAL, pos.getX() + 0.5, pos.getY() + i + 1, pos.getZ() + 0.5, 0.05d, 0.05d, 0.05d);
                            break; // 退出
                        }
                    }
                }
            }
            if (KeyLoader.key_DOWN.isKeyDown()) {
                IBlockState state = worldIn.getBlockState(pos);
                if (state.getBlock() instanceof Elevator) {
                    for (int i = 2; i <= 10; i++) {
                        BlockPos posUp = new BlockPos(pos.getX(), (pos.getY() - i), pos.getZ());
                        IBlockState state2 = worldIn.getBlockState(posUp);
                        if (state2.getBlock() instanceof Elevator) {
                            player.setPositionAndUpdate(pos.getX() + 0.5, pos.getY() - i + 1, pos.getZ() + 0.5);
                            worldIn.playSound(null, pos.getX() + 0.5, pos.getY() - i + 1, pos.getZ() + 0.5, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                            player.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
                            for (int j = 0; j < 20; j++)
                                worldIn.spawnParticle(EnumParticleTypes.END_ROD, pos.getX() + 0.5, pos.getY() - i + 1, pos.getZ() + 0.5, 0.05d, 0.05d, 0.05d);
                            break;
                        }
                    }
                }
            }
        }
//		}
    }

    // 是不透明方块
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
}
