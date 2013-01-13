/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamelion.assetbite.enigne.dataSave;

import java.nio.file.Path;

/**
 *
 * @author Daniel
 */
public class Settings {
    private Path projectSettingsFile;
    private Path projectSettingsDirectory;

    public void setProjectSettingsFile(Path projectSettingsFile) {
        this.projectSettingsFile = projectSettingsFile;
        this.projectSettingsDirectory = projectSettingsFile.getParent();
    }
    
    
}
