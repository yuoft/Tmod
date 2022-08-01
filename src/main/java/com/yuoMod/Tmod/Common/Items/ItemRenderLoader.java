package com.yuoMod.Tmod.Common.Items;

import com.yuoMod.Tmod.Common.Blocks.BlockLoader;
import com.yuoMod.Tmod.Fluid.FluidLoader;

public class ItemRenderLoader {
    public ItemRenderLoader() {
        ItemLoader.registerRenders();
        BlockLoader.registerRenders();
        FluidLoader.registerRenders();
    }
}
