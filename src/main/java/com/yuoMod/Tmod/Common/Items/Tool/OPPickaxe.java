package com.yuoMod.Tmod.Common.Items.Tool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;
import com.yuoMod.Tmod.Tab.TmodGroup;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OPPickaxe extends ItemTool {
    public static final ToolMaterial OPPICKAXE = EnumHelper.addToolMaterial("op_pickaxe", 99, -1, 999.0f, 0, 1);
    private final ItemHander hander;

    public OPPickaxe(String name) {
        super(1.0f, -2.5f, OPPICKAXE, null);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TMOD);
        this.hander = new ItemHander(this);
    }

    //添加附魔
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            Map<Enchantment, Integer> map = new HashMap<Enchantment, Integer>();
            map.put(Enchantment.getEnchantmentByID(35), 10);
            ItemStack stack = new ItemStack(this);
            EnchantmentHelper.setEnchantments(map, stack);
            items.add(stack);
        }
    }

    //设置挖掘等级
    public int getHarvestLevel(ItemStack stack, String toolClass, @javax.annotation.Nullable net.minecraft.entity.player.EntityPlayer player, @javax.annotation.Nullable IBlockState blockState) {
        return 99;
    }

    //设置工具类型
    @Override
    public Set<String> getToolClasses(ItemStack stack) {
        return ImmutableSet.of("pickaxe", "shovel", "axe");
    }

    //附魔光效
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("tmod.item.op_pickaxe1", ""));
        tooltip.add(I18n.format("tmod.item.op_pickaxe2", ""));
    }

    @Override
    public boolean canHarvestBlock(IBlockState blockIn) {
        return true;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        return Math.max(OPPICKAXE.getEfficiency(), 25.0f);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player) {
        if (player.isSneaking()) {
            Block blockDestroyed = player.getEntityWorld().getBlockState(pos).getBlock();
            this.hander.onBlockStartBreak(stack, player.world, blockDestroyed, pos, player, 10, 20);
        }
        return false;
    }
}
