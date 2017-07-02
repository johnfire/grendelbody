/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grendelbody.soundin;

import basicstuff.ObjectStatus;
import grendelbody.internetinout.internetInterface;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author christopherrehm
 */
public class soundin extends basicstuff.basicObject {
    
    int pid;
    
    @Override
    public void run() {
        System.out.println("we are in the soundin routine");
        try {
             pid = Integer.parseInt(new File("/proc/self").getCanonicalFile().getName());
        } catch (IOException ex) {
            Logger.getLogger(internetInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ObjectStatus mystats = new basicstuff.ObjectStatus();
        mystats.setMyName("soundin cell");
        Thread soundInThread = new Thread(mystats);
        soundInThread.start(); 
    }    
}
