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
import basicstuff.message;
import java.net.*;
import java.io.*;

public class GreetingClient {

   public void run(String aServer, int port) {
      String serverName = aServer;
      try {
         System.out.println("Connecting to " + serverName + " on port " + port);
         Socket client = new Socket(serverName, port);
         
         System.out.println("Just connected to " + client.getRemoteSocketAddress());
         OutputStream outToServer = client.getOutputStream();
         DataOutputStream out = new DataOutputStream(outToServer);
         
         out.writeUTF("Hello from " + client.getLocalSocketAddress());
         InputStream inFromServer = client.getInputStream();
         DataInputStream in = new DataInputStream(inFromServer);
         
         System.out.println("Server says " + in.readUTF());
         //client.close();
      }catch(IOException e) {
         e.printStackTrace();
      }
   }
    public void sendMessage(message amessage){
        try{
            System.out.println("entered sendmessage method");
            Socket client = new Socket("192.168.0.101", 5000);
            System.out.println("111111entered sendmessage method");
            // Create the input & output streams to the server
            ObjectOutputStream outToServer = new ObjectOutputStream(client.getOutputStream());
            //ObjectInputStream inFromServer = new ObjectInputStream(client.getInputStream());
            System.out.println("22222entered sendmessage method");
            outToServer.writeObject(amessage);
            System.out.print("just sent a message");
                
        }catch(IOException e){
            System.out.println("something failed in GreetingClient.sendMessage");
        }
        
        
    }
}
