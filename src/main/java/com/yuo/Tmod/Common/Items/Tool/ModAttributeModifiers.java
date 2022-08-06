package com.yuo.Tmod.Common.Items.Tool;

import com.yuo.Tmod.Tmod;
import net.minecraft.entity.ai.attributes.AttributeModifier;

import java.util.UUID;

public class ModAttributeModifiers {
    //速度
    public static AttributeModifier SPEED_ATTR_ADD = new AttributeModifier(UUID.fromString("e18b4711-199d-b5b0-3bc3-ae28032ae0db"),
            Tmod.MOD_ID + ":speed_add", 0.1, 1);
    public static AttributeModifier SPEED_ATTR_REMOVE = new AttributeModifier(UUID.fromString("6bc557a7-6039-dc36-e6a5-03d9d687e4c9"),
            Tmod.MOD_ID + ":speed_remove", -0.1, 1);
    //最大生命值
    public static AttributeModifier HEALTH_ATTR_ADD = new AttributeModifier(UUID.fromString("e18b4711-199d-b5b0-3bc3-ae28032ae0db"),
            Tmod.MOD_ID + ":health_add", 2, 0);
    public static AttributeModifier HEALTH_ATTR_REMOVE = new AttributeModifier(UUID.fromString("f0fb48d0-a465-6a08-a5a2-5e6f69ded629"),
            Tmod.MOD_ID + ":health_remove", -2, 0);
    //击退抗性
    public static AttributeModifier KNOCK_ATTR_ADD = new AttributeModifier(UUID.fromString("3227a3ab-c0e7-9516-8d44-3f20e0e3a02f"),
            Tmod.MOD_ID + ":health_add", 0.01, 0);
    public static AttributeModifier KNOCK_ATTR_REMOVE = new AttributeModifier(UUID.fromString("34035c77-02bd-701a-655d-754bc6c6af60"),
            Tmod.MOD_ID + ":health_remove", -0.01, 0);

}
