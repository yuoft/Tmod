package com.yuo.Tmod.Common.Blocks.Crops;

import com.yuo.Tmod.Common.Blocks.BlockLoader;
import com.yuo.Tmod.Common.Items.ItemLoader;
import com.yuo.Tmod.Tab.TmodGroup;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OreLeaf extends BlockLeaves {
    //绿宝石树叶
    public OreLeaf(String name) {
        super();
        this.setUnlocalizedName(name);
        this.setHardness(0.2f);
        this.setCreativeTab(TmodGroup.CROP_TAB);
        this.setLightOpacity(1); //透光率
        this.setTickRandomly(true);
        //默认状态
        this.setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, true).withProperty(DECAYABLE, true));
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        super.updateTick(worldIn, pos, state, rand);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getBlockLayer() {
        return Blocks.LEAVES.getBlockLayer();
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return Blocks.LEAVES.isOpaqueCube(state);
    }

    @Override
    public boolean shouldSideBeRendered(@Nonnull IBlockState blockState, @Nonnull IBlockAccess blockAccess, @Nonnull BlockPos pos, @Nonnull EnumFacing side) {
        // isOpaqueCube returns !leavesFancy to us. We have to fix the variable before calling super
        this.leavesFancy = !Blocks.LEAVES.isOpaqueCube(blockState);

        return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }

    @Override
    public boolean isLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
        return true;
    }

    //使用剪刀得到什么
    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        List<ItemStack> list = new ArrayList<>();
        Random random = new Random();
        ItemStack stack = new ItemStack(this);
        ItemStack dropped = new ItemStack(getItemDropped(world.getBlockState(pos), random, fortune));
        list.add(stack);
        if (random.nextDouble() < 0.05 * fortune){
            list.add(dropped);
        }
        return list;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {

        Block block = state.getBlock();
        if (block == BlockLoader.coalLeaf){
            return ItemLoader.coalSapling;
        }else if (block == BlockLoader.diamondLeaf){
            return ItemLoader.diamondSapling;
        }else if (block == BlockLoader.emeraldLeaf){
            return ItemLoader.emeraldSapling;
        }else if (block == BlockLoader.goldLeaf){
            return ItemLoader.goldSapling;
        }else if (block == BlockLoader.ironLeaf){
            return ItemLoader.ironSapling;
        }else if (block == BlockLoader.lapisLeaf){
            return ItemLoader.lapisSapling;
        }else if (block == BlockLoader.quartzLeaf){
            return ItemLoader.quartzSapling;
        }else if (block == BlockLoader.redstoneLeaf){
            return ItemLoader.redstoneSapling;
        }else if (block == BlockLoader.netheriteLeaf){
            return ItemLoader.netheriteSapling;
        }
        return super.getItemDropped(state, rand, fortune);
    }

    @Override
    public void getDrops(net.minecraft.util.NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        Random rand = world instanceof World ? ((World) world).rand : new Random();
        int chance = this.getSaplingDropChance(state);

        if (fortune > 0) {
            chance -= 2 << fortune;
            if (chance < 10) chance = 10;
        }

        if (rand.nextInt(chance) == 0) { //树苗掉落
            ItemStack drop = new ItemStack(getItemDropped(state, rand, fortune), 1, damageDropped(state));
            if (!drop.isEmpty())
                drops.add(drop);
        }

        this.captureDrops(true);
        if (world instanceof World)
            drops.addAll(dropOre(state, fortune, rand));
        drops.addAll(this.captureDrops(false));
    }

    //矿物掉落
    private NonNullList<ItemStack> dropOre(IBlockState state, int fortune, Random rand) {
        NonNullList<ItemStack> list = NonNullList.create();
        Block block = state.getBlock();
        if (block == BlockLoader.coalLeaf){
            list.add(new ItemStack(ItemLoader.coalNugget, MathHelper.getInt(rand, 5 + fortune, 10 + fortune * 4)));
            if (rand.nextDouble() < 0.2){
                list.add(new ItemStack(Items.COAL, MathHelper.getInt(rand, 1 + fortune, 5 + fortune * 3)));
            }
        }else if (block == BlockLoader.diamondLeaf){
            list.add(new ItemStack(ItemLoader.diamondNugget, MathHelper.getInt(rand, 3 + fortune, 6 + fortune * 2)));
            if (rand.nextDouble() < 0.2){
                list.add(new ItemStack(Items.DIAMOND, MathHelper.getInt(rand, 1 + fortune, 3 + fortune)));
            }
        }else if (block == BlockLoader.emeraldLeaf){
            list.add(new ItemStack(ItemLoader.emeraldNugget, MathHelper.getInt(rand, 3 + fortune, 6 + fortune * 2)));
            if (rand.nextDouble() < 0.2){
                list.add(new ItemStack(Items.EMERALD, MathHelper.getInt(rand, 1 + fortune, 3 + fortune)));
            }
        }else if (block == BlockLoader.goldLeaf){
            list.add(new ItemStack(Items.GOLD_NUGGET, MathHelper.getInt(rand, 4 + fortune, 8 + fortune * 3)));
            if (rand.nextDouble() < 0.2){
                list.add(new ItemStack(Items.GOLD_INGOT, MathHelper.getInt(rand, 1 + fortune, 4 + fortune * 2)));
            }
        }else if (block == BlockLoader.ironLeaf){
            list.add(new ItemStack(Items.IRON_NUGGET, MathHelper.getInt(rand, 4 + fortune, 8 + fortune * 3)));
            if (rand.nextDouble() < 0.2){
                list.add(new ItemStack(Items.IRON_INGOT, MathHelper.getInt(rand, 1 + fortune, 4 + fortune * 2)));
            }
        }else if (block == BlockLoader.lapisLeaf){
            list.add(new ItemStack(ItemLoader.lapisNugget, MathHelper.getInt(rand, 5 + fortune, 10 + fortune * 4)));
            if (rand.nextDouble() < 0.2){
                list.add(new ItemStack(Items.DYE, MathHelper.getInt(rand, 1 + fortune, 6 + fortune * 3), 4));
            }
        }else if (block == BlockLoader.quartzLeaf){
            list.add(new ItemStack(ItemLoader.quartzNugget, MathHelper.getInt(rand, 5 + fortune, 10 + fortune * 4)));
            if (rand.nextDouble() < 0.2){
                list.add(new ItemStack(Items.QUARTZ, MathHelper.getInt(rand, 1 + fortune, 6 + fortune * 3)));
            }
        }else if (block == BlockLoader.redstoneLeaf){
            list.add(new ItemStack(ItemLoader.redstoneNugget, MathHelper.getInt(rand, 5 + fortune, 10 + fortune * 4)));
            if (rand.nextDouble() < 0.2){
                list.add(new ItemStack(Items.REDSTONE, MathHelper.getInt(rand, 1 + fortune, 6 + fortune * 3)));
            }
        }else if (block == BlockLoader.netheriteLeaf){
            list.add(new ItemStack(ItemLoader.netheriteNugget, MathHelper.getInt(rand, 2 + fortune, 6 + fortune)));
            if (rand.nextDouble() < 0.2){
                list.add(new ItemStack(ItemLoader.netheriteScrap, MathHelper.getInt(rand, 1 + fortune, 2 + fortune)));
            }
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
        return this.getDefaultState().withProperty(DECAYABLE, (meta & 4) == 0).withProperty(CHECK_DECAY, (meta & 8) > 0);
    }

    @Override
    public int getMetaFromState(IBlockState state) {//状态对应值
        int i = 0;
        if (!state.getValue(DECAYABLE)) {
            i |= 4;
        }
        if (state.getValue(CHECK_DECAY)) {
            i |= 8;
        }
        return i;
    }


}
