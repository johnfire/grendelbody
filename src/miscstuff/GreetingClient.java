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
import java.util.logging.Level;
import java.util.logging.Logger;


public class GreetingClient extends basicstuff.basicObject{
    private BufferedReader in;
    private PrintWriter out;


    /**
     * Constructs the client by laying out the GUI and registering a
     * listener with the textfield so that pressing Enter in the
     * listener sends the textfield contents to the server.
     */
    public GreetingClient(String computer, int port) throws IOException {
             Socket mySocket = new Socket(computer, port );

        try {
            BufferedReader msgIn = null;
            try {
                msgIn = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
                PrintWriter msgOut = new PrintWriter(mySocket.getOutputStream(), true);
            } catch (IOException ex) {
                Logger.getLogger(GreetingClient.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    msgIn.close();
                } catch (IOException ex) {
                    Logger.getLogger(GreetingClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (".".equals(inputLine)) {
                    out.println("good bye");
                    break;
                }
                out.println(inputLine);
            }
            
            
            /**
             * Runs the client application.
             */
//    public static void main(String[] args) throws Exception {
//        CapitalizeClient client = new CapitalizeClient();
//        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        client.frame.pack();
//        client.frame.setVisible(true);
//        client.connectToServer();
//    }
//   public void run(String aServer, int port) {
//      String serverName = aServer;
//      try {
//         System.out.println("Connecting to " + serverName + " on port " + port);
//         Socket client = new Socket(serverName, port);
//         
//         System.out.println("Just connected to " + client.getRemoteSocketAddress());
//         
//         OutputStream outToServer = client.getOutputStream();
//         DataOutputStream out = new DataOutputStream(outToServer);
//         System.out.println("past data stream setup");
//         
//         outToServer.("Hello from " + client.getLocalSocketAddress());
//         
//         InputStream inFromServer = client.getInputStream();
//         DataInputStream in = new DataInputStream(inFromServer);
//         
//         System.out.println("Server says " + in.readUTF());
//         //client.close();
//      }catch(IOException e) {
//         e.printStackTrace();
//      }
//   }
//   
//
//        
//    }
        } catch (IOException ex) {
            Logger.getLogger(GreetingClient.class.getName()).log(Level.SEVERE, null, ex);
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
