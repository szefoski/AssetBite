package com.gamelion.assetbite.control.rootdirectory;

import com.gamelion.assetbite.model.elements.PacksCollection;
import com.gamelion.assetbite.model.elements.TargetsCollection;

public class GuiNotifier {

	private static final GuiNotifier instance = new GuiNotifier();
	
	private GuiNotifier() {}
	 
    public static GuiNotifier getInstance() {
        return instance;
    }

///// Packs Colection 
	public void updatePacks(PacksCollection collection) {
		if (observerPacks != null) {
			observerPacks.observerPacksChange(collection);
		}
	}
	
	private ObserverPacks observerPacks;
	
	public void setObserverPacks(ObserverPacks observer) {
		this.observerPacks = observer;
	}
	
	public static interface ObserverPacks {
		void observerPacksChange(PacksCollection collection);
	}
/////
    
    
///// Targets Colection 
	public void updateTargets(TargetsCollection collection) {
		if (observerTargets != null) {
			observerTargets.observerTargetsChange(collection);
		}
	}
	
	private ObserverTargets observerTargets;
	
	public void setObserverTargets(ObserverTargets observerTargets) {
		this.observerTargets = observerTargets;
	}
	
	public static interface ObserverTargets {
		void observerTargetsChange(TargetsCollection collection);
	}
/////
    
    
///// Project Name  
	public void updateProjectName(String projectName) {
		if (observerProjectName != null) {
			observerProjectName.ObserverProjectNameChange(projectName);
		}
	}
	
	private ObserverProjectName observerProjectName;
	
	public void setObserverProjectName(ObserverProjectName observerProjectName) {
		this.observerProjectName = observerProjectName;
	}
	
	public static interface ObserverProjectName {
		void ObserverProjectNameChange(String name);
	}
/////	
}
