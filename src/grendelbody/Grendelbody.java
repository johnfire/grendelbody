/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grendelbody;

     //test again   
///test for git
import basicstuff.*;
import grendelbody.internetinout.internetInterface;
import grendelbody.output.output;
import grendelbody.soundin.soundin;
import grendelbody.visionin.visionin;

/*
 *
 * @author christopherrehm
 */
public class Grendelbody extends basicObject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // TODO code application logic here
        //
        try {
            internetInterface myInternetInterface = new internetInterface();
            output myOutput = new output();
            soundin mySoundIn = new soundin();
            visionin myVisionIn= new visionin();
            
            myInternetInterface.start();
            myOutput.start();
            mySoundIn.start();
            myVisionIn.start();
        }
        catch (Exception e){
            System.out.println("something isnt working in grendelbody");    
        }   
    }  
}
