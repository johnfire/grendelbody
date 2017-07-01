package basicstuff;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.IOUtils;

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

/**
 *
 * @author christopherrehm
 */
public abstract class basicObject extends Thread {
    
    // this is the basic object that all objects are derived from it has the following properties
    // creates and implements analysis
    // creates and implements basic message sending anywhere in the system
    // maintains a list of who is accessing this object lock problems 
    // write data to txt file 
    // read data from txt file
    
    private long timerTotal = 0 ;
    private long startTime = 0 ;
   

    public long now() {
       return this.getCurrentTime();  
    }
    
    public void setStrartTime(long inputTime){
        startTime = inputTime;  
    }
    
    public long getStartTime(){
        return startTime;
    }
    
    public long calcluateTimeDiff(long startTime, long endTime) {
        return endTime-startTime;   
    }
        
    public long updateTotalTime(long startTime, long endTime){
        return timerTotal += endTime - startTime;
    }
    
    public long getCurrentTime() {
        return System.currentTimeMillis();
    }
    
    public long getTotalTime(){
        return timerTotal;
    }
    
    public long newMessage() {   
        message newMessage = new message();
        return newMessage.showID();
    }  
    
    public String readTxtFile(String someTxtFile){
       String mystring = null;   
       // some proceedure here
       System.out.println("Reading File from Java code");
       //Name of the file
       try{
          //Create object of FileReader
          FileReader inputFile = new FileReader(someTxtFile);

          //Instantiate the BufferedReader Class
          BufferedReader bufferReader = new BufferedReader(inputFile);

          //Variable to hold the one line data
          String line;

          // Read file line by line and print on the console
          while ((line = bufferReader.readLine()) != null)   {
              mystring += line;
              // System.out.println(line);
          }
          //Close the buffer reader
          bufferReader.close();
       }catch(Exception e){
          System.out.println("Error while reading file line by line:" + e.getMessage());                      
       }
       return mystring; 
    }
    
    public int writeTxtFile(String someTxt, String someTxtFile){   
         try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(someTxtFile), "utf-8"))) {
           writer.write(someTxt);
        } catch (IOException ex) {
            Logger.getLogger(basicObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }  
}
