package com.yuo.Tmod.Entity;

import com.yuo.Tmod.Common.Items.ItemLoader;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.Random;

public class EntityHelper {

    private static final Random rand = new Random();

    //根据不同掉落等级确定不同掉落物
    public static void setMobDrops(EntityMob mob, int lootLevel, int lootingModifier) {
        switch (lootLevel) {
            case 0: {
                ItemStack stack = new ItemStack(Items.DIAMOND, rand.nextInt(1 + lootingModifier));
                ItemStack stack1 = new ItemStack(Items.EMERALD, rand.nextInt(1 + lootingModifier));
                ItemStack stack2 = new ItemStack(Items.IRON_INGOT, rand.nextInt(2 + lootingModifier));
                ItemStack stack3 = new ItemStack(Items.GOLD_INGOT, rand.nextInt(2 + lootingModifier));
                ItemStack stack8 = new ItemStack(ItemLoader.expBig, rand.nextInt(1 + lootingModifier));
                ItemStack stack9 = new ItemStack(ItemLoader.expSmall, rand.nextInt(3 + lootingModifier));
                ItemStack stack4 = new ItemStack(ItemLoader.yuanshi, rand.nextInt(2 + lootingModifier));
                mob.entityDropItem(stack, 0.0f);
                mob.entityDropItem(stack1, 0.0f);
                mob.entityDropItem(stack2, 0.0f);
                mob.entityDropItem(stack3, 0.0f);
                mob.entityDropItem(stack8, 0.0f);
                mob.entityDropItem(stack9, 0.0f);
                mob.entityDropItem(stack4, 0.0f);
            }
            break;
            case 1: {
                ItemStack stack = new ItemStack(Items.DIAMOND, rand.nextInt(2 + lootingModifier));
                ItemStack stack1 = new ItemStack(Items.EMERALD, rand.nextInt(2 + lootingModifier));
                ItemStack stack2 = new ItemStack(Items.IRON_INGOT, rand.nextInt(3 + lootingModifier));
                ItemStack stack3 = new ItemStack(Items.GOLD_INGOT, rand.nextInt(3 + lootingModifier));
                ItemStack stack4 = new ItemStack(Blocks.DIAMOND_BLOCK, rand.nextInt(1 + lootingModifier));
                ItemStack stack5 = new ItemStack(Blocks.EMERALD_BLOCK, rand.nextInt(1 + lootingModifier));
                ItemStack stack6 = new ItemStack(Blocks.IRON_BLOCK, rand.nextInt(2 + lootingModifier));
                ItemStack stack7 = new ItemStack(Blocks.GOLD_BLOCK, rand.nextInt(2 + lootingModifier));
                ItemStack stack8 = new ItemStack(ItemLoader.expBig, rand.nextInt(2 + lootingModifier));
                ItemStack stack9 = new ItemStack(ItemLoader.expSmall, rand.nextInt(4 + lootingModifier));
                ItemStack stack10 = new ItemStack(ItemLoader.yuanshi, rand.nextInt(3 + lootingModifier));
                mob.entityDropItem(stack, 0.0f);
                mob.entityDropItem(stack1, 0.0f);
                mob.entityDropItem(stack2, 0.0f);
                mob.entityDropItem(stack3, 0.0f);
                mob.entityDropItem(stack4, 0.0f);
                mob.entityDropItem(stack5, 0.0f);
                mob.entityDropItem(stack6, 0.0f);
                mob.entityDropItem(stack7, 0.0f);
                mob.entityDropItem(stack8, 0.0f);
                mob.entityDropItem(stack9, 0.0f);
                mob.entityDropItem(stack10, 0.0f);
            }
            break;
            case 2: {
                ItemStack stack = new ItemStack(ItemLoader.totemSmall, rand.nextInt(2));
                ItemStack stack7 = new ItemStack(ItemLoader.netherStarSmall, rand.nextInt(2));
                ItemStack stack1 = new ItemStack(Blocks.DIAMOND_BLOCK, rand.nextInt(3 + lootingModifier));
                ItemStack stack2 = new ItemStack(Blocks.EMERALD_BLOCK, rand.nextInt(3 + lootingModifier));
                ItemStack stack3 = new ItemStack(Blocks.IRON_BLOCK, rand.nextInt(4 + lootingModifier));
                ItemStack stack4 = new ItemStack(Blocks.GOLD_BLOCK, rand.nextInt(4 + lootingModifier));
                ItemStack stack6 = new ItemStack(ItemLoader.expBig, rand.nextInt(4) + lootingModifier);
                ItemStack stack8 = new ItemStack(ItemLoader.yuanshi, rand.nextInt(4) + lootingModifier);
                ItemStack stack9 = new ItemStack(ItemLoader.jiejing, rand.nextInt(2) + lootingModifier);
                mob.entityDropItem(stack, 0.0f);
                mob.entityDropItem(stack1, 0.0f);
                mob.entityDropItem(stack2, 0.0f);
                mob.entityDropItem(stack3, 0.0f);
                mob.entityDropItem(stack4, 0.0f);
                mob.entityDropItem(stack6, 0.0f);
                mob.entityDropItem(stack7, 0.0f);
                mob.entityDropItem(stack8, 0.0f);
                mob.entityDropItem(stack9, 0.0f);
            }
            break;
            case 3: {
                ItemStack stack = new ItemStack(ItemLoader.spaceCore, rand.nextInt(2) + lootingModifier);
                ItemStack stack1 = new ItemStack(Blocks.DIAMOND_BLOCK, rand.nextInt(4) + lootingModifier);
                ItemStack stack2 = new ItemStack(Blocks.EMERALD_BLOCK, rand.nextInt(4) + lootingModifier);
                ItemStack stack3 = new ItemStack(ItemLoader.expBig, rand.nextInt(10) + lootingModifier + 1);
                ItemStack stack4 = new ItemStack(Items.TOTEM_OF_UNDYING, rand.nextInt(2));
                ItemStack stack5 = new ItemStack(ItemLoader.yuanshi, rand.nextInt(6) + lootingModifier);
                ItemStack stack6 = new ItemStack(ItemLoader.jiejing, rand.nextInt(4) + lootingModifier + 1);
                mob.entityDropItem(stack, 0.0f);
                mob.entityDropItem(stack1, 0.0f);
                mob.entityDropItem(stack2, 0.0f);
                mob.entityDropItem(stack3, 0.0f);
                mob.entityDropItem(stack4, 0.0f);
                mob.entityDropItem(stack5, 0.0f);
                mob.entityDropItem(stack6, 0.0f);
            }
            break;
            default:
                break;
        }
    }

    //是否掉落
    public static boolean randomDrop(boolean wasRecentlyHit, int lootingModifier) {
        int i = rand.nextInt(100);
        return wasRecentlyHit && i < (50 + lootingModifier * 5);
    }
}
