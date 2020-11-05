package com.yuoMod.Tmod.Common;

import com.yuoMod.Tmod.Common.Blocks.blockLoader;
import com.yuoMod.Tmod.Common.Crafting.CraftingLoader;
import com.yuoMod.Tmod.Common.Items.itemLoader;
import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;
import com.yuoMod.Tmod.Enchantment.enchantmentLoader;
import com.yuoMod.Tmod.Entity.EntityLoader;
import com.yuoMod.Tmod.Fluid.fluidLoader;
import com.yuoMod.Tmod.Gui.guiLoader;
import com.yuoMod.Tmod.Potion.potionLoader;
import com.yuoMod.Tmod.TileEntity.TileEntityLoader;
import com.yuoMod.Tmod.WorldCreate.WorldOreLoader;
import com.yuoMod.Tmod.WorldCreate.WorldTreeLoader;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class common
{
    public void preInit(FMLPreInitializationEvent event)
    {
//    	new ConfigLoader();//�����ļ�
//    	new ConfigGuiFactory();
    	new CreativeTabsLoader(event);//������Ʒ��ע��
    	new fluidLoader(event);//����ע��
    	itemLoader.init(event);//ע����Ʒ
    	new blockLoader(event);//����ע��
    	new potionLoader(event);//ҩˮ״̬ע��
    	new EntityLoader();//ʵ��ע��
    	new TileEntityLoader(event);//TileEntityע��
    }

    public void init(FMLInitializationEvent event)
    {
    	new CraftingLoader();//��¯�䷽ע��
    	new enchantmentLoader();//��ħע��
    	new eventLoader();//�¼�ע��
    	new WorldOreLoader();//�����������������ע��
    	new WorldTreeLoader();
    	new guiLoader();//guiע��
    }
    public void postInit(FMLPostInitializationEvent event)
    {
    	
    }
}
