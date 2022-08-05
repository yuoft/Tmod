package com.yuo.Tmod.Fluid;

import com.yuo.Tmod.Tmod;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

import java.awt.*;

public class EmeraldFluid extends Fluid {
    //静止材质
    public static final ResourceLocation still = new ResourceLocation(Tmod.MOD_ID + ":" + "fluid/emerald_fluid");
    //流动材质
    public static final ResourceLocation flowing = new ResourceLocation(Tmod.MOD_ID + ":" + "fluid/emerald_fluid_flow");

    public EmeraldFluid(String name) {
    	/*
    	   第一个参数表示的是这个流体的名称，使用这个流体的现实生活中的名称就可以了
                  第二个参数表示的是这个流体静止的时候使用的贴图位置，我们通过实例化一个ResourceLocation类来完成
                  第三个参数表示的是这个流体流动的时候使用的贴图位置，和第二个参数同理
          setDensity方法用于设置这个流体的密度，单位为千克每立方米，默认为水的密度，也就是1000
          setViscosity方法用于设置这个流体的粘度，单位为千分之一平方米每秒，使用运动粘度，默认为水的粘度，也就是1000
          setLuminosity方法用于设置这个流体的亮度，也就是在Minecraft中的亮度，默认为水的亮度，也就是0
          setTemperature方法用于设置这个流体的温度，使用热力学温标，也就是开尔文，默认为室温，也就是300
          setGaseous方法用于标注这个流体是否为气体，默认不是
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
