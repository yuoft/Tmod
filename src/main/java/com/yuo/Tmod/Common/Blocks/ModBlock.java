package com.yuo.Tmod.Common.Blocks;

import com.yuo.Tmod.Tab.TmodGroup;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraftforge.oredict.OreDictionary;

public class ModBlock extends Block {
    //绿宝石锭块
    public ModBlock(String name, int hardness, int level, Material material) {
        super(material);//放置音效
        this.setUnlocalizedName(name);
        this.setHardness(hardness);//硬度
        this.setHarvestLevel("pickaxe", level);//采集工具,等级
        this.setResistance(hardness * 2);//爆炸抗性
        this.setCreativeTab(TmodGroup.OTHER_TAB);
        this.setSoundType(SoundType.SNOW);//破坏音效
    }
}
