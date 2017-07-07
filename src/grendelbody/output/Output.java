/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grendelbody.output;

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
public class Output extends basicstuff.BasicObject {
    
    int myId = 15;
    int [] intAry = {0,0,0};
    
    public LinkedList<Message> myMessagesToSend;
    public LinkedList<Message> myMessagesToRead;
    
    public Output() {
        myMessagesToSend = new LinkedList(); 
        myMessagesToRead = new LinkedList();
    } 
    
    @Override
    public void run() {
        
        this.systemMessageStartUp("Starting Output run routine");
        
        ObjectStatus myStats = new basicstuff.ObjectStatus();
        myStats.setMyName("Output cell");
        Thread outputThread = new Thread(myStats);
        outputThread.start();
        this.systemMessageStartUp("Started Output self monitoring thread");
       
        // set up connection 
        GreetingClient myClient = null;
        myClient = new GreetingClient("192.168.0.101",5000);
        myClient.startConnection("192.168.0.101",5000);
        
        this.systemMessage("-----Output Cell----- Made contact from Output cell to router");
        
        Message anotherMessage = new Message(myId,myId,101,1,intAry, "blah blah",true);
        this.myMessagesToSend.addLast(anotherMessage);
        
        // enter work loop 
        while(true){
            try {
                // send all mesasges in list
                myMessagesToRead = myClient.transferMessages(myMessagesToSend);
            } catch (IOException ex) {
                Logger.getLogger(InternetInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // play sound
            
            // send movements
            
            // do other work
            
            //this.systemMessage("-----InternetInterface-----leaving work loop statement");
        }
    }    
}
