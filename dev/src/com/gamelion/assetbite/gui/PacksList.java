package com.gamelion.assetbite.gui;

import java.awt.EventQueue;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.gamelion.assetbite.MainWindow;
import com.gamelion.assetbite.control.rootdirectory.GuiNotifier;
import com.gamelion.assetbite.control.rootdirectory.MainControl;
import com.gamelion.assetbite.control.rootdirectory.RootTreeControl;
import com.gamelion.assetbite.model.elements.Pack;
import com.gamelion.assetbite.model.elements.PacksCollection;
import com.gamelion.assetbite.model.elements.Target;

public class PacksList extends JList<Pack> implements GuiNotifier.ObserverPacks, ListSelectionListener, ActionListener{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4196462060988562246L;
	private DefaultListModel<Pack> model = new DefaultListModel<>();
	
	JPopupMenu popupMenuItem;
	JMenuItem popupItemTitle;
	JMenuItem popupItemAdd;
	JMenuItem popupItemRename;
	JMenuItem popupItemCopy;
	JMenuItem popupItemRemove;
	NameChangeWindow nameChangeWindow;
	
	
	public PacksList() {
		this.setModel(model);
		this.addListSelectionListener(this);
		GuiNotifier.getInstance().setObserverPacks(this);
		
		popupMenuItem = new JPopupMenu();
		popupMenuItem.add(popupItemTitle = new JMenuItem(" "));
		popupItemTitle.setEnabled(false);
		popupMenuItem.addSeparator();
		popupMenuItem.add(popupItemAdd = new JMenuItem("Add"));
		popupItemAdd.addActionListener(this);
		popupMenuItem.add(popupItemRename = new JMenuItem("Rename"));
		popupItemRename.addActionListener(this);
		popupMenuItem.add(popupItemCopy = new JMenuItem("Copy"));
		popupItemCopy.addActionListener(this);
		popupMenuItem.add(popupItemRemove = new JMenuItem("Remove"));
		popupItemRemove.addActionListener(this);
		

		this.addMouseListener(ms);
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		if ( !e.getValueIsAdjusting() )
		{
			System.out.println("Packs: " + this.getSelectedValue());
			RootTreeControl.getInstance().refreshRootTree();
		}
		
	}


	@Override
	public void observerPacksChange(PacksCollection collection) {		
		if (collection != null) {
			Pack array[] = new Pack[collection.getElements().size()];
			collection.getElements().toArray(array);
			this.setListData(array);
		} else {
			this.setListData(new Pack[0]);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(popupItemAdd)) {
		    showNewTargetDialog();
		} else if (e.getSource().equals(popupItemRemove)) {
			MainControl.getInstance().removePack(this.getSelectedValue());
		} else if (e.getSource().equals(popupItemRename)) {
			showRenameTargetDialog();
		} else if (e.getActionCommand().equals("NewElement")) {
			MainControl.getInstance().addPack(nameChangeWindow.getText());
			nameChangeWindow.dispose();
			nameChangeWindow = null;
		} else if (e.getActionCommand().equals("ChangeTargetName")) {
			//MainControl.getInstance().addTarget(nameChangeWindow.getText());
			//MainControl.getInstance().renameTarget(this.getSelectedValue(), nameChangeWindow.getText());
			nameChangeWindow.dispose();
			nameChangeWindow = null;
		}
	}
	
	
	MouseAdapter ms = new MouseAdapter() {
		public void mouseClicked(MouseEvent me) {
			if (SwingUtilities.isRightMouseButton(me)) {
				int index = PacksList.this.locationToIndex(me.getPoint());
				PacksList.this.setSelectedIndex(-1);
				PacksList.this.setSelectedIndex(index);
				
				
				final boolean isSelectioEmpty = PacksList.this.isSelectionEmpty();
				final int mouseX = me.getX();
				final int mouseY = me.getY();
				
				String elementName = " ";
				if (PacksList.this.getSelectedValue() != null)
				{
					elementName = PacksList.this.getSelectedValue().getName();
				}
				
				popupItemTitle.setText(elementName);
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						popupItemAdd.setEnabled(true);
						if (isSelectioEmpty)
						{
							popupItemTitle.setVisible(false);
							popupItemRename.setEnabled(false);
							popupItemCopy.setEnabled(false);
							popupItemRemove.setEnabled(false);
						} else {
							popupItemTitle.setVisible(true);
							popupItemRename.setEnabled(true);
							popupItemCopy.setEnabled(true);
							popupItemRemove.setEnabled(true);
						}
						popupMenuItem.show(PacksList.this, mouseX, mouseY);
					}
				});
			}
		}
	};
	
	
	private void showNewTargetDialog() {
		nameChangeWindow = new NameChangeWindow();
		nameChangeWindow.setModalityType(ModalityType.APPLICATION_MODAL);
		nameChangeWindow.setLocationRelativeTo(MainWindow.GetFrame());
		nameChangeWindow.setTitle("New pack name:");
		nameChangeWindow.getOkButton().setActionCommand("NewElement");
		nameChangeWindow.getOkButton().addActionListener(this);
		nameChangeWindow.setVisible(true);
	}
	
	private void showRenameTargetDialog() {
		nameChangeWindow = new NameChangeWindow();
		nameChangeWindow.setModalityType(ModalityType.APPLICATION_MODAL);
		nameChangeWindow.setLocationRelativeTo(MainWindow.GetFrame());
		nameChangeWindow.setTitle("Change pack name:");
		nameChangeWindow.getOkButton().setActionCommand("ChangeTargetName");
		nameChangeWindow.getOkButton().addActionListener(this);
		nameChangeWindow.setText(this.getSelectedValue().getName());
		nameChangeWindow.setVisible(true);
	}
}
