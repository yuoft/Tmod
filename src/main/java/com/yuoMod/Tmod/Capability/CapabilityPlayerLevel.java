package com.yuoMod.Tmod.Capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nullable;

public class CapabilityPlayerLevel {
    public static class Storage implements Capability.IStorage<IPlayerLevel> {

        @Override
        public void readNBT(Capability<IPlayerLevel> capability, IPlayerLevel instance, EnumFacing side, NBTBase nbt) {
            if (!nbt.hasNoTags()) {
                NBTTagCompound compound = (NBTTagCompound) nbt;
                NBTTagCompound tmod_level = compound.getCompoundTag("tmod_level");
                Integer lv = tmod_level.getInteger("player_level");
                Integer exp = tmod_level.getInteger("player_exp");
                instance.setPlayerLevel(lv);
                instance.setPlayerExp(exp);
            }
        }

        @Nullable
        @Override
        public NBTBase writeNBT(Capability<IPlayerLevel> capability, IPlayerLevel instance, EnumFacing side) {
            Integer lv = instance.getPlayerLevel();
            Integer exp = instance.getPlayerExp();
            NBTTagCompound compound = new NBTTagCompound();
            compound.setInteger("player_level", lv);
            compound.setInteger("player_exp", exp);
//            NBTTagCompound nbt = new NBTTagCompound();
//            compound.setTag("tmod_level", compound);
            return compound;
        }
    }

    public static class Implementation implements IPlayerLevel{
        private Integer PLAYER_LEVEL = 1; //玩家等级
        private Integer PLAYER_EXP = 0; //玩家经验

        @Override
        public void setPlayerLevel(Integer level) {
            this.PLAYER_LEVEL = level;
        }

        @Override
        public void setPlayerExp(Integer exp) {
            this.PLAYER_EXP = exp;
        }

        @Override
        public Integer getPlayerLevel() {
            return this.PLAYER_LEVEL;
        }

        @Override
        public Integer getPlayerExp() {
            return this.PLAYER_EXP;
        }
    }

    public static class ProviderPlayer implements ICapabilitySerializable<NBTTagCompound> {
        private final Implementation level = new Implementation();
        private final IStorage<IPlayerLevel> storage = CapabilityLoader.tmodLv.getStorage();

        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
            return CapabilityLoader.tmodLv.equals(capability);
        }

        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
            if (CapabilityLoader.tmodLv.equals(capability)) {
                T result = (T) level;
                return result;
            }
            return null;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            return (NBTTagCompound) storage.writeNBT(CapabilityLoader.tmodLv, level, null);
        }

        @Override
        public void deserializeNBT(NBTTagCompound compound) {
            storage.readNBT(CapabilityLoader.tmodLv, level, null, compound);
        }
    }
}
