package com.yuoMod.Tmod.Entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Items;
import net.minecraft.world.World;

public class EntityGoldenChicken extends EntityChicken{
	public EntityGoldenChicken(World worldIn) {
		super(worldIn);
		this.setSize(1.0F, 1.0F);
	}
	//实体更新
	@Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
    }
	//掉落物(是否被玩家杀死，抢夺等级)
	@Override
    protected void dropFewItems(boolean arg1, int arg2)
    {
        if (this.rand.nextInt(3) ==0)
        {
            this.dropItem(Items.GOLD_INGOT, 1+arg2);
        }
        super.dropFewItems(arg1, arg2);
    }
	//实体生成属性
	@Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
    }
}
