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
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author christopherrehm
 */
public class InternetInterface extends BasicObject  {
    
    int myId = 8;
    int[] intAry= {1,2,3,4};

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
        testMessage = new Message(5,5,101,1,intAry,"blah");
        testMessage.setOrigin(00005);
        testMessage.setDestination(101);
        testMessage.setMyActionCode(0);
        testMessage.setMessageTxt("aha this works!!!!!!!!!!!!!!!!");
        this.systemMessage("The test message is located at " + testMessage +" in the Internet Interface" + testMessage.showMessageNr() + " = msg nr ::" + testMessage.showOrigin() + "= origin ::" + testMessage.showDestination() + "= dest ::" + testMessage.getMessageTxt());
        this.myMessagesToSend.addLast(testMessage);
        
        Message anotherMessage = new Message(5,5,101,1,intAry, "blah blah");
        
        this.myMessagesToSend.addLast(anotherMessage);
        
        //start status monitor
        ObjectStatus myStats = new basicstuff.ObjectStatus();
        myStats.setMyName("Internet Interface cell");
        Thread intInfThread = new Thread(myStats);
        intInfThread.start();
        this.systemMessageStartUp("Started Internet Interface self monitoring thread");
        
        // set up connection 
        GreetingClient myClient = null;
        myClient = new GreetingClient("192.168.0.101",5000);
        myClient.startConnection("192.168.0.101",5000);
        
        this.systemMessage("-----Internet Interface cell----- Made contact from Internet Interface to router");
        
        // enter work loop 
        while(true){
            
            // send all mesasges in list
            try {
                while(this.myMessagesToSend.isEmpty() != true){
                    myClient.sendMessageObject(this.myMessagesToSend.removeFirst());
                    this.systemMessage("-----Internet Interface cell----- Sent a message");
                }
                while(this.myMessagesToRead.isEmpty() != true){
                    try {
                        this.myMessagesToRead.addLast(myClient.receiveMessageObject());
                        this.systemMessage("-----Internet Interface cell----- Received a message");
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(InternetInterface.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(InternetInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Send and recieve Internet data
            
            // do other work 
            
            //this.systemMessage("-----InternetInterface-----leaving work loop statement");
        }
    } 
} 