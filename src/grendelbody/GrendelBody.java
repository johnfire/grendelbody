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
import grendelbody.internetinout.InternetInterface;
import grendelbody.output.Output;
import grendelbody.soundin.SoundIn;
import grendelbody.visionin.VisionIn;

/*
 *
 * @author christopherrehm
 */
public class GrendelBody extends BasicObject {
    public static final int  myId = 11; 
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int delayTime =1000; // needed for socket sconnections
        // TODO code application logic here
        //
        try {
            InternetInterface myInternetInterface = new InternetInterface();
            Thread theInternetInterface = new Thread(myInternetInterface);
            Output myOutput = new Output();
            Thread.sleep(delayTime);
            Thread theOutputer = new Thread(myOutput);
            SoundIn mySoundIn = new SoundIn();
            Thread.sleep(delayTime);
            Thread theListener = new Thread(mySoundIn);
            VisionIn myVisionIn= new VisionIn();
            Thread.sleep(delayTime);
            Thread theEyes = new Thread(myVisionIn);
            Thread.sleep(delayTime);
            ThreadTracker myThreadTracker = new ThreadTracker();
            Thread theTracker = new Thread(myThreadTracker);
            
            theInternetInterface.start();
            theOutputer.start();
            theListener.start();
            theEyes.start();
            theTracker.start();
            
        }
        catch (Exception e){
            System.out.println("-----SYSTEM MESSAGE ERROR----Something failed in GrendelBody");
        }   
    }  
}
