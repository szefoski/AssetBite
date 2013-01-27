package com.gamelion.assetbite.model.elements;

import com.gamelion.assetbite.gui.RootDirectory;
import com.gamelion.assetbite.model.UUID;

public class Pack {
	private final UUID uuid;
	private String name;
	private RootDirectory rootDiectory;
	
	public Pack(UUID uuid, String name) {
		this.uuid = uuid;
		this.name = name;
		rootDiectory = new RootDirectory();
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
