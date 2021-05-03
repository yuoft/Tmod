package com.yuoMod.Tmod.Common;

import java.util.Random;

import com.yuoMod.Tmod.Common.Items.UpGradeGem;
import com.yuoMod.Tmod.Common.Items.itemLoader;
import com.yuoMod.Tmod.Common.Items.ToolAndArmor.OPArmor;
import com.yuoMod.Tmod.Common.Items.ToolAndArmor.OPPickaxe;
import com.yuoMod.Tmod.Common.Items.ToolAndArmor.OPSword;
import com.yuoMod.Tmod.Enchantment.enchantmentLoader;
import com.yuoMod.Tmod.Entity.EntityKiana;
import com.yuoMod.Tmod.Gui.BossHealthHUD;
import com.yuoMod.Tmod.Potion.potionLoader;
import com.yuoMod.Tmod.TileEntity.TileTorcherino;
import com.yuoMod.Tmod.Util.Helper;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
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
	//���ٻ��
	@SubscribeEvent
	public void speedTorch(RightClickBlock event) {
		if(event.getHand() == EnumHand.OFF_HAND) return;
		EntityPlayer player = event.getEntityPlayer();
		TileEntity tile = event.getWorld().getTileEntity(event.getPos());
		if(tile == null || !(tile instanceof TileTorcherino)) return;
		if(!event.getWorld().isRemote)
		{
			TileTorcherino torch = (TileTorcherino) tile;
			if(player.isSneaking()) {
				torch.changeMode(false); //Ǳ���л���Χ
			}else {
				torch.changeMode(true); //��Ǳ���л��ٶ�
			}
			player.sendStatusMessage(torch.getDescription(), true); //hud��ʾ��Ϣ
		}
		event.setCanceled(true);
	}
	//������ʯ��op�� ���ᱻ�ҽ��ջ�
	@SideOnly(value = Side.CLIENT)
	@SubscribeEvent
	public void entityItemUnDeath(ItemEvent event) { //��Ʒʵ���¼�
		EntityItem entityItem = event.getEntityItem();
		Item item = entityItem.getItem().getItem();
		if(item instanceof UpGradeGem || item instanceof OPArmor || item instanceof OPSword 
				|| item instanceof OPPickaxe) {
			entityItem.setEntityInvulnerable(true); // ������Ʒʵ�岻������
		}
	}
	//bossѪ��HUD��ʾ
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
	//�����䷽
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
	//�ھ����
	@SubscribeEvent
	public void breakJiYan(PlayerInteractEvent.LeftClickBlock event)
	{
		World world = event.getWorld();
		if(!world.isRemote) {
			EntityPlayer player = event.getEntityPlayer();
			ItemStack heldItemMainhand = player.getHeldItemMainhand();
			BlockPos pos = event.getPos();
			IBlockState state = world.getBlockState(pos);
//			player.sendMessage(new TextComponentTranslation("���� " +state.getBlock().getUnlocalizedName()+""+ event.getUseBlock() + " " + event.getUseItem()));
			if(heldItemMainhand.getItem().equals(itemLoader.op_pickaxe) && state.getBlock().equals(Blocks.BEDROCK)) {
				world.setBlockToAir(pos);
				world.playSound(null, pos, SoundEvents.BLOCK_ANVIL_BREAK, SoundCategory.AMBIENT, 1.0f, 3.0f);
				EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Blocks.BEDROCK));
				world.spawnEntity(entityItem);
			}
		}
	}
	//�ռ乭 �����������10�˺����Ҳ�����ը ����Ŀ��ʵ�崫��
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
    		EntityLivingBase player = event.getEntityLiving();//��ȡʵ��
    		ItemStack Stack_chest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);//��ȡʵ��װ����
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
    		if(Stack_chest.getItem() instanceof OPArmor || Stack_feet.getItem() instanceof OPArmor
    				|| Stack_head.getItem() instanceof OPArmor || Stack_legs.getItem() instanceof OPArmor) {
    			player.addPotionEffect(new PotionEffect(Potion.getPotionById(12), 100, 0));
    		}
//    		else
//    		{
//    			PotionEffect potion=new PotionEffect(Potion.getPotionById(12),1000000 ,10);//����ʱ��״̬
//    			player.addPotionEffect(potion);//��ʵ�����״̬
//    		}
    	}
    }
	//��ħ����ս��ս
	@SubscribeEvent
	public void warToWar(AttackEntityEvent event) {
		EntityPlayer player = event.getEntityPlayer();
		World world = player.world;
		if(!world.isRemote) {
			ItemStack stack = player.getHeldItemMainhand();
			Integer i = EnchantmentHelper.getEnchantmentLevel(enchantmentLoader.wartowar, stack);
			if( i != null && i > 0) { //�и�ħ
				Random random = new Random();
				player.sendMessage(new TextComponentTranslation("��ħ:" + i));
				switch(i) {  //��ѪЧ���͸�����ȼ����
				case 1: 
					if(random.nextInt(100) > 80)
						player.setHealth(player.getHealth() + i/2.0f);
					break;
				case 2: 
					if(random.nextInt(100) > 60)
						player.setHealth(player.getHealth() + i/2.0f);
					break;
				case 3: 
					if(random.nextInt(100) > 40)
						player.setHealth(player.getHealth() + i/2.0f);
					break;
				case 4: 
					if(random.nextInt(100) > 10)
						player.setHealth(player.getHealth() + i/2.0f);
					break;
				default : break;
				}
			}
		}
	}
	//ȼ��
//	@SubscribeEvent
//	public static void getVanillaFurnaceFuelValue(FurnaceFuelBurnTimeEvent event) {
//	    if (event.getItemStack().getItem().equals(itemLoader.salt_wash)) 
//	    {
//	        event.setBurnTime(10000);
//	        // �����趨Ϊ 0��0 ���������Ʒ����ȼ�ϡ�����׼ȷ��˵�ǡ������Ʒȼ��ʱ���� 0����
//	        // �����趨Ϊ -1��-1 ������ԭ���߼�����������
//	        // ��ͨ�� event.getBurnTime() ��õ�ǰ������ȼ��ʱ�䡣
//	        // ����¼�����ȡ����ȡ����ζ�ź����� Event listener �������յ�����¼�������
//	        // �޷��޸�ȼ��ʱ�䡣
//	    }
//	}
}
