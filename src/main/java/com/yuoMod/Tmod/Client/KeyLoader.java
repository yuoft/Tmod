package com.yuoMod.Tmod.Client;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
/*
 * ÈÈ¼ü°ó¶¨
 */
public class KeyLoader 
{
	public static KeyBinding key;

    public KeyLoader()
    {
        KeyLoader.key = new KeyBinding("key.tmod.key", Keyboard.KEY_Z, "key.categories.tmod");

        ClientRegistry.registerKeyBinding(KeyLoader.key);
    }
}
