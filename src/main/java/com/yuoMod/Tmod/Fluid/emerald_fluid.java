package com.yuoMod.Tmod.Fluid;

import com.yuoMod.Tmod.tmod;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class emerald_fluid  extends Fluid
{
	//��ֹ����
	public static final ResourceLocation still = new ResourceLocation(tmod.MODID + ":" + "textures/fluid/emerald_fluid_still.png");
    //��������
	public static final ResourceLocation flowing = new ResourceLocation(tmod.MODID + ":" + "textures/fluid/emerald_fluid_flow.png");

    public emerald_fluid (String name)
    {
    	/*
    	   ��һ��������ʾ���������������ƣ�ʹ������������ʵ�����е����ƾͿ�����
                  �ڶ���������ʾ����������徲ֹ��ʱ��ʹ�õ���ͼλ�ã�����ͨ��ʵ����һ��ResourceLocation�������
                  ������������ʾ�����������������ʱ��ʹ�õ���ͼλ�ã��͵ڶ�������ͬ��
          setDensity���������������������ܶȣ���λΪǧ��ÿ�����ף�Ĭ��Ϊˮ���ܶȣ�Ҳ����1000
          setViscosity��������������������ճ�ȣ���λΪǧ��֮һƽ����ÿ�룬ʹ���˶�ճ�ȣ�Ĭ��Ϊˮ��ճ�ȣ�Ҳ����1000
          setLuminosity�����������������������ȣ�Ҳ������Minecraft�е����ȣ�Ĭ��Ϊˮ�����ȣ�Ҳ����0
          setTemperature���������������������¶ȣ�ʹ������ѧ�±꣬Ҳ���ǿ����ģ�Ĭ��Ϊ���£�Ҳ����300
          setGaseous�������ڱ�ע��������Ƿ�Ϊ���壬Ĭ�ϲ���
    	 */
        super(name, emerald_fluid .still, emerald_fluid .flowing);
        this.setUnlocalizedName(name);
        this.setDensity(10000);
        this.setViscosity(1500);
        this.setLuminosity(5);
        this.setTemperature(3000);
    }
}
