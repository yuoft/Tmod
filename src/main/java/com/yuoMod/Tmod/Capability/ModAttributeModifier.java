package com.yuoMod.Tmod.Capability;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;

import java.util.UUID;

//属性修饰器
public class ModAttributeModifier {
    // 第一个参数是刚才的 UUID。实际上可以省略，Minecraft 默认会随机生成一个。
    // 随机生成的 UUID 影响也不大，只是不稳定。
    // 第二个参数是我们一看就知道是干什么的名字。不能是 null。
    // 第三个参数（double value）和第四个参数（int operation）共同决定了这个修饰符会如何影响该属性：
    //   - operation == 0 时，原属性值 + value = 新属性值。
    //   - operation == 1 时，原属性值 + 原属性值 * value = 新属性值。
    //   - operation == 2 时，原属性值 * (1 + value) = 新属性值。
//    public static AttributeModifier MAX_HEALTH = new AttributeModifier(MODIFIER_ID_ONE, , 1.0, 0);

    /**
     * 获取一个属性修饰器
     *
     * @param type  某个属性
     * @param value 变化值
     * @return 修饰器
     */
    public static AttributeModifier getModifier(ATTR_TYPE type, double value) {
        UUID uuid = UUID.randomUUID();
        AttributeModifier modifier = new AttributeModifier(uuid, type.getName(), value, type.getType());
        if (type != ATTR_TYPE.LEVEL)
            modifier.setSaved(false);
        return modifier;
    }

    enum ATTR_TYPE {
        HEALTH("generic.maxHealth", 0),
        KNOCK("generic.knockbackResistance", 0),
        MOVE_SPEED("generic.movementSpeed", 0),
        DAMAGE("generic.attackDamage", 0),
        ATTACK_SPEED("generic.attackSpeed", 0),
        ARMOR("generic.armor", 0),
        LEVEL("tmod.level", 0);

        private final String name;
        private final int type;

        ATTR_TYPE(String attrName, int operation) {
            this.name = attrName;
            this.type = operation;
        }

        public String getName() {
            return name;
        }

        public int getType() {
            return type;
        }
    }
}
