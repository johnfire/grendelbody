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
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GreetingClient extends basicstuff.basicObject{
    private BufferedReader in;
    private PrintWriter out;
     private Socket clientSocket;

    public GreetingClient(String string, int i) { //To change body of generated methods, choose Tools | Templates.
    }
   
    /**
     * Constructs the client by laying out the GUI and registering a
     * listener with the textfield so that pressing Enter in the
     * listener sends the textfield contents to the server.
     * @throws java.io.IOException
     */
 
    public void startConnection(String ip, int port) {
        try {
            try {
                clientSocket = new Socket(ip, port);
                System.out.println("in GreetingClient, establised socket");
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
        
        String resp ="";
        try {
            resp = in.readLine();
            System.out.println(resp);
        } catch (IOException ex) {
            Logger.getLogger(GreetingClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }
    
    public void sendMessageObject (message myMessage) throws IOException{
        ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
        outToServer.writeObject(myMessage); 
    }
    
    public message ReceiveMessageObject () throws IOException, ClassNotFoundException{
        message newMessage = new message();
        ObjectInputStream fromServer = new ObjectInputStream(clientSocket.getInputStream());
        newMessage = (message) fromServer.readObject();
        System.out.println(newMessage);
        return newMessage;
    }
}
