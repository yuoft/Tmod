package com.yuoMod.Tmod.Capability;

import java.lang.reflect.Field;

public class Test {
	
	public static void main(String[] args) {
		Test.getMod();
	}
	static void getMod() {
			Class<?> forName = null;
			try {
				forName = Class.forName("net.minecraft.init.Blocks");
			} catch (ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} //反射获取类
			Field[] declaredFields = forName.getDeclaredFields(); // 获取类的所有属性
			for(Field field : declaredFields) {
				System.out.println("属性:"+ field);
			}
	}
}
