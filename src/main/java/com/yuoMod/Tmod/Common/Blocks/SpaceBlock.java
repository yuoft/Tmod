package com.yuoMod.Tmod.Common.Blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SpaceBlock extends Block
{
	//绿宝石锭块
	public SpaceBlock(String name)
	{
        super(Material.ROCK);//放置音效
        this.setUnlocalizedName(name);
        this.setHardness(30);//硬度
        this.setHarvestLevel("pickaxe", 5);//采集工具,等级
        this.setResistance(500);//爆炸抗性
        this.setCreativeTab(CreativeTabsLoader.TMOD);
        this.setSoundType(SoundType.STONE);//破坏音效
    }
	//实体在方块上走时
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
    {
		if(entityIn instanceof EntityLivingBase)
		{
			//原版紫颂果效果
			EntityLivingBase entityLiving=(EntityLivingBase) entityIn;
			if (!worldIn.isRemote)
	        {
	            double d0 = entityLiving.posX;
	            double d1 = entityLiving.posY;
	            double d2 = entityLiving.posZ;

	            for (int i = 0; i < 16; ++i)
	            {
	                double d3 = entityLiving.posX + (entityLiving.getRNG().nextDouble() - 0.5D) * 16.0D;
	                double d4 = MathHelper.clamp(entityLiving.posY + (double)(entityLiving.getRNG().nextInt(16) - 8), 0.0D, (double)(worldIn.getActualHeight() - 1));
	                double d5 = entityLiving.posZ + (entityLiving.getRNG().nextDouble() - 0.5D) * 16.0D;

	                if (entityLiving.isRiding())
	                {
	                    entityLiving.dismountRidingEntity();
	                }

	                if (entityLiving.attemptTeleport(d3, d4, d5))
	                {
	                    worldIn.playSound((EntityPlayer)null, d0, d1, d2, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
	                    entityLiving.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
	                    break;
	                }
	            }
	        }
		}
    }
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
       tooltip.add(I18n.format("tmod.block.space_block", ""));
    }
}
