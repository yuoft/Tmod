package com.yuoMod.Tmod.Fluid;

import com.yuoMod.Tmod.Tmod;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

import java.awt.*;

public class EmeraldFluid extends Fluid {
    //��ֹ����
    public static final ResourceLocation still = new ResourceLocation(Tmod.MOD_ID + ":" + "fluid/emerald_fluid");
    //��������
    public static final ResourceLocation flowing = new ResourceLocation(Tmod.MOD_ID + ":" + "fluid/emerald_fluid_flow");

    public EmeraldFluid(String name) {
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
        super(name, EmeraldFluid.still, EmeraldFluid.flowing);
        this.setUnlocalizedName(name);
        this.setDensity(3000);
        this.setViscosity(2000);
        this.setLuminosity(10);
        this.setTemperature(3300);
        this.setColor(Color.GREEN);
    }
}
