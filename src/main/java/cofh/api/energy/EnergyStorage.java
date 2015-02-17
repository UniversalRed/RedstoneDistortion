package cofh.api.energy;

import net.minecraft.nbt.NBTTagCompound;

/**
 * Reference implementation of {@link IEnergyStorage}. Use/extend this or implement your own.
 * 
 * @author King Lemming
 * 
 */
public class EnergyStorage implements IEnergyStorage {

	public static int energy;
    public static int capacity;
    public static int maxReceive;
    public static int maxExtract;

	public EnergyStorage(int capacity) {

		this(capacity, capacity, capacity);
	}

	public EnergyStorage(int capacity, int maxTransfer) {

		this(capacity, maxTransfer, maxTransfer);
	}

	public EnergyStorage(int capacity, int maxReceive, int maxExtract) {

		EnergyStorage.capacity = capacity;
		EnergyStorage.maxReceive = maxReceive;
		EnergyStorage.maxExtract = maxExtract;
	}

	public EnergyStorage readFromNBT(NBTTagCompound nbt) {

		energy = nbt.getInteger("Energy");

		if (energy > capacity) {
			energy = capacity;
		}
		return this;
	}

	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {

		if (energy < 0) {
			energy = 0;
		}
		nbt.setInteger("Energy", energy);
		return nbt;
	}

	public void setCapacity(int capacity) {

		EnergyStorage.capacity = capacity;

		if (energy > capacity) {
			energy = capacity;
		}
	}

	public void setMaxTransfer(int maxTransfer) {

		setMaxReceive(maxTransfer);
		setMaxExtract(maxTransfer);
	}

	public void setMaxReceive(int maxReceive) {

		EnergyStorage.maxReceive = maxReceive;
	}

	public void setMaxExtract(int maxExtract) {

		EnergyStorage.maxExtract = maxExtract;
	}

	public int getMaxReceive() {

		return maxReceive;
	}

	public int getMaxExtract() {

		return maxExtract;
	}

	/**
	 * This function is included to allow for server -&gt; client sync. Do not call this externally to the containing Tile Entity, as not all IEnergyHandlers
	 * are guaranteed to have it.
	 * 
	 * @param energy
	 */
	public void setEnergyStored(int energy) {

		EnergyStorage.energy = energy;

		if (EnergyStorage.energy > capacity) {
			EnergyStorage.energy = capacity;
		} else if (EnergyStorage.energy < 0) {
			EnergyStorage.energy = 0;
		}
	}

	/**
	 * This function is included to allow the containing tile to directly and efficiently modify the energy contained in the EnergyStorage. Do not rely on this
	 * externally, as not all IEnergyHandlers are guaranteed to have it.
	 * 
	 * @param energy
	 */
	public void modifyEnergyStored(int energy) {

		EnergyStorage.energy += energy;

		if (EnergyStorage.energy > capacity) {
			EnergyStorage.energy = capacity;
		} else if (EnergyStorage.energy < 0) {
			EnergyStorage.energy = 0;
		}
	}

	/* IEnergyStorage */
	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {

		int energyReceived = Math.min(capacity - energy, Math.min(EnergyStorage.maxReceive, maxReceive));

		if (!simulate) {
			energy += energyReceived;
		}
		return energyReceived;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {

		int energyExtracted = Math.min(energy, Math.min(EnergyStorage.maxExtract, maxExtract));

		if (!simulate) {
			energy -= energyExtracted;
		}
		return energyExtracted;
	}

	@Override
	public int getEnergyStored() {

		return energy;
	}

	@Override
	public int getMaxEnergyStored() {

		return capacity;
	}

}
