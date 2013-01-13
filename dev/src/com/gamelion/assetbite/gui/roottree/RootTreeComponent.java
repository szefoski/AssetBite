/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamelion.assetbite.gui.roottree;

import com.gamelion.assetbite.control.RootTreeControl;
import com.gamelion.assetbite.engine.roottree.RootTreeElement;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author Daniel
 */
public class RootTreeComponent extends JTree {

    private DefaultTreeModel treeModel2 = new DefaultTreeModel(null);
    DefaultMutableTreeNode root;
    
    HashMap<RootTreeElement, DefaultMutableTreeNode> map = new HashMap<>();

    public RootTreeComponent() {
        RootTreeControl.getInstance().setRootTreeComponent(this);
        getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        clear();
        
        this.setCellRenderer( new CellTreeRenderer());
    }
    
    final public void setRootName(RootTreeElement element) {
        root.setUserObject(new ElementData(element));
    }

    public void Add(RootTreeElement element) {
        
        DefaultMutableTreeNode parent = map.get(element.getParent());
        if (parent == null) {
            parent = root;
        }
        
        ElementData data = new ElementData(element);
        DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(data);
        parent.add(treeNode);
        map.put(element, treeNode);
    }
    
    DefaultMutableTreeNode FindParent(RootTreeElement element) {
        return null;
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
        RootTreeElement rte;

        public ElementData(RootTreeElement rte) {
            this.rte = rte;
        }

        @Override
        public String toString() {
            return rte.GetPath().getFileName().toString();
        }
    }
}
