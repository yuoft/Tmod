package com.yuoMod.Tmod.Common.Items;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;
import com.yuoMod.Tmod.Potion.potionLoader;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionFallUse extends ItemPotion{
	public PotionFallUse(String name)
	{
		super();
		this.setUnlocalizedName(name);
		this.setCreativeTab(CreativeTabsLoader.TMOD);
	}
	//物品使用完后
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        EntityPlayer entityplayer = entityLiving instanceof EntityPlayer ? (EntityPlayer)entityLiving : null;
        if(entityplayer !=null)
        {
        	Random random=new Random();
        	if(random.nextInt(100)>89)
        	{
        		entityplayer.addPotionEffect(new PotionEffect(potionLoader.potionFall,1800,1));
        	}
        	else entityplayer.addPotionEffect(new PotionEffect(potionLoader.potionFall,1800,0));
        	return new ItemStack(Items.GLASS_BOTTLE);
        }
        return stack;
    }
	//药水类型添加
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            items.add(PotionUtils.addPotionToItemStack(new ItemStack(this), potionLoader.potionFall_type));
        }
    }
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
       tooltip.add(I18n.format("tmod.item.potion_fall_use1", ""));
       tooltip.add(I18n.format("tmod.item.potion_fall_use2", ""));
    }
}
