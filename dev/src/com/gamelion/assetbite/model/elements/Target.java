package com.gamelion.assetbite.model.elements;

import java.util.HashSet;
import java.util.Set;

import com.gamelion.assetbite.model.UUID;

public class Target {

	public PacksCollection getPacksCollection() {
		return packsCollection;
	}

	public void setPacksCollection(PacksCollection packsCollection) {
		this.packsCollection = packsCollection;
	}



	private final UUID uuid;
	private String name;
	private PacksCollection packsCollection;
	
	
	public Target(UUID uuid, String name) {
		this.uuid = uuid;
		this.name = name;
		packsCollection = new PacksCollection();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addPack(Pack target) {
		packsCollection.add(target);
	}
	
	@Override
	public String toString() {
		return name;
	}
}
