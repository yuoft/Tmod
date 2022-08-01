package com.yuoMod.Tmod.Entity.Model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports


public class ModelEmeraldBoss extends ModelBase {
    private final ModelRenderer EmeraldBoss;
    private final ModelRenderer body;//身体
    private final ModelRenderer hand_left;//左手
    private final ModelRenderer hand_right;//右手
    private final ModelRenderer head;//头
    private final ModelRenderer hair_right_r1;//头发右
    private final ModelRenderer hair_left_r1;//头发左
    private final ModelRenderer leg_left;//左腿
    private final ModelRenderer leg_right;//右腿

    public ModelEmeraldBoss() {
        textureWidth = 64;
        textureHeight = 64;

        EmeraldBoss = new ModelRenderer(this);
        EmeraldBoss.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(EmeraldBoss, 0.0F, 3.1416F, 0.0F);


        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 0.0F, 0.0F);
        EmeraldBoss.addChild(body);
        body.cubeList.add(new ModelBox(body, 0, 16, -4.0F, -20.0F, -2.0F, 8, 10, 4, 0.0F, false));

        hand_left = new ModelRenderer(this);
        hand_left.setRotationPoint(0.0F, 0.0F, 0.0F);
        EmeraldBoss.addChild(hand_left);
        hand_left.cubeList.add(new ModelBox(hand_left, 0, 30, -6.0F, -20.0F, -1.0F, 2, 12, 2, 0.0F, false));

        hand_right = new ModelRenderer(this);
        hand_right.setRotationPoint(0.0F, 0.0F, 0.0F);
        EmeraldBoss.addChild(hand_right);
        hand_right.cubeList.add(new ModelBox(hand_right, 24, 24, 4.0F, -20.0F, -1.0F, 2, 12, 2, 0.0F, false));

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        EmeraldBoss.addChild(head);
        head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, -28.0F, -4.0F, 8, 8, 8, 0.0F, false));

        hair_right_r1 = new ModelRenderer(this);
        hair_right_r1.setRotationPoint(-2.0F, -28.0F, -4.0F);
        head.addChild(hair_right_r1);
        setRotationAngle(hair_right_r1, -0.2618F, 0.0F, 0.1745F);
        hair_right_r1.cubeList.add(new ModelBox(hair_right_r1, 42, 0, -1.0F, 0.0F, -2.0F, 2, 12, 2, 0.0F, false));

        hair_left_r1 = new ModelRenderer(this);
        hair_left_r1.setRotationPoint(2.0F, -28.0F, -4.0F);
        head.addChild(hair_left_r1);
        setRotationAngle(hair_left_r1, -0.2618F, 0.0F, -0.1745F);
        hair_left_r1.cubeList.add(new ModelBox(hair_left_r1, 34, 0, -1.0F, 0.0F, -2.0F, 2, 12, 2, 0.0F, false));

        leg_left = new ModelRenderer(this);
        leg_left.setRotationPoint(0.0F, 0.0F, 0.0F);
        EmeraldBoss.addChild(leg_left);
        leg_left.cubeList.add(new ModelBox(leg_left, 8, 30, 1.0F, -10.0F, -1.0F, 2, 8, 2, 0.0F, false));
        leg_left.cubeList.add(new ModelBox(leg_left, 24, 16, -3.0F, -2.0F, -1.0F, 2, 2, 3, 0.0F, false));

        leg_right = new ModelRenderer(this);
        leg_right.setRotationPoint(0.0F, 0.0F, 0.0F);
        EmeraldBoss.addChild(leg_right);
        leg_right.cubeList.add(new ModelBox(leg_right, 24, 0, 1.0F, -2.0F, -1.0F, 2, 2, 3, 0.0F, false));
        leg_right.cubeList.add(new ModelBox(leg_right, 16, 30, -3.0F, -10.0F, -1.0F, 2, 8, 2, 0.0F, false));
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        EmeraldBoss.render(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        copyModelAngles(this.leg_left, this.leg_left);
        copyModelAngles(this.leg_right, this.leg_right);
        copyModelAngles(this.hand_left, this.hand_left);
        copyModelAngles(this.hand_right, this.hand_right);

    }

    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {

    }
}