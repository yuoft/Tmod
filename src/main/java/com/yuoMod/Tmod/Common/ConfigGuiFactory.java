package com.yuoMod.Tmod.Common;

import java.util.Collections;
import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;

public class ConfigGuiFactory implements IModGuiFactory {
    @Override
    public void initialize(Minecraft mc) 
    {
    	
    }

    @Override
    public boolean hasConfigGui() 
    {
        return true; 
    }

    @Override
    public GuiScreen createConfigGui(GuiScreen parent) {
        return new GuiConfig(parent, ConfigElement.from(ConfigLoader.class).getChildElements(), "tmod", false, false, "First line", "Second line");
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() 
    {
        return Collections.emptySet();
    }

}
