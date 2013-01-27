package com.gamelion.assetbite.control.rootdirectory;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.gamelion.assetbite.model.ProjectLoader;
import com.gamelion.assetbite.model.UUID;
import com.gamelion.assetbite.model.elements.Project;
import com.gamelion.assetbite.model.elements.Target;

public class MainControl {
	private static final MainControl instance = new MainControl();
	
	private Project project;
	
	private MainControl() {}
	 
    public static MainControl getInstance() {
        return instance;
    }
	
	public void LoadProject() {
		Path path = Paths.get("e:\\Projects\\Gamelion\\c++\\rotg\\test.assetbite");
		ProjectLoader pl = new ProjectLoader();
		project = pl.Load(path);
		
		GuiNotifier.getInstance().updatePacks(null);
		GuiNotifier.getInstance().updateProjectName(project.getName());
		GuiNotifier.getInstance().updateTargets(project.getTargets());
	}
	
	public void addTarget(String name) {
		project.addTarget(new Target(UUID.getNextUUID(), name));
		GuiNotifier.getInstance().updatePacks(null);
		GuiNotifier.getInstance().updateTargets(project.getTargets());
	}
	
	public void removeTarget(Target target) {
		project.removeTarget(target);
		GuiNotifier.getInstance().updatePacks(null);
		GuiNotifier.getInstance().updateTargets(project.getTargets());
	}
	
	public void ChangeProjectName(String name) {
		project.setName(name);
		GuiNotifier.getInstance().updateProjectName(project.getName());
	}
	
	public void selectedTarget(Target target) {
		System.out.println(target);
		if (target == null) {
			GuiNotifier.getInstance().updatePacks(null);
		} else {
			GuiNotifier.getInstance().updatePacks(target.getPacksCollection());
		}
	}
}
