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

    /**
     *
     */
    public LinkedList<Message> myMessagesToSend;
    public LinkedList<Message> myMessagesToRead;
    
    public InternetInterface() {
        myMessagesToSend = new LinkedList(); 
        myMessagesToRead = new LinkedList();
    }      
    
    @Override
    public void run() {
        
        this.systemMessageStartUp("starting Internet Interface run routine");
        
        Message testMessage =new Message();
        testMessage.setOrigin(00005);
        testMessage.setDestination(101);
        testMessage.setActionCode(0);
        testMessage.setMessageTxt("aha this works!!!!!!!!!!!!!!!!");
        this.systemMessage("the test message is located at " + testMessage +"in the internet interface");
        this.myMessagesToSend.addLast(testMessage);
        
        Message anotherTestMsg;
        anotherTestMsg = this.generateRndMessage(myId, 101 , 0,  intAry , "") ;  //(myId, 101 , 0, 0, "")
        this.myMessagesToSend.addLast(anotherTestMsg);
        
        //start status monitor
        ObjectStatus myStats = new basicstuff.ObjectStatus();
        myStats.setMyName("internet Interface");
        Thread intInfThread = new Thread(myStats);
        intInfThread.start();
        this.systemMessageStartUp("started Internet Interface self monitoring thread");
        
        // set up connection 
        GreetingClient myClient = null;
        myClient = new GreetingClient("192.168.0.101",5000);
        myClient.startConnection("192.168.0.101",5000);
        
        this.systemMessage("-----InternetInterface-----made contact from internet Interface to router");
        // enter work loop 
        while(true){
            try {
                // send all mesasges in list
            while(this.myMessagesToSend.isEmpty() != true){
                myClient.sendMessageObject(this.myMessagesToSend.removeFirst());
                this.systemMessage("-----Internet Interface sent a message");
            }
            while(this.myMessagesToRead.isEmpty() != true){
                try {
                    this.myMessagesToRead.addLast(myClient.receiveMessageObject());
                    this.systemMessage("-----Internet Interface----- just received a message");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(InternetInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            } catch (IOException ex) {
                Logger.getLogger(InternetInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
            //this.systemMessage("-----InternetInterface-----leaving work loop statement");
        }
    } 
} 