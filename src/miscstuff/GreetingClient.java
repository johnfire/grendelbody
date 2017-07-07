/*
 * Copyright (C) 2017 christopherrehm.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package miscstuff;

/**
 *
 * @author christopherrehm
 */

// File Name GreetingClient.java
import basicstuff.*;
import java.net.*;
import java.io.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GreetingClient extends basicstuff.BasicObject{

    Socket clientSocket;
    int myId = 20;
    int[] intAry ={1,2,3};

    public GreetingClient(String ip, int port) {
        // constructor.
    }
   
    /**
     * Constructs the client by laying out the GUI and registering a
     * listener with the text field so that pressing Enter in the
     * listener sends the text field contents to the server.
     * @param ip
     * @param port
     */
 
    public void startConnection(String ip, int port) {

        try {
            clientSocket = new Socket(ip, port);
            this.systemMessage("In Greeting Client, established socket");
        } catch (IOException ex) {
            Logger.getLogger(GreetingClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public LinkedList<Message> transferMessages(LinkedList<Message> listToSend) throws IOException {
       Message myMessageHolder;
       LinkedList<Message> incomingMessages = null;
       
       ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
       ObjectInputStream fromServer = new ObjectInputStream(clientSocket.getInputStream());
       
       while(listToSend.isEmpty() != true){
          myMessageHolder = listToSend.removeFirst();
          //outToServer.reset();
          outToServer.writeObject(myMessageHolder);
          //outToServer.flush();
       }
       while(fromServer.available() > 1){
           try {
               myMessageHolder = (Message)fromServer.readObject();
               incomingMessages.addLast(myMessageHolder);
           } catch (ClassNotFoundException ex) {
               Logger.getLogger(GreetingClient.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       return incomingMessages;  
    }
}
