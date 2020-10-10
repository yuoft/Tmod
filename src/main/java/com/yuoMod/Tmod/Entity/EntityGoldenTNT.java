package com.yuoMod.Tmod.Entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

//Ͷ����
public class EntityGoldenTNT extends EntityThrowable
{
	public EntityGoldenTNT(World worldIn) {
		super(worldIn);
	}

	public EntityGoldenTNT(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
//		this.shoot((Entity)throwerIn, 1.0f, 1.0f, 1.0f, 3.0f, 0.1f);
//		this.shoot(posX, posY, posZ, 3.0f, 0.1f);
	}

	public EntityGoldenTNT(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}
	//��ػ���ײ�󴥷��¼�
	@Override
	protected void onImpact(RayTraceResult result) {
		if(!this.world.isRemote)
		{
			// createExplosion������ը��entityIn ��ըԴ������Ϊ null�������뱬ը�˺��ж���x,y,z double�����ꣻstrength
			// ��ը����/��Χ����ͨ������Ϊ3.0��TNTΪ4.0��isSmoking �Ƿ��ƻ����κ�����˺�
			this.world.createExplosion(this.thrower, this.posX, this.posY, this.posZ, 10.0f, true);
			// ɾ��ʵ��
			this.setDead();
		}
	}
}
