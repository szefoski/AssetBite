/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamelion.assetbite;

import com.gamelion.assetbite.control.RootTreeControl;
import com.gamelion.assetbite.engine.roottree.RootTreeCatcher;
import com.gamelion.assetbite.gui.MainForm;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author Daniel
 */
public class AssetBite {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                System.out.println(info.getName());
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    //break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        MainForm mf = new MainForm();
        mf.setVisible(true);
        
        
        int aaa = 0;
        
        System.out.flush();
        System.out.println("---------------------");
        System.out.flush();
    }
}
