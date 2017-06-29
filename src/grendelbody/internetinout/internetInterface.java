/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grendelbody.internetinout;

import basicstuff.basicObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author christopherrehm
 */
public class internetInterface extends basicObject {
    int runVar = 1;
    @Override
    public void run() {
        System.out.println("we are in the internet interface routine first time"
                + "");
        while (runVar== 1){
            try {
                Thread.sleep(5000);
                System.out.println("we are in the internet interface routine");  
            } catch (InterruptedException ex) {
                Logger.getLogger(internetInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }   
}
