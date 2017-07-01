/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grendelbody.internetinout;

import basicstuff.basicObject;
import basicstuff.message;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author christopherrehm
 */
public class internetInterface extends basicObject implements Runnable {
    int runVar = 1;
    int pid;
    @Override
    public void run() {
        try {
             pid = Integer.parseInt(new File("/proc/self").getCanonicalFile().getName());
        } catch (IOException ex) {
            Logger.getLogger(internetInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("we are in the internet interface routine first time"+ "my process ID is "+ pid);
        while (runVar== 1){
            try {
                Thread.sleep(5000);
                System.out.println("we are in the internet interface routine,process number "+pid);  
            } catch (InterruptedException ex) {
                Logger.getLogger(internetInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }   
}
