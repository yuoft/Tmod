package com.yuoMod.Tmod.Client;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextComponentString;
import org.lwjgl.input.Keyboard;

import com.yuoMod.Tmod.Common.Blocks.Elevator;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*
 * 热键绑定
 */
public class KeyLoader {
    public static final KeyBinding key_UP = new KeyBinding("key.tmod.key_UP", Keyboard.KEY_UP, "key.categories.tmod");
    public static final KeyBinding key_DOWN = new KeyBinding("key.tmod.key_DOWN", Keyboard.KEY_DOWN, "key.categories.tmod");

    public KeyLoader() {
        ClientRegistry.registerKeyBinding(KeyLoader.key_UP);
        ClientRegistry.registerKeyBinding(KeyLoader.key_DOWN);
        MinecraftForge.EVENT_BUS.register(this);
    }

    //快捷键
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.player;
//  		World world = mc.world;
        if (player != null) {
            if (KeyLoader.key_UP.isPressed()) {
//  				player.sendMessage(new TextComponentTranslation("上键"));
//  				elevator(player, world, true);
            }
            if (KeyLoader.key_DOWN.isPressed()) {
//  				player.sendMessage(new TextComponentTranslation("下键"));
            }
        } else Minecraft.getMinecraft().player.sendMessage(new TextComponentString("error"));
    }
}
