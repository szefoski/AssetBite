package com.gamelion.assetbite.control.rootdirectory;

import java.nio.file.Paths;

import com.gamelion.assetbite.gui.RootDirectory;
import com.gamelion.assetbite.model.rootdirectory.DirectoryDataModel;
import com.gamelion.assetbite.model.rootdirectory.TreeElement;

public class RootTreeControl {
	private static final RootTreeControl instance = new RootTreeControl();
	
	DirectoryDataModel ddm;
	private RootDirectory guiElement;
	 
    private RootTreeControl() {}
 
    public static RootTreeControl getInstance() {
        return instance;
    }
    
    
    public void setGuiElement(RootDirectory guiElement) {
		this.guiElement = guiElement;
	}

	public void refreshRootTree() {
    	Runnable r = new Runnable() {
			@Override
			public void run() {
				long start = System.currentTimeMillis();
				
				guiElement.clear();
				guiElement.refresh();
				
				System.out.println("Directory:");
				ddm = new DirectoryDataModel(Paths.get("res"));
				ddm.refresh();
				finishedRefresh();
				long duration = System.currentTimeMillis() - start;
				System.out.println("Directory:" + (duration / 1000.0f));
			}
		};
		
		new Thread(r).start();
    }
	
	
	private void finishedRefresh() {
		guiElement.setRootName(ddm.GetRootElement());
		guiElement.walk(ddm.GetRootElement());
        guiElement.refresh();
    }
    
    
}
