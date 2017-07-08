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
    
    int myId = 4;
    int [] intAry = {0,0,0};
    public LinkedList<Message> myMessagesToSend;
    public LinkedList<Message> myMessagesToRead;
    
    public SoundIn() {
        myMessagesToSend = new LinkedList(); 
        myMessagesToRead = new LinkedList();
    } 
    
    @Override
    public void run() {
        
        try {
            this.systemMessageStartUp("Starting Sound In run routine");
            
            ObjectStatus myStats = new basicstuff.ObjectStatus();
            myStats.setMyName("Sound In cell");
            Thread soundInThread = new Thread(myStats);
            soundInThread.start();
            this.systemMessageStartUp("Started Sound In self monitoring thread");
            
            //this.startObjStatus("Sound In");
            
            // set up connection
            GreetingClient myClient = null;
            myClient = new GreetingClient("192.168.0.101",5000);
            
            
            this.systemMessage("-----Sound In Cell----- Made contact from Sound Cell to router");
            
            Message anotherMessage = new Message(myId,myId,101,1,intAry, "blah blah",true);
            this.myMessagesToSend.addLast(anotherMessage);
            
            // enter work loop
            while(true){
                
                // send all mesasges in list
                try {
                    myMessagesToRead= myClient.transferMessages(myMessagesToSend);
                    
                } catch (IOException ex) {
                    Logger.getLogger(InternetInterface.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SoundIn.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // collect sound file, send to db
                
                // do other work
                
                //this.systemMessage("-----InternetInterface-----leaving work loop statement");
            }
        } catch (IOException ex) {
            Logger.getLogger(SoundIn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
