package com.yuo.Tmod.Common.Blocks;

import com.yuo.Tmod.Tab.TmodGroup;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class XrayBlock extends BlockOre {
    public XrayBlock(String name, int level , float hardness) {
        super();
        this.setUnlocalizedName(name);
        this.setHarvestLevel("pickaxe", level);
        this.setHardness(hardness);
        this.setResistance(hardness + 10);
        this.setCreativeTab(TmodGroup.OTHER_TAB);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

}
