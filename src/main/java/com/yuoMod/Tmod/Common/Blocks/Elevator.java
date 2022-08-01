package com.yuoMod.Tmod.Common.Blocks;

import com.yuoMod.Tmod.Client.KeyLoader;
import com.yuoMod.Tmod.Tab.TmodGroup;

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

//����
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
                if (state.getBlock() instanceof Elevator) { // ��ҽ����ǵ��ݷ���
                    for (int i = 2; i <= 10; i++) { // �����Ϸ�2-10����Ѱ�ҵ��ݷ���
                        BlockPos posUp = new BlockPos(pos.getX(), (pos.getY() + i), pos.getZ());
                        IBlockState state2 = worldIn.getBlockState(posUp);
                        if (state2.getBlock() instanceof Elevator) { // �ҵ��������
                            player.setPositionAndUpdate(pos.getX() + 0.5, pos.getY() + i + 1, pos.getZ() + 0.5);
                            worldIn.playSound(null, pos.getX() + 0.5, pos.getY() + i + 1, pos.getZ() + 0.5, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                            player.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
                            worldIn.spawnParticle(EnumParticleTypes.END_ROD, pos.getX() + 0.5, pos.getY() + i + 1, pos.getZ() + 0.5, 0.0d, 0.0d, 0.0d);
                            break; // �˳�
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
                            worldIn.spawnParticle(EnumParticleTypes.END_ROD, pos.getX() + 0.5, pos.getY() - i + 1, pos.getZ() + 0.5, 0.0d, 0.0d, 0.0d);
                            break;
                        }
                    }
                }
            }
        }
//		}
    }

    // �ǲ�͸������
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
}
