package com.yuo.Tmod.Enchantment;

import com.yuo.Tmod.Tmod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class Diamond extends Enchantment {
    Diamond(String name) {
        super(Rarity.RARE, EnumEnchantmentType.DIGGER, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setName(name);
        this.setRegistryName(Tmod.MOD_ID, name);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 10;
    }

    //钻石掉落
    public static void dropDiamond(World world, int diamond, BlockPos pos, int fortune){
        Random rand = world.rand;
        if (rand.nextDouble() < 0.3 + diamond * 0.1 + fortune * 0.05){
            world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(),
                    new ItemStack(Items.DIAMOND, MathHelper.getInt(rand, 1 + fortune, diamond + fortune * 2))));
        }
    }
}
