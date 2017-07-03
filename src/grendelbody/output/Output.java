/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grendelbody.output;

import basicstuff.ObjectStatus;
import grendelbody.internetinout.InternetInterface;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author christopherrehm
 */
public class Output extends basicstuff.BasicObject {
    
    int pid;
    int MyId = 15;
    
    @Override
    public void run() {
        
        try {
             pid = Integer.parseInt(new File("/proc/self").getCanonicalFile().getName());
        } catch (IOException ex) {
            Logger.getLogger(InternetInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ObjectStatus myStats = new basicstuff.ObjectStatus();
        myStats.setMyName("Output cell");
        Thread outputThread = new Thread(myStats);
        outputThread.start();
        System.out.println("We are in the internet output first time"+ "my process ID is "+ pid);
    }    
}
