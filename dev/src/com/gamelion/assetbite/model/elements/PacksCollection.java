package com.gamelion.assetbite.model.elements;

import java.util.HashSet;
import java.util.Set;

public class PacksCollection {
	private Set<Pack> elements;
	
	public PacksCollection() {
		elements = new HashSet<>();
	}

	public void add(Pack target) {
		boolean added = elements.add(target);
	}

	public Set<Pack> getElements() {
		return elements;
	}

}
