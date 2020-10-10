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
	 * �����¼�
	 */
	public eventLoader()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }
    //��ݼ�"X"
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event)
    {
        if (KeyLoader.key.isPressed())
        {
            EntityPlayer player = Minecraft.getMinecraft().player;
            player.setHealth(player.getHealth()+10);
            player.addExperienceLevel(ConfigLoader.txp);//������Ҿ���ȼ�
//            player.addChatMessage(new ChatComponentTranslation("chat.tmod.time", world.getTotalWorldTime()));
        }
    }
    //ҩˮЧ����ˤ�����
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
    //��ħ����������
	@SubscribeEvent
    public void fireImmune(LivingHurtEvent event)
    {
		//�˺���Դ�����棬�ҽ�������ʯ��ȼ�գ��̻�
    	if((event.getSource()==DamageSource.IN_FIRE) || (event.getSource()==DamageSource.ON_FIRE) ||
    			(event.getSource()==DamageSource.LAVA) || (event.getSource()==DamageSource.HOT_FLOOR) ||
    			(event.getSource()==DamageSource.LIGHTNING_BOLT) || (event.getSource()==DamageSource.FIREWORKS))
    	{
    		EntityLivingBase player= event.getEntityLiving();//��ȡ���ʵ��
    		ItemStack Stack_chest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);//��ȡ���װ����
    		ItemStack Stack_feet = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
    		ItemStack Stack_head = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
    		ItemStack Stack_legs = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
    		//��ȡ��װ�����Ƿ���ָ����ħ
    		int fireImmune_chest=EnchantmentHelper.getEnchantmentLevel(enchantmentLoader.fireimmune,Stack_chest);
    		int fireImmune_feet=EnchantmentHelper.getEnchantmentLevel(enchantmentLoader.fireimmune,Stack_feet);
    		int fireImmune_head=EnchantmentHelper.getEnchantmentLevel(enchantmentLoader.fireimmune,Stack_head);
    		int fireImmune_legs=EnchantmentHelper.getEnchantmentLevel(enchantmentLoader.fireimmune,Stack_legs);
    		if(fireImmune_chest==1 || fireImmune_feet==1 || fireImmune_head==1 || fireImmune_legs==1)
    		{
    			event.setAmount(0);
    			//�;ü���
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
//    			PotionEffect potion=new PotionEffect(Potion.getPotionById(12),1000000 ,10);//����ʱ��״̬
//    			player.addPotionEffect(potion);//��ʵ�����״̬
//    		}
    	}
    }
	//ȼ��
	/*
	@SubscribeEvent
	public static void getVanillaFurnaceFuelValue(FurnaceFuelBurnTimeEvent event) {
	    if (event.getItemStack().getItem()==itemLoader.emerald_powder) {
	        event.setBurnTime(-1);
	        // �����趨Ϊ 0��0 ���������Ʒ����ȼ�ϡ�����׼ȷ��˵�ǡ������Ʒȼ��ʱ���� 0����
	        // �����趨Ϊ -1��-1 ������ԭ���߼�����������
	        // ��ͨ�� event.getBurnTime() ��õ�ǰ������ȼ��ʱ�䡣
	        // ����¼�����ȡ����ȡ����ζ�ź����� Event listener �������յ�����¼�������
	        // �޷��޸�ȼ��ʱ�䡣
	    }
	    else
	    {
	    	event.setBurnTime(100);
	    }
	}
	*/
	//�ھ�ȼ�
//	@SubscribeEvent
//    public void emerald_ingot_block(HarvestCheck event)
//    {
//		event.setCanHarvest(false);
//		IBlockState block=event.getTargetBlock();//�õ��ھ򷽿�
//		EntityPlayer player=event.getEntityPlayer();//�õ��ھ����
//		ItemStack pickaxe=player.getHeldItemMainhand();//�õ����������Ʒ
//		ItemStack e_pickaxe=new ItemStack(itemLoader.emerald_pickaxe);
//		if((block.equals(blockLoader.emerald_ingot_block))&&(!pickaxe.equals(e_pickaxe)))
//		{
//			event.setCanHarvest(false);//�����ھ��Ƿ�ɹ�
//			System.out.println("ʹ�õĸ���"+pickaxe.toString());
//			System.out.println("�ھ�ķ���"+block.toString());
//			FMLCommonHandler.instance().getMinecraftServerInstance();
//			ITextComponent component;
//			component.getFormattedText();
//			player.sendMessage();
//		}
//    }
	//��Ƶ����
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
    public void onPlayerItemCrafted(ItemCraftedEvent event)
    {
//		event.player.playSound(soundLoader.CLOSE,1.0f,1.0f);
    }
}
