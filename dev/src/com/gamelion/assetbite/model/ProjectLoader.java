package com.gamelion.assetbite.model;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import com.gamelion.assetbite.model.elements.Pack;
import com.gamelion.assetbite.model.elements.Project;
import com.gamelion.assetbite.model.elements.Target;

public class ProjectLoader {
	public Project Load(Path projectSettingsPath) {
		assert(projectSettingsPath.isAbsolute());
		Settings.getInstance().setProjectPath(projectSettingsPath);
		Project project = new Project(UUID.getNextUUID(), projectSettingsPath.toString(), projectSettingsPath);

		LoadTargets(project);

		return project;
	}
	
	private void LoadTargets(Project project) {
		Target target1 = new Target(UUID.getNextUUID(), "Target 1");
		project.addTarget(target1);
		LoadPacks(target1);
		
		Target target2 = new Target(UUID.getNextUUID(), "Target 2");
		project.addTarget(target2);
		LoadPacks(target2);
		
		Target target3 = new Target(UUID.getNextUUID(), "Target 3");
		project.addTarget(target3);
		LoadPacks(target3);
	}
	
	private void LoadPacks(Target target) {
		Pack pack1 = new Pack(UUID.getNextUUID(), "Pack: " + UUID.getNextUUID());
		target.addPack(pack1);
		
		Pack pack2 = new Pack(UUID.getNextUUID(), "Pack: " + UUID.getNextUUID());
		target.addPack(pack2);
		
		Pack pack3 = new Pack(UUID.getNextUUID(), "Pack: " + UUID.getNextUUID());
		target.addPack(pack3);
	}
}
