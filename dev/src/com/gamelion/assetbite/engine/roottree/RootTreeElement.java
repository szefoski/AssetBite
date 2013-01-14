/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamelion.assetbite.engine.roottree;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author Daniel
 */
public class RootTreeElement {
    
    private Path path;
    private RootTreeElement parent;
    private Map<Path, RootTreeElement> childrensMap;
    private List<RootTreeElement> childrens;
    private boolean directory;

    public RootTreeElement(Path path, RootTreeElement parent) {
        this.path = path;
        this.parent = parent;
        directory = Files.isDirectory(path);
        childrensMap = new HashMap<>();
        childrens = new ArrayList<>();
    }
    
    void AddChild(Path child) {
        Path pathDiff = path.relativize(child);
        int pathDiffCount = pathDiff.getNameCount();
        Path narestChildPath = child.subpath(0, child.getNameCount() - pathDiffCount + 1);
        RootTreeElement narestChild = AddChildIfNotExists(narestChildPath);

        if (pathDiffCount > 1) {
            narestChild.AddChild(child);
        }
    }
    
    private RootTreeElement AddChildIfNotExists(Path childPath) {
        RootTreeElement child = childrensMap.get(childPath);
        if (child == null) {
            child = new RootTreeElement(childPath, this);
            childrensMap.put(childPath, child);
            childrens.add(child);
        }
        
        return child;
    }
    
    
    public List<RootTreeElement> getChildrens() {
        return Collections.unmodifiableList(childrens);
    }
    
    public boolean isEmpty() {
        return childrensMap.isEmpty();
    }
    
    public final Path GetPath() {
        return path;
    }

    public RootTreeElement getParent() {
        return parent;
    }

    public boolean isDirectory() {
        return directory;
    }
    
    public void sort() {
        Collections.sort(childrens, new RootTreeComparator());
        for(RootTreeElement value : childrens) {
            value.sort();
        }
    }
    
}
