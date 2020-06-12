package com.yuoMod.Tmod.Common.Crop;

import javax.annotation.Nonnull;

import com.yuoMod.Tmod.Common.Items.itemLoader;
import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.SoundType;
import net.minecraft.item.Item;

public class emerald_crop extends BlockCrops
{
	public emerald_crop(String name) 
	{
		// TODO �Զ����ɵĹ��캯�����
		this.setUnlocalizedName(name);//�̱�ʯ����
		this.setDefaultState(null);
		this.setTickRandomly(true);
		this.setCreativeTab(CreativeTabsLoader.TMOD);
		this.setHardness(0.0F);
        this.setSoundType(SoundType.PLANT);
        this.disableStats();
	}
	// SRG func_149866_i
    @Nonnull
    @Override
    protected Item getSeed() {
        return itemLoader.emerald_crop_seeds;
    }

    // SRG func_149865_P
    @Nonnull
    @Override
    protected Item getCrop() {
        return itemLoader.emerald_powder;
    }
}
