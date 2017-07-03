/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grendelbody.soundin;

import basicstuff.ObjectStatus;

/**
 *
 * @author christopherrehm
 */
public class SoundIn extends basicstuff.BasicObject {
    
    int MyId = 4;
    
    @Override
    public void run() {
        
        System.out.println("we are in the soundin routine");
        
        ObjectStatus myStats = new basicstuff.ObjectStatus();
        myStats.setMyName("soundin cell");
        Thread soundInThread = new Thread(myStats);
        soundInThread.start(); 
        this.systemMessageStartUp("started sound in thread");
    }    
}
