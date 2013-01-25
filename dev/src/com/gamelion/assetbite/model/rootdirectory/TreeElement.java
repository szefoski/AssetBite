package com.gamelion.assetbite.model.rootdirectory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeElement {
	private Path path;
    private TreeElement parent;
    private Map<Path, TreeElement> childrensMap;
    private List<TreeElement> childrens;
    private boolean directory;
    
    private static TreeComparator comparator = new TreeComparator();

    public TreeElement(Path path, TreeElement parent) {
    	assert(parent != this);
    	
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
        TreeElement narestChild = AddChildIfNotExists(narestChildPath);

        if (pathDiffCount > 1) {
            narestChild.AddChild(child);
        }
    }
    
    private TreeElement AddChildIfNotExists(Path childPath) {
    	TreeElement child = childrensMap.get(childPath);
        if (child == null) {
            child = new TreeElement(childPath, this);
            childrensMap.put(childPath, child);
            childrens.add(child);
        }
        
        return child;
    }
    
    
    public List<TreeElement> getChildrens() {
        return Collections.unmodifiableList(childrens);
    }
    
    public boolean isEmpty() {
        return childrensMap.isEmpty();
    }
    
    public final Path GetPath() {
        return path;
    }

    public TreeElement getParent() {
        return parent;
    }

    public boolean isDirectory() {
        return directory;
    }
    
    public void sort() {
        Collections.sort(childrens, comparator);
        for(TreeElement value : childrens) {
            value.sort();
        }
    }
    
    
    private static class TreeComparator implements Comparator<TreeElement>{
    	@Override
        public int compare(TreeElement o1, TreeElement o2) {
            
            if (o1.isDirectory() && !o2.isDirectory()) {
                return -1;
            }
            else if (!o1.isDirectory() && o2.isDirectory()) {
                return 1;
            }
            else {
                return o1.GetPath().compareTo(o2.GetPath());
            }
        }
    }
}
