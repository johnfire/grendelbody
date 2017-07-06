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
    
    int MyId = 15;
    public LinkedList<Message> myMessagesToSend;
    public LinkedList<Message> myMessagesToRead;
    int port = 5000;
    String iPAddress ="192.198.0.101";
    
    public Output() {
        myMessagesToSend = new LinkedList(); 
        myMessagesToRead = new LinkedList();
    } 
    
    @Override
    public void run() {
        
        this.systemMessageStartUp("starting Output run routine");
        
        ObjectStatus myStats = new basicstuff.ObjectStatus();
        myStats.setMyName("Output cell");
        Thread outputThread = new Thread(myStats);
        outputThread.start();
        this.systemMessageStartUp("started output self monitoring thread");
        
        //this.startObjStatus(" Output ");
        
        // set up connection 
        GreetingClient myClient = null;
        myClient = new GreetingClient("192.168.0.101",5000);
        myClient.startConnection("192.168.0.101",5000);
        
        this.systemMessage("-----Output Cell----- made contact from Internet Interface cell to router");
        // enter work loop 
        while(true){
            try {
                // send all mesasges in list
            while(this.myMessagesToSend.isEmpty() != true){
                myClient.sendMessageObject(this.myMessagesToSend.removeFirst());
                this.systemMessage("-----Output Cell sent a message");
            }
            while(this.myMessagesToRead.isEmpty() != true){
                try {
                    this.myMessagesToRead.addLast(myClient.receiveMessageObject());
                    this.systemMessage("-----Output Cell----- just received a message");
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
