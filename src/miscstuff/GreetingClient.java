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
//import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GreetingClient extends basicstuff.BasicObject{
    BufferedReader in;
    PrintWriter out;
    Socket clientSocket;
    int myId = 20;

    public GreetingClient(String ip, int port) { //To change body of generated methods, choose Tools | Templates.
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
            try {
                clientSocket = new Socket(ip, port);
                this.systemMessage("In Greeting Client, established socket");
            } catch (IOException ex) {
                Logger.getLogger(GreetingClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(GreetingClient.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
   
    public String sendMessage(String msg) {
        out.println(msg);
        
        String response ="";
        try {
            response = in.readLine();
            System.out.println(response);
        } catch (IOException ex) {
            Logger.getLogger(GreetingClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
    
    public void sendMessageObject (Message myMessage) throws IOException{
        ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
        //System.out.println("-----System Message- entered send Message Object-----");
        this.systemMessageStartUp("-----Greeting Client----- Entered send Message Object-----");
        outToServer.reset();
        outToServer.writeObject(myMessage);
        outToServer.flush();
        this.systemMessage("-----Greeting Client----- Sent this "+ myMessage);
        this.systemMessage("-----Greeting Client----- Sent Message #" + myMessage.showMessageNr());
    }
    
    public Message receiveMessageObject () throws IOException, ClassNotFoundException{
        Message newMessage = new Message();
        ObjectInputStream fromServer = new ObjectInputStream(clientSocket.getInputStream());
        newMessage = (Message) fromServer.readObject();
        System.out.println(newMessage);
        return newMessage;
    }
}
