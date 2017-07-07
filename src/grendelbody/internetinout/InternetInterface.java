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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author christopherrehm
 */
public class InternetInterface extends BasicObject  {
    
    int myId = 8;
    int[] intAry= {0,0,0};

    public LinkedList<Message> myMessagesToSend;
    public LinkedList<Message> myMessagesToRead;
    
    public InternetInterface() {
        myMessagesToSend = new LinkedList(); 
        myMessagesToRead = new LinkedList();
    }      
    
    @Override
    public void run() {
        
        this.systemMessageStartUp("Starting Internet Interface run routine");
        
        Message testMessage;
        testMessage = new Message(8,8,101,1,intAry,"ok this works",true);
        this.systemMessage(" In internet interface ::  The test message is located at " + testMessage +" in the Internet Interface " + testMessage.showMessageNr() + " = msg nr ::" + testMessage.showOrigin() + " = origin ::" + testMessage.showDestination() + " = dest ::"+ testMessage.getMyActionCode() + " = :: acton code " + testMessage.getMessageTxt());
        this.myMessagesToSend.addLast(testMessage);
        
        Message anotherMessage = new Message(myId,myId,101,1,intAry, "blah blah",true);
        this.myMessagesToSend.addLast(anotherMessage);
        
        //myId, myId, 4, intAry, "bsgfabgfabbaf", true
        //this.myMessagesToSend.addLast(this.generateRndMessage(myId, myId, myId, intAry, "QWERQWERQERQWQR", true))
        
        //start status monitor
        ObjectStatus myStats = new basicstuff.ObjectStatus();
        myStats.setMyName("Internet Interface cell");
        Thread intInfThread = new Thread(myStats);
        intInfThread.start();
        this.systemMessageStartUp("in internet interface ::   Started Internet Interface self monitoring thread");
        
        // set up connection 
        GreetingClient myClient = null;
        myClient = new GreetingClient("192.168.0.101",5000);
        myClient.startConnection("192.168.0.101",5000);
        
        this.systemMessage("-----Internet Interface cell----- Made contact from Internet Interface to router");
        
        // enter work loop 
        while(true){
            
            anotherMessage = new Message(myId,myId,7,1,intAry, "blah blah",true);
            this.myMessagesToSend.addLast(anotherMessage);
            
            // send all mesasges in list
            try {
                
                myMessagesToRead= myClient.transferMessages(myMessagesToSend);
            
            } catch (IOException ex) {
                Logger.getLogger(InternetInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Send and recieve Internet data
            
            // do other work 
            
            //this.systemMessage("-----InternetInterface-----leaving work loop statement");
        }
    } 
} 