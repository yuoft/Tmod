package com.yuoMod.Tmod.Common;

import com.yuoMod.Tmod.Client.KeyLoader;
import com.yuoMod.Tmod.Enchantment.enchantmentLoader;
import com.yuoMod.Tmod.Potion.potionLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
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
    //快捷键"X"
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event)
    {
        if (KeyLoader.key.isPressed())
        {
            EntityPlayer player = Minecraft.getMinecraft().player;
            player.setHealth(player.getHealth()+10);
            player.addExperienceLevel(ConfigLoader.txp);//给与玩家经验等级
//            player.addChatMessage(new ChatComponentTranslation("chat.tmod.time", world.getTotalWorldTime()));
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
    		EntityLivingBase player= event.getEntityLiving();//获取玩家实体
    		ItemStack Stack_chest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);//获取玩家装备槽
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
	/*
	@SubscribeEvent
	public static void getVanillaFurnaceFuelValue(FurnaceFuelBurnTimeEvent event) {
	    if (event.getItemStack().getItem()==itemLoader.emerald_powder) {
	        event.setBurnTime(-1);
	        // 可以设定为 0。0 代表“这个物品不是燃料”，更准确地说是“这个物品燃烧时间是 0”。
	        // 可以设定为 -1。-1 代表“由原版逻辑来决定”。
	        // 可通过 event.getBurnTime() 获得当前决定的燃烧时间。
	        // 这个事件可以取消。取消意味着后续的 Event listener 将不会收到这个事件，进而
	        // 无法修改燃烧时间。
	    }
	    else
	    {
	    	event.setBurnTime(100);
	    }
	}
	*/
	//挖掘等级
//	@SubscribeEvent
//    public void emerald_ingot_block(HarvestCheck event)
//    {
//		event.setCanHarvest(false);
//		IBlockState block=event.getTargetBlock();//得到挖掘方块
//		EntityPlayer player=event.getEntityPlayer();//得到挖掘玩家
//		ItemStack pickaxe=player.getHeldItemMainhand();//得到玩家主手物品
//		ItemStack e_pickaxe=new ItemStack(itemLoader.emerald_pickaxe);
//		if((block.equals(blockLoader.emerald_ingot_block))&&(!pickaxe.equals(e_pickaxe)))
//		{
//			event.setCanHarvest(false);//设置挖掘是否成功
//			System.out.println("使用的镐子"+pickaxe.toString());
//			System.out.println("挖掘的方块"+block.toString());
//			FMLCommonHandler.instance().getMinecraftServerInstance();
//			ITextComponent component;
//			component.getFormattedText();
//			player.sendMessage();
//		}
//    }
	//音频播放
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
    public void onPlayerItemCrafted(ItemCraftedEvent event)
    {
//		event.player.playSound(soundLoader.CLOSE,1.0f,1.0f);
    }
}
