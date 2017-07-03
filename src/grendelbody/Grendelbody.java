/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grendelbody;

     //test again   
///test for git
import basicstuff.*;
import basicstuff.ThreadTracker;
import grendelbody.internetinout.internetInterface;
import grendelbody.output.output;
import grendelbody.soundin.soundin;
import grendelbody.visionin.visionin;

/*
 *
 * @author christopherrehm
 */
public class Grendelbody extends basicObject {
    public static final long  myId = 11; 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // TODO code application logic here
        //
        try {
            internetInterface myInternetInterface = new internetInterface();
            Thread theInternetInterface = new Thread(myInternetInterface);
            output myOutput = new output();
            Thread theOutputer = new Thread(myOutput);
            soundin mySoundIn = new soundin();
            Thread theListener = new Thread(mySoundIn);
            visionin myVisionIn= new visionin();
            Thread theEyes = new Thread(myVisionIn);
            ThreadTracker myThreadTracker = new ThreadTracker();
            Thread theTracker = new Thread(myThreadTracker);
            
            theInternetInterface.start();
            theOutputer.start();
            theListener.start();
            theEyes.start();
            theTracker.start();
            
        }
        catch (Exception e){
            System.out.println("something isn't working in grendelbody");    
        }   
    }  
}
