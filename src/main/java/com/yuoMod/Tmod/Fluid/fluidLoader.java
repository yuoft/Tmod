package com.yuoMod.Tmod.Fluid;

import com.yuoMod.Tmod.tmod;
import com.yuoMod.Tmod.Common.Blocks.blockLoader;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class fluidLoader
{
	public static Fluid emerald_fluid = new emerald_fluid("emerald_fluid");

    public fluidLoader(FMLPreInitializationEvent event)
    {
        if (FluidRegistry.isFluidRegistered(emerald_fluid))
        {
            event.getModLog().info("Found fluid {}, the registration is canceled. ", emerald_fluid.getName());
            emerald_fluid = FluidRegistry.getFluid(emerald_fluid.getName());
        }
        else
        {
            FluidRegistry.registerFluid(emerald_fluid);
            FluidRegistry.addBucketForFluid(emerald_fluid);
        }
    }
    //注册流体方块材质
    @SideOnly(Side.CLIENT)
    public static void registerRenders()
    {
        registerFluidRender((BlockFluidBase) blockLoader.block_Emerald_Fluid, "emerald_fluid");
    }

    @SideOnly(Side.CLIENT)
    public static void registerFluidRender(BlockFluidBase blockFluid, String blockStateName)
    {
        final String location = tmod.MODID + ":" + blockStateName;
        final Item itemFluid = Item.getItemFromBlock(blockFluid);
        ModelLoader.setCustomMeshDefinition(itemFluid, new ItemMeshDefinition()
        {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack)
            {
                return new ModelResourceLocation(location, "fluid");
            }
        });
        ModelLoader.setCustomStateMapper(blockFluid, new StateMapperBase()
        {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state)
            {
                return new ModelResourceLocation(location, "fluid");
            }
        });
//        ModelLoader.setCustomStateMapper(emerald_fluid.getBlock(), new StateMapperBase() {
//            @Override
//            protected ModelResourceLocation getModelResourceLocation(@Nonnull IBlockState state) {
//                return new ModelResourceLocation(new ResourceLocation(tmod.MODID, "emerald_fluid"), "fluid");
//            }
//        });
    } 
//    @SubscribeEvent
//    public static void regFluidSpirit(TextureStitchEvent.Pre event) {
//        TextureMap textureMap = event.getMap();
//        textureMap.registerSprite(emerald_fluid.getFlowing());
//        textureMap.registerSprite(emerald_fluid.getStill());
//    }
}
