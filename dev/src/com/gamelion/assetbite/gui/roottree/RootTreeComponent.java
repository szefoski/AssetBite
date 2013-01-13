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
        root = new DefaultMutableTreeNode("Root");
        treeModel2.setRoot(root);
        this.setModel(treeModel2);
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
        //data.getHierarhy();
    }
    
    DefaultMutableTreeNode FindParent(RootTreeElement element) {
        return null;
    }
    
    private void AddHierarhy(Path node) {
        DefaultMutableTreeNode currentTreeNode = root;
        AddElement(root, node);
        //root.add(top2);
        /*Iterator<Path> it = node.iterator();
        while(it.hasNext()) {
            Path p = it.next();
            String elementName = root.toString();
            boolean found = false;
            DefaultMutableTreeNode newRoot = null;
            for (Enumeration<DefaultMutableTreeNode> e = root.breadthFirstEnumeration(); e.hasMoreElements();) {
                DefaultMutableTreeNode element = e.nextElement();
                String treeNodeName = element.getUserObject().toString();
                
                if (elementName.equals(treeNodeName)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                //DefaultMutableTreeNode newRoot
            }
            int aaa = 0;
        }
        */
    }
    
    void AddElement(DefaultMutableTreeNode parent, Path element) {
        parent.add(new DefaultMutableTreeNode(element));
        //parent.insert(new DefaultMutableTreeNode(element), 0);
    }
    
    public void refresh() {
        treeModel2.reload();
    }
    
    class ElementData {
        RootTreeElement rte;

        public ElementData(RootTreeElement rte) {
            this.rte = rte;
        }

        @Override
        public String toString() {
            return rte.GetPath().getFileName().toString();
        }
        
        ArrayList<String> getHierarhy() {
            ArrayList<String> list = new ArrayList<>();
            
            RootTreeElement currentElement = rte;
            list.add(currentElement.GetPath().toString());
            
            while(currentElement.getParent() != null) {
                currentElement = currentElement.getParent();
                list.add(currentElement.GetPath().toString());
            }
            
            return list;
        }
    }
}
