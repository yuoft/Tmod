package com.yuoMod.Tmod.Common.Items.ToolAndArmor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;
import javax.swing.Timer;

import com.yuoMod.Tmod.Common.Items.itemLoader;
import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
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
	public emerald_pickaxe(String name,ToolMaterial toolmaterial) 
	{
		super(toolmaterial);
		this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabsLoader.TMOD);
        this.setHarvestLevel(name, 4);
	}
	//�ھ����
	public boolean canHarvestBlock(IBlockState blockIn)
    {
		return true;
    }
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
       tooltip.add(I18n.format("tmod.item.emerald_pickaxe1", ""));
       tooltip.add(I18n.format("tmod.item.emerald_pickaxe2", ""));
    }
	//����
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(playerIn.experienceLevel >= 10)
		{
			playerIn.experienceLevel-=10;
			playerIn.addPotionEffect(new PotionEffect(Potion.getPotionById(3), 1200, 1));
			playerIn.addPotionEffect(new PotionEffect(Potion.getPotionById(26), 1200, 0));
			playerIn.addPotionEffect(new PotionEffect(Potion.getPotionById(23), 1200, 0));
		}
		else playerIn.sendMessage(new TextComponentTranslation("tmod.text.emeraldSword"));
		return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
	}
	//��˪����
	@Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		Random random=new Random();
        if (random.nextInt(100) > 89 && stack.getItem().equals(itemLoader.emerald_pickaxe) && target instanceof IMob) {
            target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60, 3));//���ù���״̬������4��
            World world=attacker.world;
            BlockPos pos=target.getPosition();
			IBlockState state=Blocks.FROSTED_ICE.getDefaultState();//���ñ���
            world.setBlockState(pos, state);
            BlockPos pos2=new BlockPos(pos.getX(), pos.getY()+1, pos.getZ());
            world.setBlockState(pos2, state);
            int delay=2000;    //ʱ��������λΪ����
            ActionListener taskPerformer=new ActionListener()//������ʱ��
            {
				@Override
				public void actionPerformed(ActionEvent e) {//�����¼�
					IBlockState blockState=Blocks.AIR.getDefaultState();
					world.setBlockState(pos, blockState);
					world.setBlockState(pos2, blockState);
				}
            };
            Timer timer = new Timer(delay,taskPerformer);
            timer.start();//��ʱ��ʼ
        }
        return super.hitEntity(stack, target, attacker);
    }
}
