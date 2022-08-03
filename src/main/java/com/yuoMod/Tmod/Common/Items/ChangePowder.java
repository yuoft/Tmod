package com.yuoMod.Tmod.Common.Items;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.yuoMod.Tmod.Tab.TmodGroup;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ChangePowder extends Item {
    public ChangePowder(String name) {
        super();
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TMOD);
        this.setMaxStackSize(64);
    }

    //物品使用
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            Block block = worldIn.getBlockState(pos).getBlock();
            if (block instanceof BlockSapling) {
                Random random = new Random();
                if (random.nextInt(100) > 79) {
                    player.sendMessage(new TextComponentTranslation("tmod.text.change_powder1"));
                } else {
                    worldIn.playEvent(2005, pos, 0);
                    worldIn.setBlockState(pos, ItemLoader.emeraldSapling.getBlock().getDefaultState());
                    // 构造器里把 Translation Key 放进去即可。
                    player.sendMessage(new TextComponentTranslation("tmod.text.change_powder2"));
                }
                ItemStack itemStack = player.getHeldItem(hand);
                itemStack.setCount(itemStack.getCount() - 1);
            }
        }
        return EnumActionResult.PASS;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("tmod.item.change_powder3", ""));
        tooltip.add(I18n.format("tmod.item.change_powder4", ""));
    }
}
