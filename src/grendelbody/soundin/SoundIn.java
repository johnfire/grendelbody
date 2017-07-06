/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grendelbody.soundin;

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
public class SoundIn extends basicstuff.BasicObject {
    
    int MyId = 4;
    public LinkedList<Message> myMessagesToSend;
    public LinkedList<Message> myMessagesToRead;
    int port = 5000;
    String iPAddress ="192.198.0.101";
    
    public SoundIn() {
        myMessagesToSend = new LinkedList(); 
        myMessagesToRead = new LinkedList();
    } 
    
    @Override
    public void run() {
        
        this.systemMessageStartUp("starting soundin run routine");
        
        
        ObjectStatus myStats = new basicstuff.ObjectStatus();
        myStats.setMyName("soundin cell");
        Thread soundInThread = new Thread(myStats);
        soundInThread.start(); 
        this.systemMessageStartUp("started Sound In self monitoring thread");

        
        //this.startObjStatus("Sound In");
        
        // set up connection 
        GreetingClient myClient = null;
        myClient = new GreetingClient("192.168.0.101",5000);
        myClient.startConnection("192.168.0.101",5000);
        
        
        this.systemMessage("-----SoundIn Cell-----made contact from Sound Cell to router");
        // enter work loop 
        while(true){
            try {
                // send all mesasges in list
            while(this.myMessagesToSend.isEmpty() != true){
                myClient.sendMessageObject(this.myMessagesToSend.removeFirst());
                this.systemMessage("-----SoundIn Cell----- sent a message");
            }
            while(this.myMessagesToRead.isEmpty() != true){
                try {
                    this.myMessagesToRead.addLast(myClient.receiveMessageObject());
                    this.systemMessage("-----SoundIn Cell----- just received a message");
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
