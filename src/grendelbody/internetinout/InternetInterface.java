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
public class InternetInterface extends basicObject  {
    
    int pid; 
    
    @Override
    public void run() {
        
        System.out.println("im in the internet interface run function");
        message testMessage =new message();
        testMessage.setOrigin(00005);
        testMessage.setDestination(101);
        testMessage.setActionCode(0);
        testMessage.setDataID(11111);
        testMessage.setMessageTxt("aha this works!!!!!!!!!!!!!!!!");
        System.out.println(testMessage);
        
        //start status monitor
        ObjectStatus myStats = new basicstuff.ObjectStatus();
        myStats.setMyName("internet Interface");
        Thread intInfThread = new Thread(myStats);
        intInfThread.start();
        
        // get process id number
        try {
             pid = Integer.parseInt(new File("/proc/self").getCanonicalFile().getName());
        } catch (IOException ex) {
            Logger.getLogger(InternetInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        // set up connection 
        GreetingClient myClient = null;
        myClient = new GreetingClient("192.168.0.101",5000);
        myClient.startConnection("192.168.0.101",5000);
        
        System.out.println("-----made contact from internet Interface to router");
        
        try {
            myClient.sendMessageObject(testMessage);
        } catch (IOException ex) {
            Logger.getLogger(InternetInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("-----just past send message statement");
        String[] str = {"mememe","you","no-data","the nsa is watchin ya"};
        String aMessage = this.buildMessage(str);
    } 
    
    public String assembleMessage (String[] args){
        int loopCounter = 0;
        String theMessage = "";
        for (loopCounter = 0; loopCounter < args.length; loopCounter++){
         theMessage += args[loopCounter];    
        }
        //System.out.println("the message assembled is " + theMessage);
        return theMessage;
    }        
} 