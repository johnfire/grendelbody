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
package basicstuff;

/**
 *
 * @author christopherrehm
 */

public class message{

    private long idNumber ;
    private long myOrigin ;
    private long myDestination;
    private long myData;  
    
    public void setID(long id){
        idNumber = id;
    }
    
    public long showID(){
        return idNumber;
    }
    
    public void setOrigin (long id){
        myOrigin = id;
    }
    
    public long showOrigin(){
        return myOrigin;   
    }
    
    public void setDestination(long id){
        myDestination = id;    
    }
    
    public long showDestination(long id){
        return myDestination;
    }
    
    public void setDataID(long id){
        myData = id;
    }
    
    public long showDataID(){
        return myData;    
    }
}
