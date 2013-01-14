/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamelion.assetbite.engine.roottree;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

/**
 *
 * @author Daniel
 */
public class RootTreeComparator implements Comparator<RootTreeElement> {

    @Override
    public int compare(RootTreeElement o1, RootTreeElement o2) {
        
        if (Files.isDirectory(o1.GetPath()) && !Files.isDirectory(o2.GetPath())) {
            return -1;
        }
        else if (!Files.isDirectory(o1.GetPath()) && Files.isDirectory(o2.GetPath())) {
            return 1;
        }
        else {
            return o1.GetPath().compareTo(o2.GetPath());
        }
    }
    
}
