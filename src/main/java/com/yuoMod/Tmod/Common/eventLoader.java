package com.yuoMod.Tmod.Common;

import com.yuoMod.Tmod.Common.Items.itemLoader;
import com.yuoMod.Tmod.Enchantment.enchantmentLoader;
import com.yuoMod.Tmod.Entity.EntityKiana;
import com.yuoMod.Tmod.Gui.BossHealthHUD;
import com.yuoMod.Tmod.Potion.potionLoader;
import com.yuoMod.Tmod.Util.Helper;

import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class eventLoader 
{
	/*
	 * 各种事件
	 */
	public eventLoader()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }
	//boss血条HUD显示
	@SideOnly(value = Side.CLIENT)
	@SubscribeEvent
    public void renderGameOverlay(RenderGameOverlayEvent.Chat event) {
		Minecraft mc=Minecraft.getMinecraft();
		World world=mc.world;
		for(Entity entity: world.loadedEntityList)
		{
			if(entity instanceof EntityKiana)
			{
				BossHealthHUD hud=new BossHealthHUD((EntityKiana) entity);
		        hud.drawHud();
			}
		}
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
        	return ;
        }
    }
	//铁砧配方
	@SubscribeEvent
	public void NewRecipes(AnvilUpdateEvent event)
	{
		ItemStack stack=event.getLeft();
		ItemStack stack2=event.getRight();
		int count1=stack.getCount();
		int count2=stack2.getCount();
		if(stack.getItem().equals(Items.STRING) && stack2.getItem().equals(itemLoader.space_patch))
		{
			if(count1 <= count2)
			{
				event.setCost(5 * count1);
				event.setMaterialCost(count1);
				event.setOutput(new ItemStack(itemLoader.space_line, count1));
			}
		}
	}
	//空间弓 攻击怪物造成10伤害并且产生爆炸 否则将目标实体传送
	@SubscribeEvent
	public void spaceBow(ProjectileImpactEvent.Arrow event)
	{
		EntityArrow arrow=event.getArrow();
		if(!event.getArrow().getEntityWorld().isRemote)
		{
			if(arrow.shootingEntity instanceof EntityPlayer)
			{
				EntityPlayer player=(EntityPlayer) arrow.shootingEntity;
				ItemStack itemStack=player.getHeldItemMainhand();
				if(itemStack.getItem().equals(itemLoader.space_bow))
				{
					if(event.getRayTraceResult().entityHit instanceof IMob)
					{
						arrow.setDamage(10.0f);
						arrow.world.createExplosion(player, arrow.posX, arrow.posY, arrow.posZ, 5.0f, false);
					}
					else 
					{
						if(event.getRayTraceResult().entityHit instanceof EntityLivingBase)
						{
							Helper.TP((EntityLivingBase) event.getRayTraceResult().entityHit, event.getArrow().getEntityWorld());
						}
					}
				}
			}
		}
	}
    //药水效果，摔落减免
	@SubscribeEvent
    public void potionFall(LivingHurtEvent event)
    {
        if (event.getSource()==DamageSource.FALL)// .getSource().getDamageType().equals("potionFall"))
        {
            PotionEffect effect = event.getEntityLiving().getActivePotionEffect(potionLoader.potionFall);
            if (effect != null)
            {
                if (effect.getAmplifier() == 0)
                {
                	event.setAmount(event.getAmount()/2);
                }
                else
                {
                	event.setAmount(0);
                }
            }
        }
    }
    //附魔，火焰免疫
	@SubscribeEvent
    public void fireImmune(LivingHurtEvent event)
    {
		//伤害来源：火焰，岩浆，熔岩石，燃烧，烟花
    	if((event.getSource()==DamageSource.IN_FIRE) || (event.getSource()==DamageSource.ON_FIRE) ||
    			(event.getSource()==DamageSource.LAVA) || (event.getSource()==DamageSource.HOT_FLOOR) ||
    			(event.getSource()==DamageSource.LIGHTNING_BOLT) || (event.getSource()==DamageSource.FIREWORKS))
    	{
    		EntityLivingBase player = event.getEntityLiving();//获取实体
    		ItemStack Stack_chest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);//获取实体装备槽
    		ItemStack Stack_feet = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
    		ItemStack Stack_head = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
    		ItemStack Stack_legs = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
    		//获取此装备上是否有指定附魔
    		int fireImmune_chest=EnchantmentHelper.getEnchantmentLevel(enchantmentLoader.fireimmune,Stack_chest);
    		int fireImmune_feet=EnchantmentHelper.getEnchantmentLevel(enchantmentLoader.fireimmune,Stack_feet);
    		int fireImmune_head=EnchantmentHelper.getEnchantmentLevel(enchantmentLoader.fireimmune,Stack_head);
    		int fireImmune_legs=EnchantmentHelper.getEnchantmentLevel(enchantmentLoader.fireimmune,Stack_legs);
    		if(fireImmune_chest==1 || fireImmune_feet==1 || fireImmune_head==1 || fireImmune_legs==1)
    		{
    			event.setAmount(0);
    			//耐久减少
    			if(fireImmune_chest==1)
    			{
    				Stack_chest.setItemDamage(Stack_chest.getItemDamage()+2);
    			}
    			else
    			{
    				if(fireImmune_feet==1)
    				{
    					Stack_feet.setItemDamage(Stack_feet.getItemDamage()+2);
    				}
    				else
    				{
    					if(fireImmune_head==1)
    					{
    						Stack_head.setItemDamage(Stack_head.getItemDamage()+2);
    					}
    					else
    					{
    						Stack_legs.setItemDamage(Stack_legs.getItemDamage()+2);
    					}
    				}
    			}
    		}
//    		else
//    		{
//    			PotionEffect potion=new PotionEffect(Potion.getPotionById(12),1000000 ,10);//无限时间状态
//    			player.addPotionEffect(potion);//给实体添加状态
//    		}
    	}
    }
	//燃料
//	@SubscribeEvent
//	public static void getVanillaFurnaceFuelValue(FurnaceFuelBurnTimeEvent event) {
//	    if (event.getItemStack().getItem().equals(itemLoader.salt_wash)) 
//	    {
//	        event.setBurnTime(10000);
//	        // 可以设定为 0。0 代表“这个物品不是燃料”，更准确地说是“这个物品燃烧时间是 0”。
//	        // 可以设定为 -1。-1 代表“由原版逻辑来决定”。
//	        // 可通过 event.getBurnTime() 获得当前决定的燃烧时间。
//	        // 这个事件可以取消。取消意味着后续的 Event listener 将不会收到这个事件，进而
//	        // 无法修改燃烧时间。
//	    }
//	}
}
