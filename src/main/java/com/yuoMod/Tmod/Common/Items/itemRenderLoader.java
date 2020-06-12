package com.yuoMod.Tmod.Common.Items;

import com.yuoMod.Tmod.Common.Blocks.blockLoader;
import com.yuoMod.Tmod.Fluid.fluidLoader;

public class itemRenderLoader 
{
	public itemRenderLoader()
	{
		itemLoader.registerRenders();
		blockLoader.registerRenders();
		fluidLoader.registerRenders();
	}
}
