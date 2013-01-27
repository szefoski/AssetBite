package com.gamelion.assetbite.model;

import java.nio.file.Path;

public class Settings {

	private static Settings instance = new Settings();
	
	private Path projectPath;
	private Path projectDirectory;
	
	public static Settings getInstance() {
		return instance;
	}
	
	public final Path getProjectPath() {
		return projectPath;
	}
	
	public final void setProjectPath(Path projectPath) {
		assert(projectPath.isAbsolute());
		this.projectPath = projectPath;
		this.projectDirectory = projectPath.getParent();
	}
	
	public final Path getProjectDirectory() {
		return projectDirectory;
	}
	
}
