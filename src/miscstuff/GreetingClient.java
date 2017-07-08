/*
 * Copyright (C) 2017 christopherrehm.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of theßpassing object over channel javaßpassing object over channel java License, or (at your option) any later version.
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
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

public class GreetingClient extends basicstuff.BasicObject {

    Socket clientSocket;
    int myId = 20;
    Message myMessageHolder;
    LinkedList<Message> incomingMessages = new LinkedList();
    SocketChannel mySC;

    public GreetingClient(String ip, int port) throws IOException {
        // constructor.
        
        mySC = SocketChannel.open();
        mySC.configureBlocking(false);
        mySC.connect(new InetSocketAddress(ip, port));
        while (!mySC.finishConnect()) {
        }
    }
   
    /**
     * @param listToSend
     * @return 
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
 
    public LinkedList<Message> transferMessages(LinkedList<Message> listToSend) throws IOException, ClassNotFoundException {
        
        ObjectOutputStream oOS = new <Message> ObjectOutputStream(Channels.newOutputStream(mySC));
        ObjectInputStream oIS = new <Message> ObjectInputStream(Channels.newInputStream(mySC));
        
        while(listToSend.size()>0){
           Message tempMessage = listToSend.removeFirst();
           oOS.writeObject(tempMessage);  
        }
        
        while(oIS.available() >1){
          Message tempMessage = (Message) oIS.readObject();
          incomingMessages.addLast(tempMessage);
        }
        return incomingMessages;
    }
}
    

       
       
       
    

