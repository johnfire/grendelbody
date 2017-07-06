/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grendelbody.visionin;

import basicstuff.Message;
import basicstuff.ObjectStatus;
import grendelbody.internetinout.InternetInterface;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import miscstuff.GreetingClient;

/**
 *
 * @author christopherrehm
 */
public class VisionIn extends basicstuff.BasicObject {
    
    int MyId = 003;
    public LinkedList<Message> myMessagesToSend;
    public LinkedList<Message> myMessagesToRead;
    
    public VisionIn() {
        myMessagesToSend = new LinkedList(); 
        myMessagesToRead = new LinkedList();
    } 
   
    @Override
    public void run() {
        
        this.systemMessageStartUp("Starting Vision In run routine");


        ObjectStatus myStats = new basicstuff.ObjectStatus();
        myStats.setMyName("Vision In cell");
        Thread visionThread = new Thread(myStats);
        visionThread.start(); 
        this.systemMessageStartUp("Started vision self monitoring thread");
        
        // set up connection 
        GreetingClient myClient = null;
        myClient = new GreetingClient("192.168.0.101",5000);
        myClient.startConnection("192.168.0.101",5000);
        
        this.systemMessage("-----Vision In Cell----- Made contact from Vision In cell to router");
        // enter work loop 
        while(true){
            //send and get all messages 
            try {
                // send all mesasges in list
                while(this.myMessagesToSend.isEmpty() != true){
                    myClient.sendMessageObject(this.myMessagesToSend.removeFirst());
                    this.systemMessage("-----Vision In Cell----- Sent a message");
                }
                while(this.myMessagesToRead.isEmpty() != true){
                    try {
                        this.myMessagesToRead.addLast(myClient.receiveMessageObject());
                        this.systemMessage("-----Vision In Cell----- Received a message");
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(InternetInterface.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(InternetInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
            // do other work, collect data, or send data
            
            //this.systemMessage("-----InternetInterface-----leaving work loop statement");
        }
    }
}
