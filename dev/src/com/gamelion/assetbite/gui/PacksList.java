package com.gamelion.assetbite.gui;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.gamelion.assetbite.control.rootdirectory.GuiNotifier;
import com.gamelion.assetbite.model.elements.Pack;
import com.gamelion.assetbite.model.elements.PacksCollection;

public class PacksList extends JList<Pack> implements GuiNotifier.ObserverPacks, ListSelectionListener{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4196462060988562246L;
	private DefaultListModel<Pack> model = new DefaultListModel<>();
	
	
	public PacksList() {
		this.setModel(model);
		this.addListSelectionListener(this);
		GuiNotifier.getInstance().setObserverPacks(this);
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		if ( e.getValueIsAdjusting() )
		System.out.println("Packs: " + this.getLeadSelectionIndex());
		
	}


	@Override
	public void observerPacksChange(PacksCollection collection) {
		if (!model.isEmpty()) {
			model.clear();
		}
		
		if (collection != null) {
			for (Pack target : collection.getElements()) {
				model.addElement(target);
			}
		}
	}
}
