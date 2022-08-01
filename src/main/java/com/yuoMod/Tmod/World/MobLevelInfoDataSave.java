package com.yuoMod.Tmod.World;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

//保存数据
public class MobLevelInfoDataSave extends WorldSavedData {
    private static final String DATA_NAME = "tmod_mobLevelBase";
    private static final String NBT_LEVEL = "mobLevelBase";
    private static final String NBT_SPEED = "mobLevelUpSpeed";

    private int mobLevelBase = 0;
    private int mobLevelUpSpeed = 0;

    public MobLevelInfoDataSave(String name) {
        super(name);
    }

    private MobLevelInfoDataSave() {
        super(DATA_NAME);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        mobLevelBase = nbt.getInteger(NBT_LEVEL);
        mobLevelUpSpeed = nbt.getInteger(NBT_SPEED);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger(NBT_LEVEL, mobLevelBase);
        compound.setInteger(NBT_SPEED, mobLevelUpSpeed);
        return compound;
    }

    public int getMobLevelBase() {
        return mobLevelBase;
    }

    public int getMobLevelUpSpeed() {
        return mobLevelUpSpeed;
    }

    public void setMobLevelBase(int mobLevelBase) {
        this.mobLevelBase = mobLevelBase;
        this.markDirty();
    }

    public void setMobLevelUpSpeed(int mobLevelUpSpeed) {
        this.mobLevelUpSpeed = mobLevelUpSpeed;
        this.markDirty();
    }

    //如果有一项数据为0则表示null
    public boolean isEmpty() {
        return this.mobLevelBase == 0 || this.mobLevelUpSpeed == 0;
    }

    //初始化默认数据
    public void initData() {
        this.mobLevelBase = 1;
        this.mobLevelUpSpeed = 15;
        this.markDirty();
    }

    //修改2想项数据
    public void change(int mobLevelBase, int mobLevelUpSpeed) {
        this.mobLevelBase = mobLevelBase;
        this.mobLevelUpSpeed = mobLevelUpSpeed;
        this.markDirty();
    }

    //增加基础等级1
    public void addBase() {
        this.mobLevelBase++;
        this.markDirty();
    }

    public void addSpeed() {
        this.mobLevelUpSpeed++;
        this.markDirty();
    }

    public static MobLevelInfoDataSave get(World world) {
        MapStorage storage = world.getPerWorldStorage();
        MobLevelInfoDataSave data = (MobLevelInfoDataSave) storage.getOrLoadData(MobLevelInfoDataSave.class, DATA_NAME);
        if (data == null) {
            data = new MobLevelInfoDataSave();
            storage.setData(DATA_NAME, data);
        }
        return data;
    }

}
