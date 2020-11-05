package com.yuoMod.Tmod.Entity.render;

import com.yuoMod.Tmod.tmod;
import com.yuoMod.Tmod.Entity.EntityGoldenChicken;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
/**
 * 实体渲染
 * @author Lenovo
 *
 */
@SideOnly(Side.CLIENT)
public class RenderGoldenChicken extends RenderLiving<EntityGoldenChicken>
{
    private static final ResourceLocation GOLDEN_CHICKEN_TEXTURE = new ResourceLocation(tmod.MODID + ":" + "textures/entity/golden_chicken.png");

    /**
     * 第一个参数表示的是Minecraft的渲染管理器，这里留作参数传入就可以了，我们刚刚也注意到了，在注册的时候，这确实作为参数，
     * 把Minecraft.getMinecraft().getRenderManager()传入了这个类的构造方法。第三个参数其实表示的是阴影大小，这里用默认的0.5就可以了
     * 第二个参数表示的，就是这个实体生物的实体渲染模型了，这里我们暂时会先用鸡的渲染模型代替，在稍后我们会讲到如何去自定义这个模型，
     * 这其实也是这一部分的重点。
     * @param renderManager
     */
    public RenderGoldenChicken(RenderManager renderManager)
    {
        super(renderManager, new ModelChicken(), 0.5F);
    }
    //这里我们可以加一些我们想要的变换操作
    @Override
    protected void preRenderCallback(EntityGoldenChicken entity, float partialTickTime)
    {
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
    }
    //这个方法指定了渲染的材质
    @Override
    protected ResourceLocation getEntityTexture(EntityGoldenChicken entity)
    {
        return RenderGoldenChicken.GOLDEN_CHICKEN_TEXTURE;
    }
    //实体的坐标和转动角度,这里的partialTicks，就是tick数的小数部分
    @Override
    public void doRender(EntityGoldenChicken entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}