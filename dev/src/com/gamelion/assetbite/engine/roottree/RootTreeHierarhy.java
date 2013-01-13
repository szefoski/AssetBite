/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamelion.assetbite.engine.roottree;

import java.nio.file.Path;
import java.util.TreeSet;

/**
 *
 * @author Daniel
 */
public class RootTreeHierarhy {
    
    RootTreeElement element;
    
    public void SetRoot(Path path) {
        element = new RootTreeElement(path, null);
    }
    
    public void Add(Path path) {
        element.AddChild(path);
    }
    
    public RootTreeElement GetRootElement() {
        return element;
    }
    
    public void clear() {
        element = null;
    }
}
