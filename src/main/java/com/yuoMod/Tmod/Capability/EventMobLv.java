package com.yuoMod.Tmod.Capability;

import java.util.ArrayList;
import java.util.Random;
import com.yuoMod.Tmod.tmod;
import com.yuoMod.Tmod.Common.ConfigLoader;
import com.yuoMod.Tmod.Common.Items.itemLoader;
import com.yuoMod.Tmod.Entity.EntityKiana;
import com.yuoMod.Tmod.Network.MessagePlayerLevel;
import com.yuoMod.Tmod.Network.NetworkLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.AnimalTameEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EventMobLv 
{
	// ��һ������ָ��һ����� IAttribute ��Ϊ���� parent��һ�㶼���� null��
	// ����������������Ǵӱ���������������Ļ���������ϡ�ԭ���ͨ��
	// IAttribute.getParent() ������ parent ����һ�����뵽Ŀ��ʵ���ϡ�
	// �ڶ��������� id����ְ translation key����Ҫ�ͱ�����������ˡ�
	// �����������Ǹ����Եĳ�ʼֵ��
	// ���ĸ�����ָ�����ޣ���������ͱ��������ֵ��
	// ���������ָ�����ޣ����������ֻ�������ֵ��
	//
	// setShouldWatch �����˸����Ե�ֵ�ı仯�Ƿ���Ҫͬ�����߼��ͻ��ˡ�
	// setShouldWatch(false) �����߼��ͻ��˿����������Եı仯��
	// ����㲻ϣ���ͻ��˿������ֵ�����߿ͻ��˲���Ҫ֪�����ֵ����������
	// ����΢��Լһ������
	public static final IAttribute LIVING_LEVEL = new RangedAttribute(null, "tmod.attribute.level", 0, 0, ConfigLoader.level).setDescription("Level").setShouldWatch(true);
	
	/*
	 * ����ȼ�
	 * �����¼����������¼������ݾ�������жϵȼ�
	 */
	public EventMobLv()
	{
		MinecraftForge.EVENT_BUS.register(this);
	}
	//���������¼�
	@SubscribeEvent
    public void mobSpawnLv(EntityJoinWorldEvent event) {
		Entity entity = event.getEntity();
		if(entity instanceof EntityLiving) {
			BlockPos pos = new BlockPos(entity.posX, entity.posY, entity.posZ); //��������
			Double lv = getMobLv(pos);//��ȡ����ȼ�
			EntityLiving living = (EntityLiving) entity;
			Double attr = living.getEntityAttribute(LIVING_LEVEL).getBaseValue();
			if(attr == null || attr > 0) { //ʵ��û�еȼ�ʱ���Ż�Ϊʵ����������
				return ;
//				System.out.println("����" + attr );
			}else {
				World world = event.getWorld();
				if(living instanceof EntityKiana){
					lv = ConfigLoader.level * 0.9; //ģ��boss�ȼ��̶�Ϊ90��
				}
				if(living instanceof EntityDragon || living instanceof EntityWither) {
					lv = ConfigLoader.level * 0.5; //ԭ��boss�ȼ��̶�Ϊ50��
				}
				if(world.provider.getDimensionType().equals(DimensionType.NETHER)) {
					lv += 10 ; //����������������10��
					lv = lv < ConfigLoader.level ? lv: ConfigLoader.level;
				}
				if(world.provider.getDimensionType().equals(DimensionType.THE_END)) {
					lv += 20; //����������������20��
					lv = lv < ConfigLoader.level ? lv: ConfigLoader.level;
				}
				setMobAttr(living, lv);
				
			}
		}
    }
	//Ϊ������Capaility
	@SubscribeEvent
    public void onAttachCapabilitiesEntity(AttachCapabilitiesEvent<Entity> event)
    {
		Entity entity = event.getObject();
        if (entity instanceof EntityPlayer)
        {
        	/*
        	 * ��һ��������Ҫ����һ��ResourceLocation����ΪICapabilitySerializable��Ψһ��ʶ������������ʹ�õ���"tmod:player_level"��
        	 * �ڶ�������������Ҫ�����ICapabilitySerializable��
        	 */
        	ICapabilitySerializable<NBTTagCompound> provider = new CapabilityPlayerLevel.ProviderPlayer();
            event.addCapability(new ResourceLocation(tmod.MODID + ":" + "tmod_level"), provider);
        }
    }
	//��ҽ�������ʱ��������
	@SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event)
    {
        if (!event.getWorld().isRemote && event.getEntity() instanceof EntityPlayer)
        {
            EntityPlayerMP player = (EntityPlayerMP) event.getEntity();
            if (player.hasCapability(CapabilityLoader.tmodLv, null))
            {
                MessagePlayerLevel message = new MessagePlayerLevel();

                IPlayerLevel tmodLv = player.getCapability(CapabilityLoader.tmodLv, null);
                IStorage<IPlayerLevel> storage = CapabilityLoader.tmodLv.getStorage();

                message.nbt = new NBTTagCompound();
                message.nbt.setTag("tmod_level", storage.writeNBT(CapabilityLoader.tmodLv, tmodLv, null));

                NetworkLoader.instance.sendTo(message, player);
            }
        }
    }
	//��Ҵ�������ʱ��������
	@SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event)
    {
        Capability<IPlayerLevel> capability = CapabilityLoader.tmodLv;
        IStorage<IPlayerLevel> storage = capability.getStorage();

        if (event.getOriginal().hasCapability(capability, null) && event.getEntityPlayer().hasCapability(capability, null))
        {
            NBTBase nbt = storage.writeNBT(capability, event.getOriginal().getCapability(capability, null), null);
            storage.readNBT(capability, event.getEntityPlayer().getCapability(capability, null), null, nbt);
        }
    }
	//��Ҽ�����Ʒʵ��ʱ��������Ʒ�ȼ�
	@SubscribeEvent
	public void pickupItem(EntityItemPickupEvent event) {
		EntityItem entityItem = event.getItem();
		ItemStack stack = entityItem.getItem();
		if (stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemArmor) {
			EntityPlayer player = event.getEntityPlayer();
			if(player.hasCapability(CapabilityLoader.tmodLv, null) && stack.getTagCompound() == null) {
				IPlayerLevel playerCap = player.getCapability(CapabilityLoader.tmodLv, null);
				Integer playerLv = playerCap.getPlayerLevel();
				Random random = new Random();
				int lv = random.nextInt(playerLv.intValue() + 5);
				lv = lv < ConfigLoader.level ? lv : ConfigLoader.level;
				//���õȼ���Ϣnbt����,�ȼ�Ϊ��ҵȼ�����
				stack.setTagInfo("level", new NBTTagInt(lv));
//				setItemAttr(lv, stack.getItem());
			}
		}

	}
	//��ҹ����ȼ������˺�����
	@SubscribeEvent
	public void attckEntity(AttackEntityEvent event) {
		EntityPlayer player = event.getEntityPlayer();
		Entity target = event.getTarget();
		if(target instanceof EntityLiving) {
			EntityLiving living = (EntityLiving) target; //����ҹ���ʵ��
			ItemStack stack = player.getHeldItemMainhand();
			//���ʹ�������еȼ��ͱ�����ʵ���еȼ�
			if(stack.getTagCompound() != null && living.getEntityAttribute(LIVING_LEVEL).getAttribute() != null) {
				Float amount = 0f;
				if(stack.getItem() instanceof ItemSword) {
					ItemSword sword = (ItemSword) stack.getItem();
					Float oldDamage = sword.getAttackDamage();
					Integer lv = stack.getTagCompound().getInteger("level");
					amount = oldDamage + lv;
				}
				if(!player.world.isRemote && living.getHealth() < amount) {
					if(player.hasCapability(CapabilityLoader.tmodLv, null)) { //��־����ȡ
						Double mobLv =  living.getEntityAttribute(LIVING_LEVEL).getBaseValue();
						//��ȡ�������ϵͳ
						IPlayerLevel playerCap = player.getCapability(CapabilityLoader.tmodLv, null);
						Integer exp = playerCap.getPlayerExp();//��ȡ����ֵ
						playerCap.setPlayerExp(exp + mobLv.intValue());//���þ���ֵ
						playerCap.setPlayerLevel(setPlayerLv(player)); //���õȼ�
						//ͬ���������
						MessagePlayerLevel msg = new MessagePlayerLevel();
						msg.nbt = new NBTTagCompound();
						msg.nbt.setLong("player_exp", playerCap.getPlayerExp());
						msg.nbt.setLong("player_level", playerCap.getPlayerLevel());
						if( player instanceof EntityPlayerMP) {
							NetworkLoader.instance.sendTo(msg, (EntityPlayerMP) player);
						}
					}
				}
				target.attackEntityFrom(DamageSource.causePlayerDamage(player), amount);
			}
			//op��ֱ��ɱ��Kiana
			if(stack.getItem().equals(itemLoader.op_sword) && living instanceof EntityKiana) {
				living.setHealth(0.1f);
			}
		}
	}
	//���﹥����Ҽ������
	@SubscribeEvent
	public void attckPlayer(LivingDamageEvent event) {
		Entity entity = event.getEntity();
		if(entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			//��ȡʵ��װ��
			ItemStack StackChest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST) == null ? ItemStack.EMPTY:player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
    		ItemStack StackFeet = player.getItemStackFromSlot(EntityEquipmentSlot.FEET) == null ? ItemStack.EMPTY:player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
    		ItemStack StackHead = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD) == null ? ItemStack.EMPTY:player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
    		ItemStack StackLegs = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS) == null ? ItemStack.EMPTY:player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
    		//��ȡ����tag
    		NBTTagCompound tagNull = new NBTTagCompound();
    		tagNull.setInteger("level", 0);
    		NBTTagCompound tagCheat = StackChest.getTagCompound() == null ? tagNull:StackChest.getTagCompound();
    		NBTTagCompound tagFeet = StackFeet.getTagCompound() == null ? tagNull:StackFeet.getTagCompound();
    		NBTTagCompound tagHead = StackHead.getTagCompound() == null ? tagNull:StackHead.getTagCompound();
    		NBTTagCompound tagLegs = StackLegs.getTagCompound() == null ? tagNull:StackLegs.getTagCompound();
    		//��ȡװ���ܵȼ�
    		Integer chestLv = tagCheat.getInteger("level");// == 0 ? 0:StackChest.getTagCompound().getInteger("level");
    		Integer feetLv = tagFeet.getInteger("level");
    		Integer headLv = tagHead.getInteger("level");
    		Integer legsLv = tagLegs.getInteger("level");
    		Integer allLv = chestLv + feetLv + headLv + legsLv;
    		//���˼���
    		float amount = event.getAmount();
    		amount = (float) (amount - (allLv / 4.0)) < 0 ? 0 : (float) (amount - (allLv / 4.0));
    		event.setAmount(amount);
		}
	}
	//ѱ������
	@SubscribeEvent
	public void animalTame(AnimalTameEvent event) {
		EntityAnimal animal = event.getAnimal();
		if(animal.getName().equals("��") && !event.getAnimal().world.isRemote) {
			Double lv = animal.getEntityAttribute(LIVING_LEVEL).getBaseValue();
			setMobAttr(animal, lv);
		}
	}
	//��һ�ɱ�ȼ�����,������ڵȼ�С�;�����Ƭ
	@SideOnly(value = Side.CLIENT)
	@SubscribeEvent
    public void playerKillMob(LivingDeathEvent event) {
		Entity entity = event.getEntity();
		DamageSource source = event.getSource();
		if(entity instanceof EntityLiving && !event.getEntity().getEntityWorld().isRemote) {
			EntityLiving living = (EntityLiving) entity;
			setMobDrop(living, source);
		}
	}
	//Ϊԭ��ʵ���������
	@SubscribeEvent
	public void entityInit(EntityEvent.EntityConstructing event) {
	    final Entity e = event.getEntity();
	    if (e instanceof EntityLivingBase) {
	        final EntityLivingBase living = (EntityLivingBase) e;
	        living.getAttributeMap().registerAttribute(LIVING_LEVEL);
	    }
//	    if(e instanceof EntityPlayer) {
//	    	EntityPlayer player = (EntityPlayer) e;
//	    	player.getAttributeMap().registerAttribute(PLAYER_EXP);
//	    }
	}
	//������Ʒ��Ϣ
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onTooltip(ItemTooltipEvent event)
	{
		ArrayList<String> tooltip = (ArrayList<String>) event.getToolTip();
		ItemStack stack = event.getItemStack();
		
		if (stack != null && stack.getTagCompound() != null)
		{
			if (stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemArmor)
			{
				tooltip.add(I18n.format("tmod.item.level") + stack.getTagCompound().getInteger("level")); //������Ʒ��Ϣ��ʾ
			}
		}
	}
	//�ж���ҵȼ�
	public static int setPlayerLv(EntityPlayer player) {
		IPlayerLevel playerCap = player.getCapability(CapabilityLoader.tmodLv, null);
		Integer exp = playerCap.getPlayerExp();
//		Integer lv = playerCap.getPlayerLevel();
		int playerLv = 1;
		if(exp > 25550) {
			int exp0 = exp.intValue() - 25550;
			playerLv = (exp0 / 2560) + 90;
		}else if(exp > 12750) {
			int exp0 = exp.intValue() - 12750;
			playerLv = (exp0 / 1280) + 80;
		}else if(exp > 6350) {
			int exp0 = exp.intValue() - 6350;
			playerLv = (exp0 / 640) + 70;
		}else if(exp > 3150) {
			int exp0 = exp.intValue() - 3150;
			playerLv = (exp0 / 320) + 60;
		}else if(exp > 1550) {
			int exp0 = exp.intValue() - 1550;
			playerLv = (exp0 / 160) + 50;
		}else if(exp > 750) {
			int exp0 = exp.intValue() - 750;
			playerLv = (exp0 / 80) + 40;
		}else if(exp > 350) {
			int exp0 = exp.intValue() - 350;
			playerLv = (exp0 / 40) + 30;
		}else if(exp > 150) {
			int exp0 = exp.intValue() - 150;
			playerLv = (exp0 / 20) + 20;
		}else if(exp > 50) {
			int exp0 = exp.intValue() - 50;
			playerLv = (exp0 / 10) + 10;
		}else {
			int exp0 = exp.intValue();
			playerLv = exp0 / 5;
		}
		return playerLv;
	}
	//��ȡ����ȼ���������������������㣩
	private double getMobLv(BlockPos pos) 
	{
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.player;
		if(player == null) {
			return 1;
		}
		int x0 = pos.getX();
		int z0 = pos.getZ();
		//�������
		int i = (int) Math.ceil((Math.sqrt(Math.pow(Math.abs(x0), 2) + Math.pow(Math.abs(z0), 2))));
		int lv = (int) Math.ceil((i / 100));
		if(lv == 0) { //�ȼ�0-100
			lv = 1;
		}
//		player.sendMessage(new TextComponentTranslation("level:" + lv + " pos:" + pos.toString()));
		return (double) (lv < ConfigLoader.level ? lv : ConfigLoader.level);
	}
	//���ù�������
	private void setMobAttr(EntityLiving entityLiving, Double lv) {
		float health = (float) (entityLiving.getHealth() + (lv - 1) * 5);
		if(entityLiving instanceof EntityMob || entityLiving instanceof EntityWolf) { // ʵ���й��������Բ����ù�����
			Double damage = entityLiving.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getBaseValue();
			entityLiving.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(damage + lv * 0.5D);
		}
		double armor = entityLiving.getEntityAttribute(SharedMonsterAttributes.ARMOR).getBaseValue();
		entityLiving.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(armor + lv * 1D);
		entityLiving.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(health);
		entityLiving.getEntityAttribute(LIVING_LEVEL).setBaseValue((double)lv);
		entityLiving.setCustomNameTag("");//���ʵ������
		entityLiving.setCustomNameTag("Lv:" + lv.intValue() + " " + entityLiving.getName()); //����ʵ������
		entityLiving.setHealth(health);
	}
	//�������
	private void setMobDrop(EntityLiving entityLiving, DamageSource source) {
		if(source.damageType == "player" || source.getDamageType() == "generic") { //�˺���Դ�����
			float health = entityLiving.getMaxHealth();
			int lv =  (int) Math.floor(((health - 20) / 5)); //���ݹ���Ѫ������ȼ�
			if(lv == 0) {
				lv = 1;
			}
			ItemStack stack = new ItemStack(itemLoader.exp_small, lv);
			World world = entityLiving.world;
			EntityItem entityItem = new EntityItem(world, entityLiving.posX, entityLiving.posY, entityLiving.posZ, stack);
			if(!world.isRemote) {
				world.spawnEntity(entityItem);
			}
		}
	}
	//���������Ϳ�������
//	private void setItemAttr(int lv, Item item) {
//		if(item instanceof ItemSword) {
//			ItemSword sword = (ItemSword) item;
//			sword.getAttackDamage();
//		}
//	}
}
