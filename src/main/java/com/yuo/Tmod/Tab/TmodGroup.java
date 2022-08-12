package com.yuo.Tmod.Tab;

import com.yuo.Tmod.Common.Items.ItemLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TmodGroup {
    public static CreativeTabs OTHER_TAB;
    public static CreativeTabs CROP_TAB;
    public static CreativeTabs TOOL_TAB;
    public static CreativeTabs FOOD_TAB;

    public TmodGroup() {
        OTHER_TAB = new OtherTab();//添加创造物品栏
        CROP_TAB = new CropTab();
        TOOL_TAB = new ToolTab();
        FOOD_TAB = new FoodTab();
    }

    static class OtherTab extends CreativeTabs {

        public OtherTab() {
            super("tmod_other");
        }

        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ItemLoader.emeraldIngot);
        }
    }
    static class CropTab extends CreativeTabs {

        public CropTab() {
            super("tmod_crop");
        }

        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ItemLoader.appleReeds);
        }
    }
    static class ToolTab extends CreativeTabs {

        public ToolTab() {
            super("tmod_tool");
        }

        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ItemLoader.opSword);
        }
    }
    static class FoodTab extends CreativeTabs {

        public FoodTab() {
            super("tmod_food");
        }

        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ItemLoader.badApple);
        }
    }
}