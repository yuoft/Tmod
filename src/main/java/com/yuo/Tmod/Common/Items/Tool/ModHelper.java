package com.yuo.Tmod.Common.Items.Tool;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ModHelper {
    //传送效果 原版紫颂果
    public static void TP(EntityLivingBase entityLiving, World worldIn) {
        if (!worldIn.isRemote) {
            double d0 = entityLiving.posX;
            double d1 = entityLiving.posY;
            double d2 = entityLiving.posZ;

            for (int i = 0; i < 16; ++i) {
                double d3 = entityLiving.posX + (entityLiving.getRNG().nextDouble() - 0.5D) * 16.0D;
                double d4 = MathHelper.clamp(entityLiving.posY + (double) (entityLiving.getRNG().nextInt(16) - 8), 0.0D, worldIn.getActualHeight() - 1);
                double d5 = entityLiving.posZ + (entityLiving.getRNG().nextDouble() - 0.5D) * 16.0D;

                if (entityLiving.isRiding()) {
                    entityLiving.dismountRidingEntity();
                }

                if (entityLiving.attemptTeleport(d3, d4, d5)) {
                    worldIn.playSound(null, d0, d1, d2, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    entityLiving.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
                    break;
                }
            }
        }
    }
}
