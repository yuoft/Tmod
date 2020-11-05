package com.yuoMod.Tmod.Common.Items;

import java.util.List;

import javax.annotation.Nullable;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class emerald_pickaxe extends ItemPickaxe

{
	/*
	The harvestlevel parameter represents the level of the tool you make. This is especially obvious in pickaxes. For example, if the wood is 0, only the diamonds with the corresponding level of 0 can be excavated to drop objects, such as stones. 
	If the diamond is 3, then the diamonds with the corresponding level of 3 can be excavated, and other pickaxes cannot dig out objects, such as obsidian. The highest level 3 is used here
	The maxuses parameter indicates the durability of the tool. For example, the diamond tool is 1561, with the highest durability, while the wood tool is 59, with the lowest durability. This value is deliberately reduced to 16
	The efficiency parameter indicates the efficiency of the tool. The efficiency is proportional to the value of this parameter. This value is deliberately increased to 16.0f
	The damagevsentity parameter indicates the attack damage strength. Similarly, the force is positively correlated with the value of the parameter. Here is 0.0F, indicating low attack power
	The enchantability parameter is related to the enchantment level. The system of enchantment level in minecraft is very complex. But one thing is that the higher the value is, the easier it is for the corresponding enchant to get a higher level. 
	That's why gold is more likely to get high-level enchantments, while stone's enchantments are relatively low. It's 10 here. It's the same as a diamond
	*/
	protected emerald_pickaxe(String name,ToolMaterial toolmaterial) 
	{
		super(toolmaterial);
		this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabsLoader.TMOD);
        this.setHarvestLevel(name, 4);
	}
	//ÍÚ¾ò¿ØÖÆ
	public boolean canHarvestBlock(IBlockState blockIn)
    {
		return true;
    }
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
       tooltip.add(I18n.format("tmod.item.emerald_pickaxe", ""));
    }
}
