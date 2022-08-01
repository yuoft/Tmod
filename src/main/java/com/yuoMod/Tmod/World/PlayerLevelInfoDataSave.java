package com.yuoMod.Tmod.World;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

import java.util.*;

//保存数据
public class PlayerLevelInfoDataSave extends WorldSavedData {
    private static final String DATA_NAME = "tmod_playerInfo";
    private static final String NBT_NAME = "player_name";
    private static final String NBT_LEVEL = "player_level";
    private static final String NBT_EXP = "player_exp";

    private final Map<String, LevelInfo> players = new HashMap<>();

    public class LevelInfo {
        private final int level;
        private final int exp;

        public LevelInfo(int level, int exp) {
            this.level = level;
            this.exp = exp;
        }

        public int getLevel() {
            return level;
        }

        public int getExp() {
            return exp;
        }
    }

    public PlayerLevelInfoDataSave(String name) {
        super(name);
    }

    private PlayerLevelInfoDataSave() {
        super(DATA_NAME);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        this.players.clear();
        NBTTagList list = (NBTTagList) nbt.getTag(DATA_NAME);
        for (int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound compound = (NBTTagCompound) list.get(i);
            this.players.put(compound.getString(NBT_NAME), new LevelInfo(compound.getInteger(NBT_LEVEL), compound.getInteger(NBT_EXP)));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagList list = new NBTTagList();
        for (Map.Entry<String, LevelInfo> entry : players.entrySet()) {
            String name = entry.getKey();
            int lv = entry.getValue().getLevel();
            int exp = entry.getValue().getExp();
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setInteger(NBT_LEVEL, lv);
            nbt.setInteger(NBT_EXP, exp);
            nbt.setString(NBT_NAME, name);
            list.appendTag(nbt);
        }
        compound.setTag(DATA_NAME, list);
        return compound;
    }

    public int size() {
        return this.players.size();
    }

    //如果有一项数据为0则表示null
    public boolean isEmpty() {
        return this.players.size() == 0;
    }

    //添加一位玩家的等级数据
    public void add(String playerName, int playerLevel, int playerExp) {
        this.players.put(playerName, new LevelInfo(playerLevel, playerExp));
        this.markDirty();
    }

    //修改指定玩家等级数据
    public void change(String name, int level, int exp) {
        if (this.players.size() > 0) {
            for (Map.Entry<String, LevelInfo> entry : this.players.entrySet()) {
                if (name.equals(entry.getKey())) {
                    entry.setValue(new LevelInfo(level, exp));
                    this.markDirty();
                }
            }
        }
    }

    //有无此玩家名
    public boolean isName(String name) {
        return this.players.containsKey(name);
    }

    //获取指定玩家的等级数据
    public LevelInfo getPlayerInfo(String name) {
        return this.players.get(name);
    }

    public static PlayerLevelInfoDataSave get(World world) {
        MapStorage storage = world.getPerWorldStorage();
        PlayerLevelInfoDataSave data = (PlayerLevelInfoDataSave) storage.getOrLoadData(PlayerLevelInfoDataSave.class, DATA_NAME);
        if (data == null) {
            data = new PlayerLevelInfoDataSave();
            storage.setData(DATA_NAME, data);
        }
        return data;
    }

}
