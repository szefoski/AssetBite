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
public class RootTreeComparator implements Comparator<Path> {

    @Override
    public int compare(Path o1, Path o2) {
        
        if (Files.isDirectory(o1) && !Files.isDirectory(o2)) {
            return -1;
        }
        else if (!Files.isDirectory(o1) && Files.isDirectory(o2)) {
            return 1;
        }
        else {
            return o1.compareTo(o2);
        }
    }
    
}
