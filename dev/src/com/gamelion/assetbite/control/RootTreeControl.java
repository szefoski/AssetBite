/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamelion.assetbite.control;

import com.gamelion.assetbite.engine.roottree.RootTreeHierarhy;
import com.gamelion.assetbite.engine.roottree.RootTreeElement;
import com.gamelion.assetbite.AssetBite;
import com.gamelion.assetbite.engine.roottree.RootTreeCatcher;
import com.gamelion.assetbite.gui.roottree.RootTreeComponent;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class RootTreeControl {
    
    private RootTreeHierarhy rth;
    private RootTreeComponent rtc;
    private Path rootPath;
    
    private RootTreeControl() {
        rth = new RootTreeHierarhy();
    }
    
    public static RootTreeControl getInstance() {
        return RootTreeControlHolder.INSTANCE;
    }
    
    public void setRootTreeComponent(RootTreeComponent rtc) {
        this.rtc = rtc;
    }
    
    public void Add(Path path) {
        Path nodeRel = rootPath.relativize(path);
        if (!nodeRel.toString().equals("")) {
            rth.Add(path);
        }
    }
    
    private static class RootTreeControlHolder {

        private static final RootTreeControl INSTANCE = new RootTreeControl();
    }
    
    
    public void setRootPath(String s) {
        rootPath = FileSystems.getDefault().getPath(s);
    }

    
    public void refresh() {
        rth.clear();
        rtc.clear();
        rtc.refresh();
        rth.SetRoot(rootPath);
        startRefresh();
    }
    
    private void startRefresh() {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                RootTreeCatcher t = new RootTreeCatcher();
                try {
                    Files.walkFileTree(rootPath, t);
                    rth.sort();
                    finishedRefresh();
                    System.gc();
                } catch (IOException ex) {
                    Logger.getLogger(AssetBite.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
        Thread t = new Thread(r);
        t.start();
    }
    
    private void finishedRefresh() {
        rtc.setRootName(rth.GetRootElement());
        walk(rth.GetRootElement());
        rtc.refresh();
        
    }
    
    private void walk(RootTreeElement element) {
        for(RootTreeElement value : element.getChildrens()) {
            rtc.Add(value);
            if (!value.isEmpty()) {
                walk(value);
            }
        }
    }
}
