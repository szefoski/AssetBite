/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamelion.assetbite.engine.roottree;

import java.nio.file.Path;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author Daniel
 */
public class RootTreeElement {
    
    private Path path;
    private RootTreeElement parent;

    private TreeMap<Path, RootTreeElement> childrens;

    public RootTreeElement(Path path, RootTreeElement parent) {
        this.path = path;
        this.parent = parent;
        childrens = new TreeMap<>(new RootTreeComparator());
    }
    
    void AddChild(Path child) {
        try {
            Path pathDiff = path.relativize(child);
            int pathDiffCount = pathDiff.getNameCount();
            Path narestChildPath = child.subpath(0, child.getNameCount() - pathDiffCount + 1);
            RootTreeElement narestChild = AddChildIfNotExists(narestChildPath);

            if (pathDiffCount > 1) {
                narestChild.AddChild(child);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            int aaa = 0;
        }
    }
    
    private RootTreeElement AddChildIfNotExists(Path childPath) {
        RootTreeElement child = childrens.get(childPath);
        if (child == null) {
            child = new RootTreeElement(childPath, this);
            childrens.put(childPath, child);
        }
        
        return child;
    }
    
    
    public TreeMap<Path, RootTreeElement> getChildrens() {
        return childrens;
    }
    
    public boolean isEmpty() {
        return childrens.isEmpty();
    }
    
    public Path GetPath() {
        return path;
    }

    public RootTreeElement getParent() {
        return parent;
    }

    
}
