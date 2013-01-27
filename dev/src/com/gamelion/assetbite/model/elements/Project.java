package com.gamelion.assetbite.model.elements;

import java.nio.file.Path;

import com.gamelion.assetbite.model.UUID;

public class Project {
	
	private final UUID uuid;
	private String name;
	private TargetsCollection targets;
	
	private Path projectDir;
	
	
	public Project(UUID uuid, String name, Path projectDir) {
		assert(projectDir.isAbsolute());
		this.uuid = uuid;
		this.projectDir = projectDir; 
		this.name = name;
		targets = new TargetsCollection();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public TargetsCollection getTargets() {
		return targets;
	}
	
	public void addTarget(Target target) {
		targets.add(target);
	}
	
	public void removeTarget(Target target) {
		targets.remove(target);
	}

}
