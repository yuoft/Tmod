package com.yuoMod.Tmod.Gui;

import com.yuoMod.Tmod.TileEntity.MyTileEntity;
import com.yuoMod.Tmod.TileEntity.NineTileEntity;
import com.yuoMod.Tmod.Tmod;
import com.yuoMod.Tmod.Common.Items.InventoryStorageRingBag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class GuiLoader implements IGuiHandler {
    public static final int GUI_DEMO = 1;
    public static final int NINE_GUI = 2;
    public static final int SIX_GUI = 3;
    public static final int THREE_GUI = 4;
    public static final int CHEST_GUI = 5;

    public GuiLoader() {
        //第一个参数传入一个Mod的实例，第二个参数传入这个IGuiHandler。
        NetworkRegistry.INSTANCE.registerGuiHandler(Tmod.instance, this);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GUI_DEMO:
                return new ContainerDemo(player.inventory, (MyTileEntity) world.getTileEntity(new BlockPos(x, y, z)));
            case NINE_GUI:
                return new NineContainer(player, new InventoryStorageRingBag(player.getHeldItemMainhand(), 81));
            case SIX_GUI:
                return new SixContainer(player, new InventoryStorageRingBag(player.getHeldItemMainhand(), 54));
            case THREE_GUI:
                return new ThreeContainer(player, new InventoryStorageRingBag(player.getHeldItemMainhand(), 27));
            case CHEST_GUI:
                return new ChestContainer(player, (NineTileEntity) world.getTileEntity(new BlockPos(x, y, z)));
            default:
                return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GUI_DEMO:
                return new GuiContainerDemo(player.inventory, (MyTileEntity) world.getTileEntity(new BlockPos(x, y, z)));
            case NINE_GUI:
                return new NineGuiContainer(new NineContainer(player, new InventoryStorageRingBag(player.getHeldItemMainhand(), 81)));
            case SIX_GUI:
                return new SixGuiContainer(new SixContainer(player, new InventoryStorageRingBag(player.getHeldItemMainhand(), 54)));
            case THREE_GUI:
                return new ThreeGuiContainer(new ThreeContainer(player, new InventoryStorageRingBag(player.getHeldItemMainhand(), 27)));
            case CHEST_GUI:
                return new ChestGuiContainer(new ChestContainer(player, (NineTileEntity) world.getTileEntity(new BlockPos(x, y, z))));
            default:
                return null;
        }
    }

}
