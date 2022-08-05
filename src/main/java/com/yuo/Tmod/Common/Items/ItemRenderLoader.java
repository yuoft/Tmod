package com.yuo.Tmod.Common.Items;

import com.yuo.Tmod.Common.Blocks.BlockLoader;
import com.yuo.Tmod.Fluid.FluidLoader;

public class ItemRenderLoader {
    public ItemRenderLoader() {
        ItemLoader.registerRenders();
        BlockLoader.registerRenders();
        FluidLoader.registerRenders();
    }
}
