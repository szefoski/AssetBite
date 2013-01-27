package com.gamelion.assetbite.model;

public class UUID {
	private static int nextUUID = 1;
	
	private final int uuid;
	
	public UUID(int uuid) {
		this.uuid = uuid;
	}
	
	synchronized public static UUID getNextUUID() {
		int uuid = nextUUID;
		++nextUUID;
		return new UUID(uuid);
	}
	
	
	@Override
	public int hashCode() {
		return uuid;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UUID other = (UUID) obj;
		if (uuid != other.uuid)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "UUID [uuid=" + uuid + "]";
	}
}
