package com.yuoMod.Tmod.Capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class CapabilityPlayerLevel {
	public static class Storage implements Capability.IStorage<IPlayerLevel>
    {
        @Override
        public NBTBase writeNBT(Capability<IPlayerLevel> capability, IPlayerLevel instance, EnumFacing side)
        {
            NBTTagList list = new NBTTagList();
            Integer lv = instance.getPlayerLevel();
            Integer exp = instance.getPlayerExp();
            NBTTagCompound compoundLv = new NBTTagCompound();
            NBTTagCompound compoundExp = new NBTTagCompound();
            if (lv != null && exp != null)
            {
            	compoundExp.setInteger("player_exp", exp);
            	compoundLv.setInteger("player_level", lv);
            }
            list.appendTag(compoundExp);
            list.appendTag(compoundLv);
            return list;
        }

        @Override
        public void readNBT(Capability<IPlayerLevel> capability, IPlayerLevel instance, EnumFacing side,
                NBTBase nbt)
        {
            NBTTagList list = (NBTTagList) nbt;
            NBTTagCompound compoundLv = list.getCompoundTagAt(0);
            NBTTagCompound compoundExp = list.getCompoundTagAt(1);
            Integer lv = 0;
            Integer exp = 0;
            if (!compoundLv.hasNoTags() && !compoundExp.hasNoTags())
            {
                lv = compoundLv.getInteger("player_level");
                exp = compoundLv.getInteger("player_exp");
            }
            instance.setPlayerLevel(lv);
            instance.setPlayerExp(exp);
        }
    }
	public static class Implementation implements IPlayerLevel
    {
		private Integer PLAYER_LEVEL = 0; //玩家等级
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
	public static class ProviderPlayer implements ICapabilitySerializable<NBTTagCompound>
    {
        private IPlayerLevel level = new Implementation();
        private IStorage<IPlayerLevel> storage = CapabilityLoader.tmodLv.getStorage();

        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing)
        {
            return CapabilityLoader.tmodLv.equals(capability);
        }

        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing)
        {
            if (CapabilityLoader.tmodLv.equals(capability))
            {
                @SuppressWarnings("unchecked")
                T result = (T) level;
                return result;
            }
            return null;
        }

        @Override
        public NBTTagCompound serializeNBT()
        {
            NBTTagCompound compound = new NBTTagCompound();
            compound.setTag("level", storage.writeNBT(CapabilityLoader.tmodLv, level, null));
            return compound;
        }

        @Override
        public void deserializeNBT(NBTTagCompound compound)
        {
            NBTTagList list = (NBTTagList) compound.getTag("level");
            storage.readNBT(CapabilityLoader.tmodLv, level, null, list);
        }
    }
}
