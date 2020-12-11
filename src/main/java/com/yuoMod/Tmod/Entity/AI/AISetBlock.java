package com.yuoMod.Tmod.Entity.AI;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public class AISetBlock extends EntityAIBase
{
	private EntityCreature entity;
	private int tick=0;
	
	public AISetBlock(EntityCreature entity) 
	{
		this.entity=entity;
	}
	//�Ƿ�ִ��ai����
	@Override
	public boolean shouldExecute() {
		EntityLivingBase entityLivingBase=entity.world.getNearestPlayerNotCreative(entity, 16.0D);//���ֿɹ����ķǴ������
		if(entityLivingBase == null)
		{
			return false;
		}
		else if(!entityLivingBase.isEntityAlive())
		{
			return false;
		}
		else
		{
			BlockPos entityPos=entity.getPosition();
			BlockPos playerPos=entityLivingBase.getPosition();
			int i= (int) Math.ceil(Math.sqrt((Math.pow(Math.abs(entityPos.getX()-playerPos.getX()),2) + Math.pow(Math.abs(entityPos.getZ()-playerPos.getZ()),2))));
			if(playerPos.getY() > entityPos.getY()+3 && i < 10 )
			{
				return true;//this.entity.world.getGameRules().getBoolean("mobGriefing");//ʵ��ɷ��÷���
			}
			else return false;
		}
	}
	//ai����
	@Override
	public void updateTask() {
		this.tick++;
		if(tick < 40)
		{
			return ;
		}
		EntityLivingBase entityLivingBase=this.entity.world.getNearestPlayerNotCreative(entity, 16.0D);
		BlockPos entityPos=entity.getPosition();
    	if(entityLivingBase instanceof EntityPlayer)
    	{
    		BlockPos playerPos=entityLivingBase.getPosition();
    		BlockPos entityPosUP = new BlockPos(entity.getPosition().getX(), entity.getPosition().getY()+2, entity.getPosition().getZ());
    		if(entityPos.getY() < playerPos.getY() && entity.world.isAirBlock(entityPosUP))//��ֱ��飬ֱ������Ҹ߶�һ��
    		{
    			IBlockState block=Blocks.COBBLESTONE.getDefaultState();
            	entity.setLocationAndAngles(entityPos.getX(), entityPos.getY()+1, entityPos.getZ(), entity.rotationYaw, entity.rotationPitch);
            	entity.world.setBlockState(entityPos, block);
    		}
    		else if(entityPos.getY() == playerPos.getY())//����Ҹ߶�һ��ʱ��ˮƽ���
    		{
    			IBlockState block=Blocks.COBBLESTONE.getDefaultState();
    			int mobX=entityPos.getX();
            	int mobZ=entityPos.getZ();
            	int playerX=playerPos.getX();
            	int playerZ=playerPos.getZ();
            	if(mobX < playerX && mobZ < playerZ)//�����mob���� de
            	{
            		BlockPos pos=new BlockPos(mobX, entityPos.getY()-1, mobZ+1);
        			if(entity.world.isAirBlock(pos))
        			    entity.world.setBlockState(pos, block);
            	}
            	else if(mobX < playerX && mobZ > playerZ)//���� ab
            	{
            		BlockPos pos=new BlockPos(mobX, entityPos.getY()-1, mobZ-1);
        			if(entity.world.isAirBlock(pos))
        			    entity.world.setBlockState(pos, block);
            	}
            	else if(mobX > playerX && mobZ < playerZ)//���� df
            	{
            		BlockPos pos=new BlockPos(mobX+1, entityPos.getY()-1, mobZ);
        			if(entity.world.isAirBlock(pos))
        			    entity.world.setBlockState(pos, block);
            	}
            	else if(mobX > playerX && mobZ > playerZ)//���� ac
            	{
            		BlockPos pos=new BlockPos(mobX-1, entityPos.getY()-1, mobZ);
        			if(entity.world.isAirBlock(pos))
        			    entity.world.setBlockState(pos, block);
            	}
            	else if(mobX == playerX && mobZ > playerZ)//��
            	{
            		BlockPos pos=new BlockPos(mobX, entityPos.getY()-1, mobZ-1);
        			if(entity.world.isAirBlock(pos))
        			    entity.world.setBlockState(pos, block);
            	}
            	else if(mobX == playerX && mobZ < playerZ)//��
            	{
            		BlockPos pos=new BlockPos(mobX, entityPos.getY()-1, mobZ+1);
        			if(entity.world.isAirBlock(pos))
        			    entity.world.setBlockState(pos, block);
            	}
            	else if(mobX > playerX && mobZ == playerZ)//��
            	{
            		BlockPos pos=new BlockPos(mobX-1, entityPos.getY()-1, mobZ);
        			if(entity.world.isAirBlock(pos))
        			    entity.world.setBlockState(pos, block);
            	}
            	else if(mobX < playerX && mobZ == playerZ)//��
            	{
            		BlockPos pos=new BlockPos(mobX+1, entityPos.getY()-1, mobZ);
        			if(entity.world.isAirBlock(pos))
        			    entity.world.setBlockState(pos, block);
            	}
            	else {}
    		}
    		else
    		{
    			
    		}
    		tick=0;
    	}
	}
}

