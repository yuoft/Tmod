package com.yuoMod.Tmod.Common.Blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.yuoMod.Tmod.Common.Items.ItemLoader;
import com.yuoMod.Tmod.Tab.TmodGroup;
import com.yuoMod.Tmod.Entity.EntityKiana;
import com.yuoMod.Tmod.Entity.EntityNewSteve;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//召唤boss
public class BossBlock extends Block {
    public static final AxisAlignedBB AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

    public BossBlock(String name) {
        super(Material.ROCK);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TMOD);
        this.setHardness(10);
        this.setHarvestLevel("pickaxe", 3);
        this.setResistance(60);
        this.setSoundType(SoundType.STONE);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            ItemStack heldItem = playerIn.getHeldItem(hand);
            Block block = state.getBlock();
            if (block == BlockLoader.bossBlock) {
                EntityNewSteve steve = new EntityNewSteve(worldIn);
                steve.setPosition(pos.getX(), pos.getY(), pos.getZ());
                if (!heldItem.isEmpty() && heldItem.getItem() == Items.DIAMOND) {
                    heldItem.shrink(1);
                    steve.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ItemLoader.dragonSword));
                    steve.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(Items.TOTEM_OF_UNDYING));
                    steve.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ItemLoader.dragonHead));
                    steve.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ItemLoader.dragonChest));
                    steve.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(ItemLoader.dragonFeet));
                    steve.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(ItemLoader.dragonLegs));
                    steve.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(250.0D);
                    steve.setHealth(250.0f);
                } else if (!heldItem.isEmpty() && heldItem.getItem() == Items.EMERALD) {
                    heldItem.shrink(1);
                    steve.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ItemLoader.spaceSword));
                    steve.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(Items.TOTEM_OF_UNDYING));
                    steve.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ItemLoader.spaceHelmet));
                    steve.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ItemLoader.spaceChest));
                    steve.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(ItemLoader.spaceBoots));
                    steve.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(ItemLoader.spaceLeggings));
                    steve.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(300.0D);
                    steve.setHealth(300.0f);
                } else {
                    steve.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ItemLoader.emeraldSword));
                    steve.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(Items.TOTEM_OF_UNDYING));
                    steve.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ItemLoader.emeraldHelmet));
                    steve.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ItemLoader.emeraldBreastplate));
                    steve.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(ItemLoader.emeraldShoes));
                    steve.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(ItemLoader.emeraldLeggings));
                    steve.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0D);
                    steve.setHealth(200.0f);
                }
                steve.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
                steve.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.3D);
                steve.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D);
                worldIn.spawnEntity(steve);
                playerIn.sendMessage(new TextComponentTranslation("tmod.text.boss_spawn"));
            } else if (block == BlockLoader.bossBlock1) {
                EntityKiana kiana = new EntityKiana(worldIn);
                kiana.setPosition(pos.getX(), pos.getY(), pos.getZ());
                float health = kiana.getMaxHealth();
                if (!heldItem.isEmpty() && heldItem.getItem() == ItemLoader.dragonCrystal) {
                    heldItem.shrink(1);
                    health += 100f;
                    kiana.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ItemLoader.emeraldSword));
                    kiana.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(Items.TOTEM_OF_UNDYING));
                    kiana.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ItemLoader.emeraldHelmet));
                    kiana.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ItemLoader.emeraldBreastplate));
                    kiana.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(ItemLoader.emeraldShoes));
                    kiana.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(ItemLoader.emeraldLeggings));
                } else if (!heldItem.isEmpty() && heldItem.getItem() == ItemLoader.spaceIngot) {
                    heldItem.shrink(1);
                    health += 300f;
                    kiana.setHealth(health);
                    kiana.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ItemLoader.dragonSword));
                    kiana.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(Items.TOTEM_OF_UNDYING));
                    kiana.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ItemLoader.dragonHead));
                    kiana.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ItemLoader.dragonChest));
                    kiana.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(ItemLoader.dragonFeet));
                    kiana.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(ItemLoader.dragonLegs));
                    kiana.addPotionEffect(new PotionEffect(Potion.getPotionById(10), 100000, 3));
                } else if (!heldItem.isEmpty() && heldItem.getItem() == Items.NETHER_STAR) {
                    heldItem.shrink(1);
                    health += 500f;
                    kiana.setHealth(health);
                    kiana.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ItemLoader.spaceSword));
                    kiana.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(Items.TOTEM_OF_UNDYING));
                    kiana.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ItemLoader.spaceHelmet));
                    kiana.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ItemLoader.spaceChest));
                    kiana.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(ItemLoader.spaceBoots));
                    kiana.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(ItemLoader.spaceLeggings));
                    kiana.addPotionEffect(new PotionEffect(Potion.getPotionById(10), 100000, 4));
                    kiana.entityDropItem(new ItemStack(Items.NETHER_STAR), 0);
                } else {
                    kiana.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(Items.TOTEM_OF_UNDYING));
                }
                kiana.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(health);
                kiana.setHealth(health);
                worldIn.spawnEntity(kiana);
                EntityLightningBolt lightningBolt = new EntityLightningBolt(worldIn, pos.getX(), pos.getY(), pos.getZ(), false);
                worldIn.spawnEntity(lightningBolt);
                playerIn.sendMessage(new TextComponentTranslation("tmod.text.boss_strong"));
            }
            worldIn.setBlockToAir(pos);
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (stack.getItem() == ItemLoader.bossBlock) {
            tooltip.add(I18n.format("tmod.block.boss_block1", ""));
            tooltip.add(I18n.format("tmod.block.boss1_block1", ""));
            tooltip.add(I18n.format("tmod.block.boss2_block1", ""));
        }
        if (stack.getItem() == ItemLoader.bossBlock1) {
            tooltip.add(I18n.format("tmod.block.boss_block2", ""));
            tooltip.add(I18n.format("tmod.block.boss1_block2", ""));
            tooltip.add(I18n.format("tmod.block.boss2_block2", ""));
        }
    }

    //是不透明方块
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}
