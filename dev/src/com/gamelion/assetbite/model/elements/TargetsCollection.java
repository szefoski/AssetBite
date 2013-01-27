package com.gamelion.assetbite.model.elements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TargetsCollection {
	private List<Target> elements;
	
	public TargetsCollection() {
		elements = new ArrayList<>();
	}

	public void add(Target target) {
		boolean added = elements.add(target);
	}

	public List<Target> getElements() {
		return elements;
	}

	public void remove(Target target) {
		elements.remove(target);
	}

}
