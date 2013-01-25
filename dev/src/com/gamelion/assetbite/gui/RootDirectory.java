package com.gamelion.assetbite.gui;

import java.nio.file.Path;
import java.util.HashMap;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.gamelion.assetbite.control.rootdirectory.RootTreeControl;
import com.gamelion.assetbite.model.rootdirectory.TreeElement;

public class RootDirectory extends JTree{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3182643413390999868L;
	
	private DefaultTreeModel treeModel2 = new DefaultTreeModel(null);
    DefaultMutableTreeNode root;
    
    HashMap<TreeElement, DefaultMutableTreeNode> map = new HashMap<>();

	public RootDirectory() {
		super();
		RootTreeControl.getInstance().setGuiElement(this);
		clear();
	}
	
	
	final public void setRootName(TreeElement element) {
        root.setUserObject(new ElementData(element));
    }
	
	
	public void Add(TreeElement element) {
        
        DefaultMutableTreeNode parent = map.get(element.getParent());
        if (parent == null) {
            parent = root;
        }
        
        ElementData data = new ElementData(element);
        DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(data);
        parent.add(treeNode);
        map.put(element, treeNode);
    }
	
	void AddElement(DefaultMutableTreeNode parent, Path element) {
        parent.add(new DefaultMutableTreeNode(element));
    }
	
	
	public final void clear() {
        root = new DefaultMutableTreeNode("Refreshing...");
        treeModel2.setRoot(root);
        this.setModel(treeModel2);
    }
	
	
	public void refresh() {
        treeModel2.reload();
    }
	
	
	public static class ElementData {
        TreeElement rte;

        public ElementData(TreeElement rte) {
            this.rte = rte;
        }

        @Override
        public String toString() {
            return rte.GetPath().getFileName().toString();
        }
    }

}
