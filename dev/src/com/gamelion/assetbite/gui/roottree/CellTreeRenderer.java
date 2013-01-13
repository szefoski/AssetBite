/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamelion.assetbite.gui.roottree;

import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import sun.swing.DefaultLookup;

/**
 *
 * @author Daniel
 */
public class CellTreeRenderer extends DefaultTreeCellRenderer{

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
        Object rawElementData = node.getUserObject();
        if (rawElementData instanceof RootTreeComponent.ElementData) {
            RootTreeComponent.ElementData elementData = (RootTreeComponent.ElementData) rawElementData;

            if (elementData.rte.isDirectory()) {
                setIcon(DefaultLookup.getIcon(this, ui, "Tree.closedIcon"));
            }
        }
        return this;
    }
    
    
}
