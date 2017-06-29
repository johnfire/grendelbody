package basicstuff;

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

import basicstuff.message;

/**
 *
 * @author christopherrehm
 */
public class basicObject {
    
    // this is the basic object that all objects are derived from it has the following properties
    // creates and implements analysis
    // creates and implements basic message sending anywhere in the system
    // maintains a list of who is accessing this object lock problems 
    // write data to txt file 
    // read data from txt file
    
    private long timerTotal = 0 ;
    private long startTime = 0 ;
    private long timerStop = 0 ;
    

    public basicObject() {
       startTime = this.getCurrentTime();  
    }
    
    public long calcluateTimeDiff(long startTime, long endTime) {
        return endTime-startTime;   
    }
        
    public long updateTotalTime(long startTime, long endTime){
        return this.timerTotal += endTime - startTime;
    }
    
    public long getCurrentTime() {
        return System.currentTimeMillis();
    }
    
    public long newMessage() {   
        message newMessage = new message();
        return newMessage.showID();
    }  
    
    public String readTxtFile(String someTxtFile){
       String mystring ="";   
       // some proceedure here
       return mystring; 
    }
    
    public int writeTxtFile(String someTxt){
        // some proceedure here
        return 0;
    }
    
            
    
}
