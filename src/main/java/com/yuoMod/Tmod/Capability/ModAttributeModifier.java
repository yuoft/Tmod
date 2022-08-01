package com.yuoMod.Tmod.Capability;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;

import java.util.UUID;

//����������
public class ModAttributeModifier {
    // ��һ�������Ǹղŵ� UUID��ʵ���Ͽ���ʡ�ԣ�Minecraft Ĭ�ϻ��������һ����
    // ������ɵ� UUID Ӱ��Ҳ����ֻ�ǲ��ȶ���
    // �ڶ�������������һ����֪���Ǹ�ʲô�����֡������� null��
    // ������������double value���͵��ĸ�������int operation����ͬ������������η������Ӱ������ԣ�
    //   - operation == 0 ʱ��ԭ����ֵ + value = ������ֵ��
    //   - operation == 1 ʱ��ԭ����ֵ + ԭ����ֵ * value = ������ֵ��
    //   - operation == 2 ʱ��ԭ����ֵ * (1 + value) = ������ֵ��
//    public static AttributeModifier MAX_HEALTH = new AttributeModifier(MODIFIER_ID_ONE, , 1.0, 0);

    /**
     * ��ȡһ������������
     *
     * @param type  ĳ������
     * @param value �仯ֵ
     * @return ������
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
