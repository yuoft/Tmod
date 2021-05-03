package com.yuoMod.Tmod.TileEntity;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.yuoMod.Tmod.Common.Blocks.blockLoader;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;

/**
 * ����Դ������https://github.com/NinjaPhenix/Torcherino/tree/1.11.2-forge�����ٻ�ѣ�
 * 
 * @author yuo
 *
 */
public class TileTorcherino extends TileEntity implements ITickable {
	private static final String[] MODES = new String[] { "ֹͣ", "��Χ: 3x3x3", "��Χ: 5x3x5", "��Χ: 7x3x7",
			"��Χ: 9x3x9" };
	private static final int SPEEDS = 4;
	private boolean state;
	private byte speed; //�ٶ�
	private byte mode; //��Χ
	private byte cachedMode;
	private Random rand;
	private int xMin;
	private int yMin;
	private int zMin;
	private int xMax;
	private int yMax;
	private int zMax;

	public TileTorcherino() {
		this.cachedMode = -1;
		this.rand = new Random();
		//��ʼ�����ᱻ���ٷ���
		blacklistBlock(Blocks.AIR);
		blacklistBlock(Blocks.WATER);
		blacklistBlock(Blocks.FLOWING_WATER);
		blacklistBlock(Blocks.LAVA);
		blacklistBlock(Blocks.FLOWING_LAVA);
		blacklistBlock(blockLoader.speed_torch);
		blacklistTile(TileTorcherino.class);
	}

	protected int speed(int base) {
		return base;
	}

	@Override
	public void update() {
		if (this.world.isRemote)
			return;
		if (this.state || this.mode == 0 || this.speed == 0)
			return;
		this.updateCachedModeIfNeeded();
		this.tickNeighbors();
	}

	// ���ü��ٷ�Χ
	private void updateCachedModeIfNeeded() {
		if (this.cachedMode != this.mode) {
			this.xMin = this.pos.getX() - this.mode;
			this.yMin = this.pos.getY() - 1;
			this.zMin = this.pos.getZ() - this.mode;
			this.xMax = this.pos.getX() + this.mode;
			this.yMax = this.pos.getY() + 1;
			this.zMax = this.pos.getZ() + this.mode;
			this.cachedMode = this.mode;
		}
	}

	// ���ٷ�Χ
	private void tickNeighbors() {
		for (int x = this.xMin; x <= this.xMax; x++) {
			for (int y = this.yMin; y <= this.yMax; y++) {
				for (int z = this.zMin; z <= this.zMax; z++) {
					this.tickBlock(new BlockPos(x, y, z));
				}
			}
		}
	}

	// �޸ķ��������ʱ���
	private void tickBlock(BlockPos pos) {
		IBlockState blockState = this.world.getBlockState(pos);
		Block block = blockState.getBlock();
		if (block == null || block instanceof BlockFluidBase || isBlockBlacklisted(block)) {
			return;
		}
		if (block.getTickRandomly()) {
			for (int i = 0; i < this.speed(this.speed); i++) {
				if (getWorld().getBlockState(pos) != blockState)
					break;
				block.updateTick(this.world, pos, blockState, this.rand);
			}
		}
		if (block.hasTileEntity(this.world.getBlockState(pos))) {
			TileEntity tile = this.world.getTileEntity(pos);
			if (tile == null || tile.isInvalid()) {
				return;
			}
			if (isTileBlacklisted(tile.getClass()))
				return;
			for (int i = 0; i < this.speed(this.speed); i++) {
				if (tile.isInvalid()) {
					break;
				}
				if (tile instanceof ITickable) {
					((ITickable) tile).update();
				}
			}
		}
	}
	
	public void setState(boolean state) {
		this.state = state;
	}

	// �л�����״̬
	public void changeState() {
		if (this.state) {
			this.state = false;
		} else {
			this.state = true;
		}
	}

	// ��ȡ����״̬
	public boolean getState() {
		return this.state;
	}

	// �ı����ģʽ(�ٶȺͷ�Χ)
	public void changeMode(boolean modifier) {
		if (modifier) {
			if (this.speed < TileTorcherino.SPEEDS) {
				this.speed++;
			} else {
				this.speed = 0;
			}
		} else {
			if (this.mode < MODES.length - 1) {
				this.mode++;
			} else {
				this.mode = 0;
			}
		}
	}

	public TextComponentString getDescription() {
		return new TextComponentString(
				TileTorcherino.MODES[this.mode] + " | �ٶ�: " + this.speed(this.speed) * 100 + "%");
	}

	public String getMode() {
		return TileTorcherino.MODES[this.mode];
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setByte("Speed", this.speed);
		tag.setByte("Mode", this.mode);
		tag.setBoolean("PoweredByRedstone", this.state);
		return tag;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.speed = tag.getByte("Speed");
		this.mode = tag.getByte("Mode");
		this.state = tag.getBoolean("PoweredByRedstone");
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new SPacketUpdateTileEntity(getPos(), -999, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
		this.readFromNBT(pkt.getNbtCompound());
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}

	public static void blacklistBlock(Block block) {
		blacklistedBlocks.add(block);
	}

	public static void blacklistTile(Class<? extends TileEntity> tile) {
		blacklistedTiles.add(tile);
	}

	public static boolean isBlockBlacklisted(Block block) {
		return blacklistedBlocks.contains(block);
	}

	public static boolean isTileBlacklisted(Class<? extends TileEntity> tile) {
		return blacklistedTiles.contains(tile);
	}
	//���ᱻ���ٵķ���ͷ���ʵ��
	private static Set<Block> blacklistedBlocks = new HashSet<Block>();
	private static Set<Class<? extends TileEntity>> blacklistedTiles = new HashSet<Class<? extends TileEntity>>();
}