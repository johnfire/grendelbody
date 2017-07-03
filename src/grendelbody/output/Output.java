/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grendelbody.output;

import basicstuff.ObjectStatus;

/**
 *
 * @author christopherrehm
 */
public class Output extends basicstuff.BasicObject {
    
    int MyId = 15;
    
    @Override
    public void run() {
        
        ObjectStatus myStats = new basicstuff.ObjectStatus();
        myStats.setMyName("Output cell");
        Thread outputThread = new Thread(myStats);
        outputThread.start();
        this.systemMessageStartUp("started output thread");
    }    
}
