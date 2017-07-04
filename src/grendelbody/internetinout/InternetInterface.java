/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grendelbody.internetinout;

import basicstuff.*;
import miscstuff.GreetingClient;
import basicstuff.Message;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author christopherrehm
 */
public class InternetInterface extends BasicObject  {
    
    int myId = 8;
    int[] intAry= {1,2,3,4};
    @Override
    public void run() {
        
        this.systemMessageStartUp("Internet Interface is starting now");
        Message testMessage =new Message();
        testMessage.setOrigin(00005);
        testMessage.setDestination(101);
        testMessage.setActionCode(0);
        
        testMessage.setMessageTxt("aha this works!!!!!!!!!!!!!!!!");
        System.out.println(testMessage);
        
        Message anotherTestMsg;
         anotherTestMsg = this.generateRndMessage(myId, 101 , 0,  intAry , "") ;  //(myId, 101 , 0, 0, "")
        
        //start status monitor
        ObjectStatus myStats = new basicstuff.ObjectStatus();
        myStats.setMyName("internet Interface");
        Thread intInfThread = new Thread(myStats);
        intInfThread.start();
        
        // set up connection 
        GreetingClient myClient = null;
        myClient = new GreetingClient("192.168.0.101",5000);
        myClient.startConnection("192.168.0.101",5000);
        
        
        this.systemMessage("-----InternetInterface-----made contact from internet Interface to router");
        
        try {
            myClient.sendMessageObject(testMessage);
        } catch (IOException ex) {
            Logger.getLogger(InternetInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        this.systemMessage("-----InternetInterface-----just past send message statement");
        String[] str = {"mememe","you","no-data","the nsa is watchin ya"};
        String aMessage = this.buildMessage(str);
    } 
} 