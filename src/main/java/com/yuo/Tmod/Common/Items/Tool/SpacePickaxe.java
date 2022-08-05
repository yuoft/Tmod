package com.yuo.Tmod.Common.Items.Tool;

import java.util.List;

import javax.annotation.Nullable;

import com.yuo.Tmod.Tab.TmodGroup;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SpacePickaxe extends ItemPickaxe {
    private final ItemHander hander;

    public SpacePickaxe(String name, ToolMaterial material) {
        super(material);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TMOD);
        this.setHarvestLevel("pickaxe", 5);
        this.attackDamage = 15.0f;//设置攻击伤害
        this.hander = new ItemHander(this);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("tmod.item.space_pickaxe1", ""));
//       tooltip.add(I18n.format("tmod.item.space_pickaxe2", ""));
    }

    //破坏方块时
    @Override
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player) {
        if (player.isSneaking()) {
            Block blockDestroyed = player.getEntityWorld().getBlockState(pos).getBlock();
            this.hander.onBlockStartBreak(stack, player.world, blockDestroyed, pos, player, 3, 6);
        }
        return false;
    }
}
