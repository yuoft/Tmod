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
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} //�����ȡ��
			Field[] declaredFields = forName.getDeclaredFields(); // ��ȡ�����������
			for(Field field : declaredFields) {
				System.out.println("����:"+ field);
			}
	}
}
