package com.gamelion.assetbite.model.elements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PacksCollection {
	private List elements;
	
	public PacksCollection() {
		elements = new ArrayList<>();
	}

	public void add(Pack target) {
		elements.add(target);
	}

	public List<Pack> getElements() {
		return elements;
	}

	public void remove(Pack pack) {
		elements.remove(pack);		
	}

}
