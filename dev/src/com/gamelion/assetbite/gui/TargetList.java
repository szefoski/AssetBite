package com.gamelion.assetbite.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.gamelion.assetbite.MainWindow;
import com.gamelion.assetbite.control.rootdirectory.GuiNotifier;
import com.gamelion.assetbite.control.rootdirectory.MainControl;
import com.gamelion.assetbite.model.elements.Target;
import com.gamelion.assetbite.model.elements.TargetsCollection;

public class TargetList extends JList<Target> implements
		GuiNotifier.ObserverTargets, ListSelectionListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5325271628456196979L;
	private DefaultListModel<Target> model = new DefaultListModel<>();
	private static TargetList instance;
	JPopupMenu popupMenuItem;
	JMenuItem popupItemTitle;
	JMenuItem popupItemAdd;
	JMenuItem popupItemRename;
	JMenuItem popupItemCopy;
	JMenuItem popupItemRemove;
	NameChangeWindow nameChangeWindow;
	
	public static TargetList getInstance() {
		return instance;
	}

	public TargetList() {
		instance = this;
		this.setModel(model);
		this.addListSelectionListener(this);
		GuiNotifier.getInstance().setObserverTargets(this);

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
	public void observerTargetsChange(TargetsCollection collection) {
		Target array[] = new Target[collection.getElements().size()];
		collection.getElements().toArray(array);
		this.setListData(array);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			Target selectedTarget = this.getSelectedValue();
			MainControl.getInstance().selectedTarget(selectedTarget);
		}
	}

	MouseAdapter ms = new MouseAdapter() {
		public void mouseClicked(MouseEvent me) {
			if (SwingUtilities.isRightMouseButton(me)) {
				int index = TargetList.this.locationToIndex(me.getPoint());
				TargetList.this.setSelectedIndex(-1);
				TargetList.this.setSelectedIndex(index);
				
				
				final boolean isSelectioEmpty = TargetList.this.isSelectionEmpty();
				final int mouseX = me.getX();
				final int mouseY = me.getY();
				
				String elementName = " ";
				if (TargetList.this.getSelectedValue() != null)
				{
					elementName = TargetList.this.getSelectedValue().getName();
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
						popupMenuItem.show(TargetList.this, mouseX, mouseY);
					}
				});
			}
		}
	};

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(popupItemAdd)) {
		    showNewTargetDialog();
		} else if (e.getSource().equals(popupItemRemove)) {
			MainControl.getInstance().removeTarget(this.getSelectedValue());
		} else if (e.getSource().equals(popupItemRename)) {
			showRenameTargetDialog();
		} else if (e.getActionCommand().equals("NewTargetName")) {
			MainControl.getInstance().addTarget(nameChangeWindow.getText());
			nameChangeWindow.dispose();
			nameChangeWindow = null;
		} else if (e.getActionCommand().equals("ChangeTargetName")) {
			MainControl.getInstance().renameTarget(this.getSelectedValue(), nameChangeWindow.getText());
			nameChangeWindow.dispose();
			nameChangeWindow = null;
		}
	}
	
	
	private void showNewTargetDialog() {
		nameChangeWindow = new NameChangeWindow();
		nameChangeWindow.setModalityType(ModalityType.APPLICATION_MODAL);
		nameChangeWindow.setLocationRelativeTo(MainWindow.GetFrame());
		nameChangeWindow.setTitle("New target name:");
		nameChangeWindow.getOkButton().setActionCommand("NewTargetName");
		nameChangeWindow.getOkButton().addActionListener(this);
		nameChangeWindow.setVisible(true);
	}
	
	private void showRenameTargetDialog() {
		nameChangeWindow = new NameChangeWindow();
		nameChangeWindow.setModalityType(ModalityType.APPLICATION_MODAL);
		nameChangeWindow.setLocationRelativeTo(MainWindow.GetFrame());
		nameChangeWindow.setTitle("change target name:");
		nameChangeWindow.getOkButton().setActionCommand("ChangeTargetName");
		nameChangeWindow.getOkButton().addActionListener(this);
		nameChangeWindow.setText(this.getSelectedValue().getName());
		nameChangeWindow.setVisible(true);
	}

}
