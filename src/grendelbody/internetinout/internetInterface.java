/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grendelbody.internetinout;

import basicstuff.*;
import miscstuff.GreetingClient;
import basicstuff.message;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author christopherrehm
 */
public class internetInterface extends basicObject  {
    
    int runVar = 1;
    int pid; 
    
    //@Override
    public void run() {
        
        message TestMessage =new message();
        ObjectStatus mystats = new basicstuff.ObjectStatus();
        mystats.setMyName("internet Interface");
        Thread intInfThread = new Thread(mystats);
        intInfThread.start();
        
        // get process id number
        try {
             pid = Integer.parseInt(new File("/proc/self").getCanonicalFile().getName());
        } catch (IOException ex) {
            Logger.getLogger(internetInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        GreetingClient myClient = null;
        myClient = new GreetingClient("192.168.0.101",5000);
        
        myClient.startConnection("192.168.0.101",5000);
        System.out.println("-----made contact from internetinterface to router");
        
        try {
            myClient.sendMessageObject(TestMessage);
        } catch (IOException ex) {
            Logger.getLogger(internetInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("-----just past send message statement");
        
        
    }             
//        
} 
    

