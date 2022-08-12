package com.yuo.Tmod.Common.Items;

import com.yuo.Tmod.Tab.TmodGroup;
import com.yuo.Tmod.Entity.Villager.VillagerLoader;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SpaceVillagerEgg extends Item {
    public SpaceVillagerEgg(String name) {
        super();
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.OTHER_TAB);
        this.setMaxStackSize(16);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            EntityVillager villager = new EntityVillager(worldIn);
            villager.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
            villager.setProfession(VillagerLoader.SPACE);
            worldIn.spawnEntity(villager);
            //用完后消失
            player.getHeldItem(hand).shrink(1);
        }
        return EnumActionResult.PASS;
    }
}
