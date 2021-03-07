package com.yuoMod.Tmod.WorldStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;

public class PlayerInfoWorldSaved extends WorldSavedData{

	private List<Integer> levels = new ArrayList<Integer>();
	private List<Integer> exps = new ArrayList<Integer>();
    private List<UUID> players = new ArrayList<UUID>();

    public PlayerInfoWorldSaved(String name) {
    	super(name);
    }
    
    public int size()
    {
        return players.size();
    }

    public Integer getPlayerLevel(int index)
    {
        return levels.get(index);
    }

    public Integer getPlayerExp(int index)
    {
    	return exps.get(index);
    }
    
    public UUID getPlayerUUID(int index)
    {
        return players.get(index);
    }

    public void add(Integer lv, Integer exp, UUID player)
    {
        levels.add(lv);
        exps.add(exp);
        players.add(player);
        this.markDirty();
    }

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		levels.clear();
		exps.clear();
        players.clear();
        NBTTagList list_lv = (NBTTagList) nbt.getTag("levels");
        NBTTagList list_exp = (NBTTagList) nbt.getTag("exps");
        if (list_lv == null)
        {
            list_lv = new NBTTagList();
        }
        if (list_exp == null)
        {
        	list_exp = new NBTTagList();
        }
        for (int i = list_lv.tagCount() - 1; i >= 0; --i)
        {
            NBTTagCompound compound = (NBTTagCompound) list_lv.get(i);
            levels.add(nbt.getInteger("player_level"));
            exps.add(nbt.getInteger("player_exp"));
            players.add(UUID.fromString(compound.getString("player")));
        }
//        for (int i = list_exp.tagCount() - 1; i >= 0; --i)
//        {
//        	NBTTagCompound compound = (NBTTagCompound) list_exp.get(i);
//        	exps.add(nbt.getInteger("player_exp"));
//        	players.add(UUID.fromString(compound.getString("player")));
//        }
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		NBTTagList list = new NBTTagList();
        for (int i = players.size() - 1; i >= 0; --i)
        {
            Integer level = levels.get(i);
            Integer exp = exps.get(i);
            UUID player = players.get(i);
            NBTTagCompound compound = new NBTTagCompound();
            compound.setInteger("player_level", level);
            compound.setInteger("player_exp", exp);
            compound.setString("player", player.toString());
            list.appendTag(compound);
        }
        nbt.setTag("levels", list);
        return nbt;
	}
	
	public static PlayerInfoWorldSaved get(World world)
    {
        WorldSavedData data = world.getPerWorldStorage().getOrLoadData(PlayerInfoWorldSaved.class, "tmodLevel");
        if (data == null)
        {
            data = new PlayerInfoWorldSaved("tmodLevel");
            world.getPerWorldStorage().setData("tmodLevel", data);
        }
        return (PlayerInfoWorldSaved) data;
    }

    public static PlayerInfoWorldSaved getGlobal(World world)
    {
        WorldSavedData data = world.getMapStorage().getOrLoadData(PlayerInfoWorldSaved.class, "tmodLevelGlobal");
        if (data == null)
        {
            data = new PlayerInfoWorldSaved("tmodLevelGlobal");
            world.getMapStorage().setData("tmodLevelGlobal", data);
        }
        return (PlayerInfoWorldSaved) data;
    }
}
